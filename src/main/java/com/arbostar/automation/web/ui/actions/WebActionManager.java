package com.arbostar.automation.web.ui.actions;

import com.arbostar.automation.web.utils.PropertyLoader;
import org.awaitility.Awaitility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Класс, описывающий базовые действия над вебэлементами.
 */
public class WebActionManager {

    private final WebDriver driver;

    private final WebDriverWait driverWait;

    public static final int TIMEOUT = Integer.parseInt(PropertyLoader.getProperty("implicitlyWait"));

    public static final int TIMEOUT_LONG = Integer.parseInt(PropertyLoader.getProperty("explicitlyWait"));

    public WebActionManager(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_LONG));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getDriverWait() {
        return driverWait;
    }

    private WebElement waitGetClickableElement(WebElement element) {
        return driverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickOnElement(WebElement element) {
        waitGetClickableElement(element).click();
    }

    public void clickOnElementWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickOnElementWithPollingInterval(WebElement element) {
        Awaitility
                .given()
                .pollInterval(100, TimeUnit.MILLISECONDS)
                .atMost(2000, TimeUnit.MILLISECONDS)
                .pollInSameThread()
                .ignoreExceptions()
                .until(() -> {
                    clickOnElement(element);
                    return true;
                });
    }

    public boolean waitIsElementVisible(WebElement element) {
        try {
            driverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitElementToBeInvisible(WebElement element) {
        driverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitIsElementInvisible(WebElement element) {
        try {
            waitElementToBeInvisible(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebElement waitGetVisibleElement(WebElement element) {
        return driverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sendKeyEscapeAction() {
        WebElement currentElement = driver.switchTo().activeElement();
        currentElement.sendKeys(Keys.ESCAPE);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(By locator) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(0));
        try {
            return getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        }
    }

    public void inputDataInField(WebElement element, String text) {
        WebElement webElement = waitGetClickableElement(element);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void hoverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void dragAndDropElement(WebElement elementToBeMoved, WebElement moveTo) {
        Actions action = new Actions(driver);
        action.dragAndDrop(elementToBeMoved, moveTo).perform();
    }

    public void scrollElementDown() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }

    public void scrollElementDown(WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.keyDown(targetElement, Keys.CONTROL).sendKeys(Keys.END).perform();
    }

    public void scrollElementUp() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
    }

    public void scrollElementUp(WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.keyDown(targetElement, Keys.CONTROL).sendKeys(Keys.HOME).perform();
    }

    public WebElement scrollToTargetElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
        return element;
    }

    public void waitUntilAttributeHasValue(WebElement webElement, String attribute, String attributeValue) {
        driverWait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));
    }

    public boolean waitIsAttributeHasValue(WebElement webElement, String attribute, String attributeValue) {
        try {
            waitUntilAttributeHasValue(webElement, attribute, attributeValue);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void closeWebElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.display = 'none'", element);
    }
}