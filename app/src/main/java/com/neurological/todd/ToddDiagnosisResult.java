package com.neurological.todd;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.neurological.todd.dao.PatientDiagnosisDAO;
import com.neurological.todd.databinding.ToddResultBinding;

import doctor.neurological.model.PatientsData;

public class ToddDiagnosisResult extends BaseActivity {

    private ToddResultBinding mToddsResultBinding;
    private final int REQUEST_FOR_DIAGNOSIS = 101;
    public static final String KEY_RESULT_DATA = "rawdata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToddsResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_todds_diagnosis_result);
        FloatingActionButton fab = mToddsResultBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPatientActivity.startNewPatientAdding(ToddDiagnosisResult.this, REQUEST_FOR_DIAGNOSIS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FOR_DIAGNOSIS) {
            if (resultCode == RESULT_OK) {
                extractIntentData(data);

            }
        }
    }

    private void extractIntentData(Intent intentData) {
        if (intentData != null && intentData.hasExtra(KEY_RESULT_DATA)) {
            PatientsData nwPatientData = (PatientsData) intentData.getExtras().get(KEY_RESULT_DATA);
            if (nwPatientData != null) {
                boolean isSaved = new PatientDiagnosisDAO().savePatientDiagnosisRecord(nwPatientData);
                if (isSaved)
                    showSnackBar(mToddsResultBinding.fab, nwPatientData.getPatientName());
            }

        }
    }

    private void showSnackBar(View view, String name) {
        Snackbar.make(view, "Add Patient " + name + "Successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
