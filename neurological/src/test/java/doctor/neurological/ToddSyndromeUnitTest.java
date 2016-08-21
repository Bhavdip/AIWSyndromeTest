package doctor.neurological;

import org.junit.Test;

import doctor.neurological.enums.Gender;
import doctor.neurological.model.PatientsData;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ToddSyndromeUnitTest {

    @Test
    public void notMatchingSyndrome() {
        PatientsData mPatientsData = new PatientsData();
        mPatientsData.setPatientName("XYZ");
        mPatientsData.setAge("13");
        mPatientsData.setGender(Gender.FEMALE);
        mPatientsData.setMigraines(false);

        PatientsData testResult = new ToddSyndromeChecker(mPatientsData).calculateResult();
        assertEquals(testResult.getSyndromePercentage(), 0);
    }

    @Test
    public void AnyTwoMatchSyndrome() {
        PatientsData mPatientsData = new PatientsData();
        mPatientsData.setPatientName("Bhavdip");
        mPatientsData.setAge("12");
        mPatientsData.setGender(Gender.MALE);
        mPatientsData.setMigraines(true);
        mPatientsData.setIncreasesDrugs(false);

        PatientsData testResult = new ToddSyndromeChecker(mPatientsData).calculateResult();
        assertEquals(testResult.getSyndromePercentage(), 50);
    }

    @Test
    public void matchingAllSyndrome() {
        PatientsData mPatientsData = new PatientsData();
        mPatientsData.setPatientName("Bhavdip");
        mPatientsData.setAge("29");
        mPatientsData.setGender(Gender.MALE);
        mPatientsData.setMigraines(true);
        mPatientsData.setIncreasesDrugs(true);

        PatientsData testResult = new ToddSyndromeChecker(mPatientsData).calculateResult();
        assertEquals(testResult.getSyndromePercentage(), 100);
    }
}