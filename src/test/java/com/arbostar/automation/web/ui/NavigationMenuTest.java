package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.enums.LeftSideNavMenuItems;
import com.arbostar.automation.web.ui.screens.DashboardScreen;
import com.arbostar.automation.web.ui.screens.LeftSideNavigationMenu;
import org.junit.jupiter.api.*;
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

    @DisplayName("Left side navigation menu should be expanded by default")
    @Test
    @Order(1)
    void leftSideNavMenuShouldBeExpandedByDefault() {
        Assertions.assertTrue(leftSideNavigationMenu.isLeftSideNavMenuExpanded());
    }

    @DisplayName("After click on left side menu item it should be expanded")
    @ParameterizedTest
    @Order(2)
    @EnumSource(LeftSideNavMenuItems.class)
    void leftSideNavMenuItemsShouldBeOpened(LeftSideNavMenuItems enumItem) {
        leftSideNavigationMenu.selectLeftSideNavMenuItem(enumItem);
    }

    @DisplayName("After click on <Minimize the sidebar> left side menu should be collapsed")
    @Test
    @Order(3)
    void leftSideNavMenuShouldBeCollapsedAfterClickMinimizeButton() {
        leftSideNavigationMenu.clickToMinimizeSideBar();
        Assertions.assertTrue(leftSideNavigationMenu.isLeftSideNavMenuCollapsed());
    }
}