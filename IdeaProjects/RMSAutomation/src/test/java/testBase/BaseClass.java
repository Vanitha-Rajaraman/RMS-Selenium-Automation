package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseClass {

    public static WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public Actions actions;
    public ResourceBundle rb;

    @Parameters("browser")
    @BeforeClass(groups = {"Master", "Sanity", "Regression"})
    public void setup(@Optional("chrome") String br) {
        rb = ResourceBundle.getBundle("config", Locale.getDefault());

        switch (br.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + br);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // default timeout
        driver.manage().window().maximize();
        driver.get(rb.getString("appURL"));

//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        js = (JavascriptExecutor) driver;
//        actions = new Actions(driver);
//        PageFactory.initElements(driver, this);
    }

    @AfterClass(groups = {"Sanity", "Regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void checkResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreen(result.getName());
        }
    }

//    public String captureScreen(String tname) throws IOException {
//        String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        // Use / for portability, or use File.separator
//        String destination = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
//        File destFile = new File(destination);
//        FileUtils.copyFile(source, destFile);
//        return destination;
//    }
    public static String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

        // Ensure the screenshots directory exists
        File screenshotsDir = new File(System.getProperty("user.dir") + File.separator + "screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String destination = screenshotsDir.getAbsolutePath() + File.separator + tname + "_" + timeStamp + ".png";
        File destFile = new File(destination);

        try {
            if (driver == null) {
                System.out.println("Driver is null. Cannot capture screenshot.");
                return null;
            }
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, destFile);
        } catch (Exception e) {
            System.err.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace();
            return null; // Return null on failure
        }
        return destination;
    }

    public void waitForPageLoad() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        System.out.println("Page is completely loaded.");
    }

    public void sendKeysWithRetry(WebElement element, String value) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                WebElement freshElement = waitForElementToBeVisible(element);
                js.executeScript("arguments[0].scrollIntoView(true);", freshElement);
                waitForStability(300);

                String script = "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
                js.executeScript(script, freshElement, value);
                waitForStability(500);

                String currentValue = freshElement.getAttribute("value").trim();
                if (!currentValue.equals(value)) {
                    throw new RuntimeException("Value mismatch. Expected: " + value + ", Found: " + currentValue);
                }
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Retrying...");
            } catch (Exception e) {
                System.out.println("SendKeys failed: " + e.getMessage());
            }
            attempts++;
        }
        throw new RuntimeException("Failed to send keys after retries: " + element);
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForStability(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

//    public void scrollUntilElementVisible(WebElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", element);
//        js.executeScript("arguments[0].click();", element);
//    }
    public void scrollUntilElementVisible(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", element);
    }

//    public void scrollElementIntoViewFromBottom(WebElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(false);", element);
//        js.executeScript("arguments[0].click();", element);
//    }

    public void clickWithRetry(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                scrollUntilElementVisible(element);
                waitForElementToBeClickable(element).click();
                return;
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException, retrying... Attempt: " + (attempts + 1));
            } catch (Exception e) {
                System.out.println("Retrying click due to: " + e.getMessage() + " Attempt: " + (attempts + 1));
            }
            waitForStability(500);
            attempts++;
        }
        throw new RuntimeException("Failed to click element after retries: " + element);
    }

    //    public void forceClick(WebElement element) {
//        try {
//            js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
//            waitForStability(300);
//            js.executeScript("arguments[0].click();", element);
//        } catch (Exception e) {
//            throw new RuntimeException("JS click failed for element: " + element, e);
//        }
//    }
    public void robustClick(WebElement elem) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elem));
            js.executeScript("arguments[0].scrollIntoView(true);", elem);
            elem.click();
        } catch (ElementClickInterceptedException ex) {
            js.executeScript("arguments[0].click();", elem);
        }
    }

    public void waitForOverlayToDisappear(String overlayXPath) {
        try {
            WebDriverWait overlayWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(overlayXPath)));
        } catch (TimeoutException ignored) {
        }
    }

    // Utility method to check if element is displayed
    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    // Wait for AJAX (jQuery) to complete, optional
    public void waitForAjax() {
        wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return window.jQuery != undefined && jQuery.active === 0"));
    }
}
