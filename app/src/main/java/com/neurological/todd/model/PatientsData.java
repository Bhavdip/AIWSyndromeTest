package com.neurological.todd.model;

import com.neurological.todd.Gender;

import java.io.Serializable;

/**
 * Created by bhavdip on 8/21/16.
 */
public class PatientsData implements Serializable {

    private String patientName;
    private String age;
    private Gender gender;
    private boolean migraines;
    private boolean increasesDrugs;
    private int percentage;

    public boolean isIncreasesDrugs() {
        return increasesDrugs;
    }

    public void setIncreasesDrugs(boolean increasesDrugs) {
        this.increasesDrugs = increasesDrugs;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isMigraines() {
        return migraines;
    }

    public void setMigraines(boolean migraines) {
        this.migraines = migraines;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "PatientsData{" +
                "patientName='" + patientName + '\'' +
                ", age='" + age + '\'' +
                ", gender=" + gender +
                ", migraines=" + migraines +
                ", increasesDrugs=" + increasesDrugs +
                ", percentage=" + percentage +
                '}';
    }
}
