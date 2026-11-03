package testCases;

import io.restassured.RestAssured;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC_1_LoginTest extends BaseClass
{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TC_1_LoginTest.class);

    @Test(groups = {"Sanity", "Master"})
    public void RmsLogin() {
        log.info("Starting TC_1_LoginTest");

        try {
            LoginPage lp = new LoginPage(driver);


            lp.setMobile(rb.getString("mobile")); // valid email, get it from properties file

            lp.clickSendOtp();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            String apiResponse = captureAPIResponse();// Assume this method makes the API call
            String otp = extractOTPFromResponse(apiResponse);

//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            lp.enterOtp(driver, otp);
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            lp.clickVerifyOtp();

//            TC_002_OrgSelectTest tc002OrgSelectTest = new TC_002_OrgSelectTest();
//            tc002OrgSelectTest.test_OrgSelect();

        } catch (Exception e) {
            Assert.fail("login failed");
        }
        log.info(" Finished TC_1_LoginTest");

    }


    public static String captureAPIResponse() {
        return RestAssured
                .given()
                .contentType("application/json; charset=UTF-8")
                .body("{\n" +
                        "    \"mobile\": \"8531821606\"\n" +
                        "}")// Specify the desired charset
                .when()
                .post("https://stage-highway.palletnow.co/proxy/auth/erp/user/login")
                .then()
                .extract().asString();
    }

    public static String extractOTPFromResponse(String apiResponse) {
        // Assume that the OTP is present in the JSON response; replace this with your actual extraction logic
        return apiResponse.split("\"otp\":")[1].split(",")[0].replaceAll("\"", "").trim();
    }
}
