package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class VendorCreationPage extends BasePage {
    public VendorCreationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/ul/li[4]/div")
    WebElement Purchase;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/ul/div/div/div/ul[1]/a/li/div/div/span")
    WebElement VendorInNav;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div[1]/div/div[2]/div/button")
    WebElement VendorNewBtn;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/label[1]/span[1]/span[1]")
    WebElement VendorImgIcon;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[2]/div/div[1]/label/input")
    WebElement UploadVendorImg;
    @FindBy(xpath = " //*[@id=\"app\"]/div[2]/div[3]/div/div/div[1]/div/div[2]/div/div[1]")
    WebElement VendorType;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[1]/div/div[2]/div[2]/div/div[4]")
    WebElement Retailer_VendorType;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]")
    WebElement Business_Location;
    @FindBy(xpath = "//*[contains(text(), 'Tamil Nadu')]")
    WebElement TamilNadu_Business_Location;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div[1]/div/input")
    WebElement PAN;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div[2]/button")
    WebElement PanVerifyButton;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[1]/div/div[8]/span/div/input")
    WebElement Displayname;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[1]/div/div[9]/span/div/input")
    WebElement website;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[3]/div/div/div/div/div[2]/div/div[1]/div/input")
    WebElement ContactName;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[3]/div/div/div/div/div[2]/div/div[2]/div/input")
    WebElement VendorNumber;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[3]/div/div/div/div/div[2]/div/div[3]/div/input")
    WebElement VendorEmail;
    @FindBy(xpath = " /html/body/div/div[2]/div[3]/div/div/div[4]/div[1]/div/div[2]/div[2]/div[2]/div/div[1]")
    WebElement Placeofsupply;
    @FindBy(xpath = " /html/body/div/div[2]/div[3]/div/div/div[4]/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div[33]")
    WebElement Place;
    @FindBy(xpath = "/html/body/div/div[2]/div[3]/div/div/div[4]/div[3]/div/button[2]")
    WebElement Save;

    public void Findpurchase(){
        Purchase.click();
    }
    public void FindVendor(){
        Purchase.click();
        retryFindingElement(driver,VendorInNav);
        VendorInNav.click();
        VendorNewBtn.click();
    }
    public void VendorLogoUpload() throws AWTException {
        VendorImgIcon.click();
        String filePath = "\"C:\\Users\\Twinleaves Tech\\Downloads\\Amma super store.jpg\"";
        handleFileDialog(filePath);
    }
    public void BasicDetails(String PANnum){
        VendorType.click();
        Retailer_VendorType.click();
        Business_Location.click();
        TamilNadu_Business_Location.click();
        PAN.sendKeys(PANnum);
        PanVerifyButton.click();
        Displayname.sendKeys("Amma Super Store");
        website.sendKeys("www.ammasuperstore.com");
        ContactName.sendKeys("Vikram");
        VendorNumber.sendKeys("7896574567");
        VendorEmail.sendKeys("vikram@gmail.com");
        Placeofsupply.click();
        Place.click();
    }
    private static void handleFileDialog(String filePath) throws AWTException {
        StringSelection stringSelection = new StringSelection(filePath);  // Create a StringSelection object with the file path
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); // Set the clipboard contents with the file path

        Robot robot = new Robot();    // Create a Robot object to simulate keyboard and mouse actions
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static WebElement retryFindingElement(WebDriver driver,WebElement VendorInNav) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);

        return wait.until(driver1 -> {
            try {
                return ExpectedConditions.elementToBeClickable(VendorInNav).apply(driver1);
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                return null;
            }
        });
    }
}
