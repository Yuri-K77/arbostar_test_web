package com.arbostar.automation.web.ui.driver;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import com.arbostar.automation.web.utils.DataGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class DriverProvider {

    private RemoteWebDriver driver;
    private final static String BROWSERSTACK_BUILD_NO = "BuildNo: " + DataGenerator.getCurrentTimeInMillis();

    @SneakyThrows
    public void setupDriver() {
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("projectName", ArbostarConfig.getString("bs.projectName"));
        browserstackOptions.put("buildName", BROWSERSTACK_BUILD_NO);
        browserstackOptions.put("sessionName", "Regression test");
        browserstackOptions.put("buildTag", "regression");

        if (ArbostarConfig.getBrowserType().equalsIgnoreCase(Browser.CHROME.name())) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 1);
            prefs.put("intl.accept_languages", "ru,ru_RU");
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //options.addArguments("--headless=new");
            options.setCapability("bstack:options", browserstackOptions);
            options.addArguments("--remote-allow-origins=*");
            //options.setLogLevel(ChromeDriverLogLevel.ALL); //запуск подробного логгирования
            initWebDriver(options);
        } else {
            System.setProperty("browserType", Browser.FIREFOX.name().toLowerCase());
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("--headless=new");
            options.addPreference("intl.accept_languages", "ru-RU");
            options.setCapability("bstack:options", browserstackOptions);
            initWebDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    private <T extends AbstractDriverOptions> void initWebDriver(AbstractDriverOptions<T> options) {
        if (ArbostarConfig.getRunType().equals("localrun")) {
            if (ArbostarConfig.getBrowserType().equalsIgnoreCase(Browser.CHROME.name())) {
                driver = new ChromeDriver((ChromeOptions) options);
            } else if (ArbostarConfig.getBrowserType().equalsIgnoreCase(Browser.FIREFOX.name())) {
                driver = new FirefoxDriver((FirefoxOptions) options);
            }
        } else {
            driver = new RemoteWebDriver(new URL("http://"
                    + ArbostarConfig.getString("bs.userName")
                    + ":"
                    + ArbostarConfig.getString("bs.accessKey")
                    + "@"
                    + ArbostarConfig.getString("bs.server") + "/wd/hub"), options);
        }
        ArbostarSeleniumWebDriverProvider.setWebDriver(driver);
        ArbostarConfig.addCurrentSessionId(driver.getSessionId().toString());
    }

    public void quitDriver() {
        ArbostarSeleniumWebDriverProvider.getWebDriver().quit();
    }

    enum Browser {
        CHROME,
        FIREFOX
    }
}