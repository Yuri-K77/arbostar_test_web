package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class LeftSideNavigationMenu extends AbstractScreen {

    public LeftSideNavigationMenu(WebDriver driver) {
        super(driver);
    }

    public WebElement getContainer() {
        return getDriverFromAbstractScreen().findElement(By.cssSelector("aside#nav"));
    }

    private List<WebElement> getLeftSideNavMenuItems() {
        return getContainer().findElements(By.cssSelector("nav.nav-primary>ul.nav>li.nav-block>a span.nav-name"));
    }

    public WebElement getLeftSideNavMenuItem(LeftSideNavMenuItems item) {
        return getLeftSideNavMenuItems()
                .stream()
                .filter(element -> element.getText().equals(item.getValue()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Navigation bar item \"" + item.getValue() + "\" is not displayed"));
    }

    public void selectLeftSideNavMenuItem(LeftSideNavMenuItems item) {
        webActionManager.clickOnElementWithPollingInterval(getLeftSideNavMenuItem(item));
    }

    public boolean isLeftSideNavMenuExpanded() {
        return !getContainer().getAttribute("class").contains("nav-xs");
    }

    public boolean isLeftSideNavMenuCollapsed() {
        return getMinimizeSideBar().getAttribute("class").contains("active");
    }

    public WebElement getMinimizeSideBar() {
        return getContainer().findElement(By.cssSelector("a[class*='nav-footer p-10 hidden-sm']"));
    }

    public void clickToMinimizeSideBar() {
        webActionManager.clickOnElement(getMinimizeSideBar());
    }
}