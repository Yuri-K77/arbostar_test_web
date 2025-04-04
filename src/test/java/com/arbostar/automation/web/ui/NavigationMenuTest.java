package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.enums.MainLeftNavBarItems;
import com.arbostar.automation.web.ui.screens.DashboardScreen;
import com.arbostar.automation.web.ui.screens.LeftSideNavigationMenu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigationMenuTest extends BaseUiTest {

    private LeftSideNavigationMenu leftSideNavigationMenu;

    @BeforeAll
    void beforeAll() {
        new DashboardScreen(driver).openScreen();
        leftSideNavigationMenu = new LeftSideNavigationMenu(driver);
    }

    @DisplayName("After click on left side menu item it should be expanded")
    @ParameterizedTest
    @EnumSource(MainLeftNavBarItems.class)
    void navMenuItemsShouldBeOpened(MainLeftNavBarItems enumItem) {
        leftSideNavigationMenu.selectMainLeftNavBarItem(enumItem);
    }
}