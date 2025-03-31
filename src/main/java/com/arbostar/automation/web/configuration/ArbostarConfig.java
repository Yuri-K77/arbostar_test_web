package com.arbostar.automation.web.configuration;

import org.apache.commons.configuration2.Configuration;

import java.util.HashMap;
import java.util.Map;

public class ArbostarConfig {

    private static ArbostarConfig instance;
    private final Configuration configuration;
    private final Map<Long, String> sessionsId = new HashMap<>();

    public ArbostarConfig() {
        String env = System.getProperty("arbostar.env");
        if (env == null) {
            env = "staging";
        }
        configuration = new ConfigurationBuilder()
                .withEnvironmentProperties()
                .withSystemProperties()
                .withClassPathProperties("/" + env + "-config.properties")
                .build();
    }

    private static ArbostarConfig getInstance() {
        if (instance == null) {
            instance = new ArbostarConfig();
        }
        return instance;
    }

    public static String getString(String key) {
        return getInstance().configuration.getString(key);
    }

    public static void addCurrentSessionId(String sessionId) {
        getInstance().sessionsId.put(Thread.currentThread().threadId(), sessionId);
    }

    public static String getCurrentSessionId() {
        return (String) getInstance().sessionsId.get(Thread.currentThread().threadId());
    }

    public static String removeCurrentSessionId() {
        return (String) getInstance().sessionsId.remove(Thread.currentThread().threadId());
    }

    public static String getRunType() {
        return getInstance().configuration.getString("runType");
    }

    public static String getBrowserType() {
        return getInstance().configuration.getString("browserType");
    }

    public static String getBaseUrl() {
        return getInstance().configuration.getString("arbostar.url");
    }

    public static String getUser() {
        return getInstance().configuration.getString("arbostar.user");
    }

    public static String getPassword() {
        return getInstance().configuration.getString("arbostar.password");
    }

    public static String getEmail() {
        return getInstance().configuration.getString("arbostar.testEmail");
    }

    public static String getAppPassword() {
        return getInstance().configuration.getString("arbostar.appEmailPassword");
    }
}