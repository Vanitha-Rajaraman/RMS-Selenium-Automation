package testCases;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PICreationPage;
import pageObjects.VendorCreationPage;

public class TC_3_PICreationTest extends TC_2_OrgSelectTest
{

    private static final Logger log = LoggerFactory.getLogger(TC_3_PICreationTest.class);
    @Test(groups = {"Sanity", "Master"})
    public void PICreation() {
        log.info("Starting TC_6_PICreation");

        try {
            PICreationPage PI = new PICreationPage(driver);
            VendorCreationPage vcr = new VendorCreationPage(driver);
            vcr.Findpurchase();
            PI.FindPurchaseIndent();
            PI.AddPiDetails();
            //PI.scrollAndClickOnaddmore();
            PI.Addproduct();
        } catch (Exception e) {
            Assert.fail("PI creation failed  " + e);
        }
        log.info("Finished TC_6_PICreation");
    }
}
