package com.arbostar.automation.web.ui.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientsListScreen extends AbstractScreen {

    public ClientsListScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("div#client-list-section"));
    }

    @Override
    public AbstractScreen openScreen() {
        return null;
    }

    @Override
    public AbstractScreen waitScreenOpen() {
        return null;
    }

    @Override
    public boolean isScreenOpen() {
        return false;
    }
}