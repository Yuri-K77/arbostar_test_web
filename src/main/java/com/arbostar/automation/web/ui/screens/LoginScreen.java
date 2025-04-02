package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import org.openqa.selenium.*;

public class LoginScreen extends AbstractScreen {

    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginScreen openScreen() {
        return this;
    }

    @Override
    public LoginScreen waitScreenOpen() {
        webActionManager.waitGetVisibleElement(getLoginButton());
        return this;
    }

    @Override
    public boolean isScreenOpen() {
        return webActionManager.isElementDisplayed(getLoginButton());
    }

    public DashboardScreen openDashboardScreen() {
        getUsernameField().sendKeys(ArbostarConfig.getUser());
        getPasswordField().sendKeys(ArbostarConfig.getPassword());
        webActionManager.clickOnElement(getLoginButton());
        return new DashboardScreen(webActionManager.getDriver());
    }

    private WebElement getUsernameField() {
        return webActionManager.getDriver().findElement(By.cssSelector("#username"));
    }

    private WebElement getPasswordField() {
        return webActionManager.getDriver().findElement(By.cssSelector("#password"));
    }

    private WebElement getLoginButton() {
        return webActionManager.getDriver().findElement(By.cssSelector("#submit"));
    }

    private WebElement getLoginError() {
        return webActionManager.getDriver().findElement(By.cssSelector("#error_message"));
    }

    private WebElement getReCaptchaCheckbox() {
        return webActionManager.getDriver().findElement(By.cssSelector("div.recaptcha-checkbox-checkmark"));
    }

    public LoginScreen inputUsername(String data) {
        webActionManager.inputDataInField(getUsernameField(), data);
        return this;
    }

    public LoginScreen inputPassword(String data) {
        webActionManager.inputDataInField(getPasswordField(), data);
        return this;
    }

    public void clickLoginButton() {
        webActionManager.clickOnElement(getLoginButton());
    }

    public void clickRecaptchaCheckbox() {
        webActionManager.clickOnElement(getReCaptchaCheckbox());
    }

    public boolean isErrorMessageDisplayed() {
        return webActionManager.waitIsElementVisible(getLoginError());
    }
}