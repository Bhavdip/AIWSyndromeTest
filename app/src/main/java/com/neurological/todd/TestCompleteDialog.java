package com.neurological.todd;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.neurological.todd.databinding.TestCompleteBinding;

import java.util.Locale;

import doctor.neurological.ToddSyndromeChecker;
import doctor.neurological.model.PatientsData;

/**
 * Created by bhavdip on 8/21/16.
 */
public class TestCompleteDialog extends BaseActivity {

    private static final String TAG = "TestCompleteDialog";

    private static final String KEY_RAW_DATA = "RawData";

    private TestCompleteBinding mTestCompleteBinding;
    private PatientsData mPatientsData;


    public static void startTextCompleteDialog(Activity activityContext, PatientsData mPatientsData) {
        Intent intentDialog = new Intent(activityContext, TestCompleteDialog.class);
        intentDialog.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intentDialog.putExtra(KEY_RAW_DATA, mPatientsData);
        activityContext.startActivity(intentDialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTestCompleteBinding = DataBindingUtil.setContentView(this, R.layout.dialog_test_complete);
        extractData();
        mTestCompleteBinding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(ToddDiagnosisResult.KEY_RESULT_DATA, mPatientsData);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }


    private void extractData() {
        if (getIntent().hasExtra(KEY_RAW_DATA)) {
            mPatientsData = (PatientsData) getIntent().getExtras().get(KEY_RAW_DATA);
            if (mPatientsData != null) {
                Log.d(TAG, String.format("Received Intent Data:%s", mPatientsData.toString()));
                mTestCompleteBinding.textMessage.setText(getDisplayMessage());
            }

        }
    }

    private String getDisplayMessage() {
        PatientsData diagnosisPatientData = new ToddSyndromeChecker(mPatientsData).calculateResult();
        return String.format(Locale.getDefault(), "Here is %2s Diagnosis result\n%d %% of Probability of syndrome detected.",
                diagnosisPatientData.getPatientName(), diagnosisPatientData.getSyndromePercentage());
    }

    @Override
    public void onBackPressed() {

    }
}
