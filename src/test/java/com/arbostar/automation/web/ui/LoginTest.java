package com.arbostar.automation.web.ui;

import com.arbostar.automation.web.ui.screens.LoginScreen;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.arbostar.automation.web.configuration.ArbostarConfig.*;
import static com.arbostar.automation.web.utils.DataGenerator.randomString;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseUiTest {

    private LoginScreen loginScreen;
    private final String userName = getUser();
    private final String userPassword = getPassword();

    @BeforeAll
    void beforeAll() {
        loginScreen = new LoginScreen(driver);
    }

    @DisplayName("Negative cases for login")
    @Order(1)
    @ParameterizedTest
    @MethodSource("setOfInvalidFields")
    void inputIncorrectFieldValueDuringLogin(String invalidUserName, String invalidPassword) {
        loginScreen.waitScreenOpen()
                .inputUsername(invalidUserName)
                .inputPassword(invalidPassword)
                .clickLoginButton();
        Assertions.assertTrue(loginScreen.waitIsScreenOpen());
    }

    static Stream<Arguments> setOfInvalidFields() {
        return Stream.of(
                Arguments.of("", getPassword()),
                Arguments.of(getUser(), ""),
                Arguments.of(getUser() + "aaa", getPassword()),
                Arguments.of(getUser(), getPassword().toUpperCase()),
                Arguments.of(randomString(6), randomString(10)));
    }

    @DisplayName("After successful login, Dashboard screen should be open")
    @Test
    void login() {
        loginScreen
                .waitScreenOpen()
                .inputUsername(userName)
                .inputPassword(userPassword)
                .clickLoginButton();
    }
}