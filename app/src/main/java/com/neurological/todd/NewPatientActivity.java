package com.neurological.todd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;

import com.neurological.todd.databinding.AddNewPatientBinding;
import com.neurological.todd.utility.AdvanceTextWatcher;
import com.neurological.todd.utility.AgeInputDetector;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by bhavdip on 8/21/16.
 */
public class NewPatientActivity extends AppCompatActivity {

    private AddNewPatientBinding mAddNewPatientBinding;

    public static void startNewPatientAdding(Activity activityContext) {
        Intent mIntent = new Intent(activityContext, NewPatientActivity.class);
        activityContext.startActivity(mIntent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
}
