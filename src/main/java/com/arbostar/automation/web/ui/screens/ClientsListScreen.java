package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import com.arbostar.automation.web.enums.LeftSideNavMenuNestedItems;
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
    public ClientsListScreen openScreen() {
        new DashboardScreen(getDriverFromAbstractScreen()).openScreen();
        new LeftSideNavigationMenu(getDriverFromAbstractScreen()).selectLeftSideNavMenuItem(LeftSideNavMenuItems.CLIENTS);
        new LeftSideNavigationMenu(getDriverFromAbstractScreen()).selectLeftSideNavMenuNestedItem(LeftSideNavMenuNestedItems.CLIENTS_LIST);
        return waitScreenOpen();
    }

    @Override
    public ClientsListScreen waitScreenOpen() {
        webActionManager.waitGetVisibleElement(getContainer());
        return this;
    }

    @Override
    public boolean isScreenOpen() {
        return webActionManager.isElementDisplayed(getContainer());
    }
}