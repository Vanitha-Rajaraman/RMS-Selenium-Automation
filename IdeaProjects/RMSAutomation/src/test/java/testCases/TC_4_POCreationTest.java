package testCases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.POCreationPage;

public class TC_4_POCreationTest extends TC_2_OrgSelectTest
{
    private static final Logger log = LoggerFactory.getLogger(TC_4_POCreationTest.class);

    @Test(groups = {"Sanity", "Master"})
    public void POCreation()
    {
        log.info("Starting TC_4_POCreationTest");
        try
        {
            POCreationPage po = new POCreationPage(driver);
            po.CreatePO();
        }
        catch(Exception e)
        {
            Assert.fail("PO Creation Failed " + e);
        }
        log.info("Finished TC_4_POCreationTest");
    }
}