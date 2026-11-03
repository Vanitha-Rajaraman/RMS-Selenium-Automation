package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrgSelectPage extends BasePage
{
    @FindBy(xpath = "//button[normalize-space()='Locations']")
    private WebElement SelectLoc;
    @FindBy(xpath = "//div[contains(text(),'Erode')]")
    private WebElement OrgLoc;
    public OrgSelectPage(WebDriver driver) {
        super(driver);
    }
    public void clickLoc()
    {
        SelectLoc.click();
    }
    public void scrollAndClickOnOrgLoc() throws InterruptedException {
        scrollUntilElementVisible(OrgLoc);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", OrgLoc);
    }

}
