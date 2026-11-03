    package testCases;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.testng.Assert;
    import org.testng.annotations.Test;
    import pageObjects.OrgSelectPage;
    import testBase.BaseClass;

    import java.time.Duration;

    public class TC_2_OrgSelectTest extends TC_1_LoginTest {
        private static final Logger log = LoggerFactory.getLogger(TC_2_OrgSelectTest.class);

        @Test(groups = {"Sanity", "Master"}) //Step8 groups added
        public void OrgSelect() {
            log.info("Starting TC_2_OrgSelectTest");

            try {
                OrgSelectPage os = new OrgSelectPage(driver);
                os.clickLoc();
                os.scrollAndClickOnOrgLoc();
            }
            catch (Exception e)
            {
                Assert.fail("org select failed" + e);
            }
            log.info("Finished TC_2_OrgSelectTest");
        }

    }
