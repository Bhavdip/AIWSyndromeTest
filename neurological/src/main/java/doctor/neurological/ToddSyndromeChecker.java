package doctor.neurological;

import android.text.TextUtils;

import doctor.neurological.enums.Gender;
import doctor.neurological.model.PatientsData;


/**
 * Created by bhavdip on 8/21/16.
 */
public class ToddSyndromeChecker {
    private boolean[] answers;
    private PatientsData mPatientsData;
    private int baseLineAge;

    public ToddSyndromeChecker(PatientsData patientsData) {
        this.baseLineAge = 15;
        if (patientsData != null) {
            this.mPatientsData = patientsData;
            answers = new boolean[4];
        }
    }

    public ToddSyndromeChecker(PatientsData patientsData, int baseLineAge) {
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
        mPatientsData.setSyndromePercentage(percentage);
        return mPatientsData;
    }

    private boolean patientAgeCheck() {
        boolean result = false;
        try {
            if (mPatientsData.getAge() != null || mPatientsData.getAge().length() != 0) {
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

