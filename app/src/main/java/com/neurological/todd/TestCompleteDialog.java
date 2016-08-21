package com.neurological.todd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.neurological.todd.databinding.TestCompleteBinding;
import com.neurological.todd.model.PatientsData;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by bhavdip on 8/21/16.
 */
public class TestCompleteDialog extends AppCompatActivity {

    private static final String TAG = "TestCompleteDialog";

    private static final String KEY_RAW_DATA = "RawData";

    private TestCompleteBinding mTestCompleteBinding;
    private PatientsData mPatientsData;


    public static void startTextCompleteDialog(Activity activityContext, PatientsData mPatientsData) {
        Intent intentDialog = new Intent(activityContext, TestCompleteDialog.class);
        intentDialog.putExtra(KEY_RAW_DATA, mPatientsData);
        activityContext.startActivity(intentDialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTestCompleteBinding = DataBindingUtil.setContentView(this, R.layout.dialog_test_complete);
        extractData();
        mTestCompleteBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
        PatientsData diagnosisPatientData = new ToddsSyndromChecker(mPatientsData).calculateResult();
        return String.format(Locale.getDefault(), "Here is %2s Diagnosis result\n%d%% of Probability of syndrome detected.",
                diagnosisPatientData.getPatientName(), diagnosisPatientData.getPercentage());
    }

    @Override
    public void onBackPressed() {

    }
}
