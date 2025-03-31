package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginScreen extends AbstractScreen {

    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginScreen waitIsScreenOpen() {
        webActionManager.waitGetVisibleElement(getLoginButton());
        return this;
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
        return webActionManager.getDriver().findElement(By.cssSelector("error_message"));
    }

    public LoginScreen inputUsername(String data) {
        getUsernameField().clear();
        getUsernameField().sendKeys(data);
        return this;
    }

    public LoginScreen inputPassword(String data) {
        getPasswordField().clear();
        getPasswordField().sendKeys(data);
        return this;
    }

    public void clickLoginButton() {
        webActionManager.waitGetClickableElement(getLoginButton()).click();
    }

    public boolean isErrorMessageDisplayed() {
        return webActionManager.waitIsElementVisible(getLoginError());
    }
}