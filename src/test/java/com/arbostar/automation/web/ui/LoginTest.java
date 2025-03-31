package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import com.arbostar.automation.web.ui.screens.LoginScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseUiTest {

    private final String userName = ArbostarConfig.getUser();
    private final String userPassword = ArbostarConfig.getPassword();

    @DisplayName("After successful login, Dashboard screen should be open")
    @Test
    void login() {
        new LoginScreen(driver)
                .waitIsScreenOpen()
                .inputUsername(userName)
                .inputPassword(userPassword)
                .clickLoginButton();
    }
}