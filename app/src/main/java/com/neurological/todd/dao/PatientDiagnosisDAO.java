package com.neurological.todd.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import doctor.neurological.enums.Gender;
import doctor.neurological.model.PatientsData;

/**
 * Created by bhavdip on 8/21/16.
 */
public class PatientDiagnosisDAO extends BaseDAO {

    public PatientDiagnosisDAO() {
        super();
    }

    public PatientDiagnosisDAO(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    public void createPatientDiagnosisTable() {
        /**
         * Gender 0 male and 1 female
         * increases Drugs 0 true 1 false
         */
        mSQLitBaseDAO.execSQL("create table PatientDiagnosis ( _id INTEGER PRIMARY KEY AUTOINCREMENT, patientName TEXT, age INTEGER,gender INTEGER,migraines INTEGER, increasesDrugs INTEGER, syndromePercentage INTEGER, testTime TEXT)");
    }

    public boolean savePatientDiagnosisRecord(PatientsData patientsData) {
        boolean result;
        if (hasDBConnected()) {

            Object[] values = new Object[]{patientsData.getPatientName(), patientsData.getAge()
                    , patientsData.getIntGender(), patientsData.getIntMigraines()
                    , patientsData.getIncreasesDrugs(), patientsData.getSyndromePercentage()
                    , patientsData.getTestTime()};
            mSQLitBaseDAO.execSQL("insert into PatientDiagnosis ( patientName, age, gender, migraines,increasesDrugs, syndromePercentage, testTime)  values ( ?, ?, ?, ?, ?, ?, ?)", values);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Cursor getSelectQuery() {
        return mSQLitBaseDAO.rawQuery("select * from PatientDiagnosis order by testTime", null);
    }

    public List<PatientsData> getAllDiagnosisTestResult() {
        List<PatientsData> allDiagnosisTest = new ArrayList<>();
        Cursor cursor = getSelectQuery();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PatientsData patientsData = constructModel(cursor);
                allDiagnosisTest.add(patientsData);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return allDiagnosisTest;
    }

    public PatientsData constructModel(Cursor aCursor) {
        PatientsData retValue = new PatientsData();
        retValue.setPatientName(getString(aCursor, "patientName"));
        retValue.setAge(getString(aCursor, "age"));
        retValue.setGender(getInt(aCursor, "gender") == 0 ? Gender.MALE : Gender.FEMALE);
        retValue.setMigraines(getInt(aCursor, "migraines") == 0);
        retValue.setIncreasesDrugs(getInt(aCursor, "increasesDrugs") == 0);
        retValue.setSyndromePercentage(getInt(aCursor, "syndromePercentage"));
        retValue.setTestTime(getString(aCursor, "testTime"));
        return retValue;
    }
}
