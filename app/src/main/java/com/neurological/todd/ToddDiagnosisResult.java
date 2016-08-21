package com.neurological.todd;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.neurological.todd.adapter.DiagnosisResultAdapter;
import com.neurological.todd.dao.PatientDiagnosisDAO;
import com.neurological.todd.databinding.ToddResultBinding;

import java.util.List;

import doctor.neurological.model.PatientsData;

public class ToddDiagnosisResult extends BaseActivity {

    private ToddResultBinding mToddResultBinding;
    private final int REQUEST_FOR_DIAGNOSIS = 101;
    public static final String KEY_RESULT_DATA = "rawdata";
    private DiagnosisResultAdapter mDiagnosisResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToddResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_todds_diagnosis_result);
        setUpRecyclerView();
        FloatingActionButton fab = mToddResultBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPatientActivity.startNewPatientAdding(ToddDiagnosisResult.this, REQUEST_FOR_DIAGNOSIS);
            }
        });
        fetchAllResultData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOR_DIAGNOSIS) {
            if (resultCode == RESULT_OK) {
                extractIntentData(data);
            } else {
                showSnackBar(mToddResultBinding.fab, "Cancel Diagnosis test!");
            }
        }
    }

    private void extractIntentData(Intent intentData) {
        if (intentData != null && intentData.hasExtra(KEY_RESULT_DATA)) {
            PatientsData nwPatientData = (PatientsData) intentData.getExtras().get(KEY_RESULT_DATA);
            if (nwPatientData != null) {
                boolean isSaved = new PatientDiagnosisDAO().savePatientDiagnosisRecord(nwPatientData);
                if (isSaved) {
                    if (mDiagnosisResultAdapter != null)
                        mDiagnosisResultAdapter.addPatientItemOnTop(nwPatientData);
                }

            }

        }
    }

    private void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    private void setUpRecyclerView() {
        mDiagnosisResultAdapter = new DiagnosisResultAdapter(this);
        mToddResultBinding.contents.patientDiagnosisList.setLayoutManager(new LinearLayoutManager(this));
        mToddResultBinding.contents.patientDiagnosisList.setHasFixedSize(true);
        mToddResultBinding.contents.patientDiagnosisList.setAdapter(mDiagnosisResultAdapter);
    }

    private void fetchAllResultData() {
        List<PatientsData> patientsDataList = new PatientDiagnosisDAO().getAllDiagnosisTestResult();
        if (patientsDataList != null && patientsDataList.size() > 0) {
            mToddResultBinding.contents.emptyView.setVisibility(View.GONE);
            mDiagnosisResultAdapter.addPatientDataSet(patientsDataList);
        } else {
            mToddResultBinding.contents.emptyView.setVisibility(View.VISIBLE);
        }
    }
}
