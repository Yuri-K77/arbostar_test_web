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
        return new DashboardScreen(getDriverFromAbstractScreen());
    }

    private WebElement getUsernameField() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("#username"));
    }

    private WebElement getPasswordField() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("#password"));
    }

    private WebElement getLoginButton() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("#submit"));
    }

    private WebElement getLoginError() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("#error_message"));
    }

    private WebElement getReCaptchaCheckbox() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("div.recaptcha-checkbox-checkmark"));
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