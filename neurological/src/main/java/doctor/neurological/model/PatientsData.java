package doctor.neurological.model;


import java.io.Serializable;

import doctor.neurological.enums.Gender;

/**
 * Created by bhavdip on 8/21/16.
 */
public class PatientsData implements Serializable {

    private String patientName;
    private String age;
    private Gender gender;
    private boolean migraines;
    private boolean increasesDrugs;
    private int syndromePercentage;
    private String testTime;

    public boolean isIncreasesDrugs() {
        return increasesDrugs;
    }

    public void setIncreasesDrugs(boolean increasesDrugs) {
        this.increasesDrugs = increasesDrugs;
    }

    public int getIncreasesDrugs() {
        return this.increasesDrugs ? 0 : 1;
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

    public int getIntGender() {
        return this.getGender() == Gender.MALE ? 0 : 1;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isMigraines() {
        return migraines;
    }

    public int getIntMigraines() {
        return this.migraines ? 0 : 1;
    }

    public void setMigraines(boolean migraines) {
        this.migraines = migraines;
    }

    public int getSyndromePercentage() {
        return syndromePercentage;
    }

    public void setSyndromePercentage(int syndromePercentage) {
        this.syndromePercentage = syndromePercentage;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "PatientsData{" +
                "patientName='" + patientName + '\'' +
                ", age='" + age + '\'' +
                ", gender=" + gender +
                ", migraines=" + migraines +
                ", increasesDrugs=" + increasesDrugs +
                ", syndromePercentage=" + syndromePercentage +
                ", testTime='" + testTime + '\'' +
                '}';
    }
}
