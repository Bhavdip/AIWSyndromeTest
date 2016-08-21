package com.neurological.todd;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.neurological.todd.databinding.ToddResultBinding;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ToddPatientsList extends AppCompatActivity {

    private ToddResultBinding mToddsResultBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToddsResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_todds_patients_list);
        FloatingActionButton fab = mToddsResultBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPatientActivity.startNewPatientAdding(ToddPatientsList.this);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void showSnackBar(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
