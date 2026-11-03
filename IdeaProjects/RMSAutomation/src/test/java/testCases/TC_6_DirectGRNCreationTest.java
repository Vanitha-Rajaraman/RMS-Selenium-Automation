package testCases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.GRNCreationPage;

public class TC_6_DirectGRNCreationTest extends TC_2_OrgSelectTest {

    private static final Logger log = LoggerFactory.getLogger(TC_6_DirectGRNCreationTest.class);

    @Test(groups = {"Sanity", "Master"})
    public void GRNCreation() {
        log.info("Starting TC_4_GRNCreationTest");

        try {
            GRNCreationPage grnPage = new GRNCreationPage(driver);
            grnPage.Addproduct();
            log.info("GRN creation process submitted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("GRN creation test failed: " + e.getMessage());
        }

        log.info("Finished TC_4_GRNCreationTest");
    }
}