package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class InwardGRNCreationPage extends BasePage
{

    public InwardGRNCreationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/div[1]/span[1]")
    WebElement Purchase;
    @FindBy(xpath = "//span[normalize-space()='Purchase orders']")
    WebElement PurchaseOrder;
    @FindBy(xpath = "//div[contains(text(),'PO001964')]")
    WebElement PurchaseOrderDetailsPage;
    @FindBy(xpath = "//div[@class='menu-icon-div']//*[name()='svg']")
    WebElement kebabMenu;
    @FindBy(xpath = "//li[contains(text(),'Inward (GRN)')]")
    WebElement InwardGRN;
    @FindBy(xpath = "(//input[@type='text' and contains(@class,'MuiInputBase-input')])[2]")
    WebElement InvoiceNumber ;
    @FindBy(xpath = "(//input[@type='date' and contains(@class,'MuiInputBase-input')])[1]]")
    WebElement InvoiceDate ;
    @FindBy(xpath = "(//input[@type='number' and contains(@class,'MuiInputBase-input')])[1]")
    WebElement InvoiceBillValue;
    @FindBy(xpath = "(//input[@type='text' and @role='combobox'])[1]")
    WebElement AddApprover;
    @FindBy(xpath = "//label[text()='vanitha Pallet']")
    private WebElement addapproverdropdown;
    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement save;




    public void CreatePO() throws InterruptedException {
        Purchase.click();
        PurchaseOrder.click();
        // Double-click instead of normal click
        Actions actions = new Actions(driver);
        actions.doubleClick(PurchaseOrderDetailsPage).perform();
        kebabMenu.click();
        InwardGRN.click();
        InvoiceNumber.sendKeys("INV12345");
        Thread.sleep(3000);
        InvoiceDate.click();
        InvoiceDate.clear();
        InvoiceDate.sendKeys("09/28/2025");
        InvoiceBillValue.sendKeys("50000");
        AddApprover.sendKeys("vanitha");
        addapproverdropdown.click();
        save.click();
    }
}
