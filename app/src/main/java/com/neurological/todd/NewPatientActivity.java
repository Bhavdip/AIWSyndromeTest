package com.neurological.todd;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.neurological.todd.databinding.AddNewPatientBinding;

import com.neurological.todd.utility.AdvanceTextWatcher;
import com.neurological.todd.utility.AgeInputDetector;
import com.neurological.todd.utility.Utils;

import doctor.neurological.enums.Gender;
import doctor.neurological.model.PatientsData;

/**
 * Created by bhavdip on 8/21/16.
 */
public class NewPatientActivity extends BaseActivity {

    private static final String TAG = "NewPatientActivity";

    private AddNewPatientBinding mAddNewPatientBinding;

    public static void startNewPatientAdding(Activity activityContext, int requestCode) {
        Intent mIntent = new Intent(activityContext, NewPatientActivity.class);
        activityContext.startActivityForResult(mIntent, requestCode);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddNewPatientBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_patients);
        mAddNewPatientBinding.contents.editTextAge.setFilters(new InputFilter[]{new AgeInputDetector(1, 100)});
        mAddNewPatientBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAddNewPatientBinding.contents.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAddNewPatientBinding.contents.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientsData passDataSet = collectInputData();
                if (passDataSet != null) {
                    Log.d(TAG, passDataSet.toString());
                    TestCompleteDialog.startTextCompleteDialog(NewPatientActivity.this, passDataSet);
                    finish();
                }
            }
        });

        mAddNewPatientBinding.contents.editTextName.addTextChangedListener(new AdvanceTextWatcher(new AdvanceTextWatcher.onTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                manageSaveOptions();
            }
        }));

        mAddNewPatientBinding.contents.editTextAge.addTextChangedListener(new AdvanceTextWatcher(new AdvanceTextWatcher.onTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                manageSaveOptions();
            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        //opening transition animations
        Utils.activityOpenBottomTransition(NewPatientActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.activityCloseBottomTransition(NewPatientActivity.this);
    }

    private void manageSaveOptions() {
        String name = mAddNewPatientBinding.contents.editTextName.getText().toString().trim();
        String age = mAddNewPatientBinding.contents.editTextAge.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
            saveOptions(true);
        } else {
            saveOptions(false);
        }
    }

    private void saveOptions(boolean visibility) {
        mAddNewPatientBinding.contents.btnSave.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private PatientsData collectInputData() {
        PatientsData mPatientsData = new PatientsData();
        //1. Patient name
        mPatientsData.setPatientName(mAddNewPatientBinding.contents.editTextName.getText().toString());
        //2. Gender
        Gender genderType = mAddNewPatientBinding.contents.btnMale.isChecked() ? Gender.MALE : Gender.FEMALE;
        mPatientsData.setGender(genderType);
        //3. Age
        mPatientsData.setAge(mAddNewPatientBinding.contents.editTextAge.getText().toString().trim());
        //4. Migraines
        mPatientsData.setMigraines(mAddNewPatientBinding.contents.switchMigraines.isChecked());
        //5 taking drugs
        mPatientsData.setIncreasesDrugs(mAddNewPatientBinding.contents.switchDrugs.isChecked());

        mPatientsData.setTestTime(String.valueOf(System.currentTimeMillis()));

        return mPatientsData;
    }
}
