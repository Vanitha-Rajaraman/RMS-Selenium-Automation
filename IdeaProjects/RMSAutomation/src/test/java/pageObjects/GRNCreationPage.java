package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GRNCreationPage extends BasePage
{
    public GRNCreationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]/span[1]")
    WebElement Purchase;
    @FindBy(xpath = "//span[normalize-space()='Inward (GRN)']")
    private WebElement GRN;
    @FindBy(xpath = "//button[normalize-space()='New']")
    private WebElement createGRN;
    @FindBy(xpath = "(//input[starts-with(@id,'react-select-') and contains(@id,'-input')])[1]")
    private WebElement vendorby;
    @FindBy(xpath = "//div[contains(@class,'option')]//label[normalize-space()='Name']")
    private WebElement vendorbydropdown;
    @FindBy(xpath = "(//input[@role='combobox' and @type='text'])[2]")
    private WebElement searchbyname;
    @FindBy(xpath = "//div[contains(@class,'option') and normalize-space()='Krishna Manufacturer']")
    private WebElement searchbynamedropdown;
    @FindBy(xpath = "//div[@class='MuiBox-root css-91otsb']//div[@class='MuiInputBase-root MuiInputBase-colorPrimary css-1i2ixsu']//input[@type='text']")
    private WebElement invoicenumber;
    @FindBy(xpath = "(//input[@type='date'])[1]")
    private WebElement invoicedate;
//    @FindBy(xpath = "")
//    private WebElement invoicedatedropdown;
    @FindBy(xpath = "(//input[@type='number'])[1]")
    private WebElement invoicebillvalue;
    @FindBy(xpath = "(//input[starts-with(@id, 'react-select') and @type='text' and @role='combobox'])[1]")
    private WebElement addapprover;
    @FindBy(xpath = "//label[text()='vanitha Pallet']")
    private WebElement addapproverdropdown;
    @FindBy(xpath = "//input[@type='number' and @role='combobox']")
    WebElement barcodebtn;
    @FindBy(xpath = "//li[@role='option' and text()='5555500111862']")
    WebElement product;
    @FindBy(xpath = "//input[@id='quantityInput-0']")
    WebElement product_quantity;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement save;


    public void Addproduct() throws InterruptedException
    {
        Purchase.click();
        GRN.click();
        createGRN.click();
        vendorby.click();
        vendorbydropdown.click();
        searchbyname.click();
        searchbynamedropdown.click();
        invoicenumber.sendKeys("INV12345");
        invoicedate.click();
        invoicedate.clear();
        invoicedate.sendKeys("09/23/2025"); // The format 'MM/dd/yyyy' is an assumption
        invoicedate.sendKeys(Keys.ENTER); // Or Keys.TAB
        invoicebillvalue.sendKeys("0");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addapprover);
        addapprover.sendKeys("vanitha");
        addapproverdropdown.click();
        barcodebtn.sendKeys("5555500111862");
        product.click();
        product_quantity.click();
        product_quantity.sendKeys("5");
        Thread.sleep(3000);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].scrollIntoView(true);", save);
        save.click();

    }


}
