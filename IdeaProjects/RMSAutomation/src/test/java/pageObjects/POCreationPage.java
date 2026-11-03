package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class POCreationPage extends BasePage
{
    public POCreationPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]/span[1]")
    WebElement Purchase;
    @FindBy(xpath = "//span[normalize-space()='Purchase indent']")
    WebElement PurchaseIndent;
    @FindBy(xpath = "//div[contains(text(),'PI11020')]")
    WebElement PurchaseIndentDetailsPage;
    @FindBy(xpath = "//div[@class='menu-icon-div']//*[name()='svg']//*[name()='path' and contains(@d,'M6 10c-1.1')]")
    WebElement kebabMenu;
    @FindBy(xpath = "//li[normalize-space()='Approve']")
    WebElement ApproveOption;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement confirmApprove;
    @FindBy(xpath = "//div[@class='menu-icon-div']//*[name()='svg']//*[name()='path' and contains(@d,'M6 10c-1.1')]")
    WebElement againkebabMenu;
    @FindBy(xpath = "//li[normalize-space()='Convert To P.O']")
    WebElement converttoPO;
    @FindBy(xpath = "//td[@class='gold-text po-item-table-body']//input[@type='checkbox']")
    WebElement clickcheckbox;
    @FindBy(xpath = "//div[contains(@class,'product-not-added')]//input[@type='number']")
    WebElement rateInput;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement save;


    public void CreatePO() throws InterruptedException
    {
        Purchase.click();
        PurchaseIndent.click();
        Thread.sleep(3000);
        // Double-click instead of normal click
        Actions actions = new Actions(driver);
        actions.doubleClick(PurchaseIndentDetailsPage).perform();
       // PurchaseIndentDetailsPage.click();
        kebabMenu.click();
        ApproveOption.click();
        confirmApprove.click();
        waitForElementToBeClickable(againkebabMenu);
        againkebabMenu.click();
        //againkebabMenu.click();
        converttoPO.click();
        //waitForElementToBeClickable(converttoPO);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(confirmApprove));
//        scrollUntilElementVisible(confirmApprove);
//        Thread.sleep(5000);
        //scrollUntilElementVisible(againkebabMenu);
        //waitForElementToBeClickable(againkebabMenu);
        //converttoPO.click();
//        scrollUntilElementVisible(clickcheckbox);
//        clickcheckbox.click();
        // Try normal click first
        try {
            clickcheckbox.click();
        } catch (Exception e) {
            // Fallback to JS click if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickcheckbox);
        }
        //waitForElementToBeClickable(rateInput);
        // Wait until rateInput is clickable
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(rateInput));
        //rateInput.sendKeys("500");
        scrollUntilElementVisible(save);
        save.click();
    }

}
