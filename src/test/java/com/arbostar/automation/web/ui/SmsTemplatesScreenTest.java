package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.SmsTemplatesScreen;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmsTemplatesScreenTest extends BaseUiTest {

    private SmsTemplatesScreen smsTemplatesScreen;

    @BeforeAll
    void beforeAll() {
        smsTemplatesScreen = new SmsTemplatesScreen(driver);
    }

    @DisplayName("<SMS Templates> screen should be displayed after opening")
    @Order(1)
    @Test
    void openSmsTemplatesScreen() {
        smsTemplatesScreen.openScreen();
        Assertions.assertTrue(smsTemplatesScreen.isScreenOpen());
    }
}