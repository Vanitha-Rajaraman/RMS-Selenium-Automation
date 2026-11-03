package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Mobile number']")
    WebElement txtMobileNumber;

    public void setMobile(String mobile) {
        txtMobileNumber.sendKeys(mobile);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[2]/div/div[2]/form/div[2]/button")
    WebElement btnSentOtp;

    public void clickSendOtp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        WebElement sendotpbtn = wait.until(ExpectedConditions.elementToBeClickable(btnSentOtp));
        sendotpbtn.click();

    }

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[2]/div/div[2]/form/div[1]/div/input")
    WebElement inputOtp;

    public void enterOtp(WebDriver driver, String otp) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        WebElement otpInputField = wait.until(ExpectedConditions.visibilityOf(inputOtp));
        otpInputField.sendKeys(otp);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div[2]/div/div[2]/form/div[3]/button")
    WebElement btnverifyOtp;

    public void clickVerifyOtp() {
        btnverifyOtp.click();
    }


}
