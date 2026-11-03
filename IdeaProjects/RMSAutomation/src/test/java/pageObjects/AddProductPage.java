package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddProductPage extends BasePage {

    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    // --- Web Elements ---

    // Note: Using formcontrolname is a good practice for Angular applications.
    // These XPaths are based on common patterns and may need to be verified against the actual page source.

    @FindBy(xpath = "//input[@formcontrolname='productName']")
    private WebElement productNameInput;

    @FindBy(xpath = "//input[@formcontrolname='sku']")
    private WebElement productSkuInput;

    @FindBy(xpath = "//input[@formcontrolname='price']")
    private WebElement priceInput;

    @FindBy(xpath = "//textarea[@formcontrolname='description']")
    private WebElement descriptionTextArea;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[normalize-space()='Save & Add Another']")
    private WebElement saveAndAddAnotherButton;

    // --- Page Methods ---

    public void setProductName(String productName) {
        productNameInput.clear();
        productNameInput.sendKeys(productName);
    }

    public void setProductSku(String sku) {
        productSkuInput.clear();
        productSkuInput.sendKeys(sku);
    }

    public void setPrice(String price) {
        priceInput.clear();
        priceInput.sendKeys(price);
    }

    public void setDescription(String description) {
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys(description);
    }

    public void clickSave() {
        saveButton.click();
    }
    
    public void clickSaveAndAddAnother() {
        saveAndAddAnotherButton.click();
    }

    /**
     * Fills out the entire add product form.
     * @param productName The name of the product.
     * @param sku The SKU of the product.
     * @param price The price of the product.
     * @param description The description of the product.
     */
    public void addProduct(String productName, String sku, String price, String description) {
        setProductName(productName);
        setProductSku(sku);
        setPrice(price);
        setDescription(description);
        clickSave();
    }
}
