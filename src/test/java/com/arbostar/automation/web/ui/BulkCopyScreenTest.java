package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.BulkCopyScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BulkCopyScreenTest extends BaseUiTest {

    private BulkCopyScreen bulkCopyScreen;

    @BeforeAll
    void beforeAll() {
        bulkCopyScreen = new BulkCopyScreen(driver);
    }

    @DisplayName("<Bulk Copy> screen should be displayed after opening")
    @Order(1)
    @Test
    void openBulkCopyScreen() {
        bulkCopyScreen.openScreen();
        Assertions.assertTrue(bulkCopyScreen.isScreenOpen());
    }
}