package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PICreationPage extends BasePage
{
    public PICreationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]/span[1]")
    WebElement Purchase;
    @FindBy(xpath = "//span[normalize-space()='Purchase indent']")
    WebElement PurchaseIndent;
    @FindBy(xpath = "//button[normalize-space()='Indent']")
    WebElement PINewBtn;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    WebElement choosevendortype;
    @FindBy(xpath = "//label[normalize-space()='Name']")
    WebElement selectvendortypedropdown;
    @FindBy(xpath = "//input[@type='text' and @role='combobox' and @aria-autocomplete='list' and @aria-haspopup='true' and @aria-expanded='false' and @aria-describedby='react-select-6-placeholder']")
    WebElement selectvendor;
    @FindBy(xpath = "//div[@class='css-1g3auwj-option' and text()='Krishna Manufacturer']")
    WebElement selectvendordropdown;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]")
    WebElement approverInput;
    @FindBy(xpath = "//label[text()='vanitha Pallet']")
    WebElement approver;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]")
    WebElement selectAssignee;
    @FindBy(xpath = "//label[text()='vanitha Pallet']")
    WebElement Assignee;
    @FindBy(xpath = "//button[@aria-label='Choose date']//*[name()='svg']")
    WebElement PiDate;
    @FindBy(xpath = "//button[normalize-space()='25']")
    WebElement PiExpectedDeliveryDate;
    @FindBy(xpath = "//label[normalize-space()='+ Add More']")
    WebElement addmorebtn;
    @FindBy(xpath = "//input[@type='number' and @role='combobox']")
    WebElement barcodebtn;
    @FindBy(xpath = "//li[@role='option' and text()='5555500111862']")
    WebElement product;
    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    WebElement confirmproduct;
    @FindBy(xpath = "//input[@id='quantityInput-0']")
    WebElement product_quantity;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement save;
    public void FindPurchaseIndent() throws InterruptedException
    {
        scrollUntilElementVisible(Purchase);
        clickWithRetry(Purchase);
        Thread.sleep(3000);
        scrollUntilElementVisible(PurchaseIndent);
        PurchaseIndent.click();
        PINewBtn.click();
    }
    public void AddPiDetails() throws InterruptedException
    {
        choosevendortype.click();
        selectvendortypedropdown.click();
        selectvendor.click();
        selectvendordropdown.click();
        approverInput.click();
        approverInput.sendKeys("vanitha");
        approver.click();
        selectAssignee.click();
        selectAssignee.sendKeys("vanitha");
        Assignee.click();
        PiDate.click();
//        scrollUntilElementVisible(PiExpectedDeliveryDate);
        Thread.sleep(2000);
        PiExpectedDeliveryDate.click();
        scrollUntilElementVisible(addmorebtn);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", addmorebtn);
//        js.executeScript("arguments[0].click();", addmorebtn);

        //addmorebtn.click();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", addmorebtn);
//        addmorebtn.click();

    }

//    public void scrollAndClickOnaddmore() throws InterruptedException {
//        //waitForOverlayToDisappear("//label[normalize-space()='+ Add More']");
//        scrollUntilElementVisible(addmorebtn);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", addmorebtn);
//        robustClick(addmorebtn);
//    }

    public void Addproduct() throws InterruptedException
    {
        //addmorebtn.click();
        scrollUntilElementVisible(barcodebtn);
        barcodebtn.sendKeys("5555500111862");
        product.click();
       // confirmproduct.click();
        // Handle optional confirm pop-up
        // Check if Confirm button is displayed
        if (isElementDisplayed(confirmproduct)) {
            confirmproduct.click();
        } else {
            System.out.println("Confirm button not displayed, skipping...");
        }
        Thread.sleep(2000);
        scrollUntilElementVisible(product_quantity);
        product_quantity.sendKeys("5");
        scrollUntilElementVisible(save);

    }


}
