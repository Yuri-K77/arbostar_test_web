package com.arbostar.automation.web.ui.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardScreen extends AbstractScreen{

    public DashboardScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriver().findElement(By.cssSelector("section#content section.hbox.stretch"));
    }

    @Override
    public DashboardScreen openScreen() {
        return new LoginScreen(webActionManager.getDriver()).waitScreenOpen().openDashboardScreen().waitScreenOpen();
    }

    @Override
    public DashboardScreen waitScreenOpen() {
        webActionManager.waitGetVisibleElement(getContainer());
        return this;
    }

    @Override
    public boolean isScreenOpen() {
        return webActionManager.isElementDisplayed(getContainer());
    }
}