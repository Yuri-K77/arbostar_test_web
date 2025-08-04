package com.arbostar.automation.web.ui.screens;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import com.arbostar.automation.web.enums.LeftSideNavMenuNestedItems;
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
                .orElseThrow(() -> new NoSuchElementException("Left side navigation menu item \"" + item.getValue() + "\" is not displayed"));
    }

    public void selectLeftSideNavMenuItem(LeftSideNavMenuItems item) {
        webActionManager.clickOnElement(getLeftSideNavMenuItem(item));
    }

    private List<WebElement> getLeftSideNavMenuNestedItems() {
        return getContainer().findElements(By.cssSelector("nav.nav-primary>ul.nav>li.nav-block>ul.nav.lt>li"));
    }

    public WebElement getLeftSideNavMenuNestedItem(LeftSideNavMenuNestedItems nestedItem) {
        return getLeftSideNavMenuNestedItems()
                .stream()
                .filter(element -> element.getText().equals(nestedItem.getValue()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Left side navigation menu nested item \"" + nestedItem.getValue() + "\" is not displayed"));
    }

    public void selectLeftSideNavMenuNestedItem(LeftSideNavMenuNestedItems nestedItem) {
        webActionManager.clickOnElement(getLeftSideNavMenuNestedItem(nestedItem));
    }

    public boolean isLeftSideNavMenuExpanded() {
        return !getContainer().getAttribute("class").contains("nav-xs");
    }

    public boolean isLeftSideNavMenuCollapsed() {
        return getMinimizeSideBar().getAttribute("class").contains("active");
    }

    private WebElement getMinimizeSideBar() {
        return getContainer().findElement(By.cssSelector("a[class*='nav-footer p-10 hidden-sm']"));
    }

    public void clickToMinimizeSideBar() {
        webActionManager.clickOnElement(getMinimizeSideBar());
    }
}