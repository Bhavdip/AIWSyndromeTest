package com.neurological.todd;

import android.text.TextUtils;

import com.neurological.todd.model.PatientsData;

/**
 * Created by bhavdip on 8/21/16.
 */
public class ToddsSyndromChecker {
    private boolean[] answers;
    private PatientsData mPatientsData;
    private int baseLineAge;

    public ToddsSyndromChecker(PatientsData patientsData) {
        this.baseLineAge = 15;
        if (patientsData != null) {
            this.mPatientsData = patientsData;
            answers = new boolean[4];
        }
    }

    public ToddsSyndromChecker(PatientsData patientsData, int baseLineAge) {
        this.baseLineAge = baseLineAge;
        if (patientsData != null) {
            this.mPatientsData = patientsData;
            answers = new boolean[4];
        }
    }

    public PatientsData calculateResult() {
        answers[0] = patientAgeCheck();
        answers[1] = checkMigraines();
        answers[2] = checkGender();
        answers[3] = isTackingDrugs();

        int percentage = 0;
        for (boolean r : answers) {
            if (r) {
                percentage = percentage + 25;
            }
        }
        mPatientsData.setPercentage(percentage);
        return mPatientsData;
    }

    private boolean patientAgeCheck() {
        boolean result = false;
        try {
            if (!TextUtils.isEmpty(mPatientsData.getAge())) {
                //1. People 15 years old or younger are more likely to have it.
                int patientAge = Integer.parseInt(mPatientsData.getAge());
                if (patientAge >= this.baseLineAge) {
                    result = true;
                }
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;

    }

    private boolean checkMigraines() {
        return mPatientsData.isMigraines();
    }

    private boolean checkGender() {
        return mPatientsData.getGender() == Gender.MALE;
    }

    private boolean isTackingDrugs() {
        return mPatientsData.isIncreasesDrugs();
    }
}

