package com.arbostar.automation.web.ui.service;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

public class BrowserStackClient extends BrowserStackBaseClient {

    private static final String PLAN_ENDPOINT = "/automate/plan.json";
    private static final String BUILD_ENDPOINT = "/automate/builds.json";
    private static final String SESSION_LIST_ENDPOINT = "/automate/builds/{build-id}/sessions.json";
    private static final String SESSION_DETAILS_ENDPOINT = "/automate/sessions/{session-id}.json";
    private static final String SESSION_LOGS_ENDPOINT = "/automate/sessions/{session-id}/logs";
    private static final String SESSION_NETWORK_LOGS_ENDPOINT = "/automate/sessions/{session-id}/networklogs";
    private static final String SESSION_CONSOLE_LOGS_ENDPOINT = "/automate/sessions/{session-id}/consolelogs";
    private static final String SESSION_SELENIUM_LOGS_ENDPOINT = "/automate/sessions/{session-id}/seleniumlogs";

    private final String sessionId;

    public BrowserStackClient(String sessionId) {
        this.sessionId = sessionId;
    }

    public static String getPlan() {
        return sendGet(PLAN_ENDPOINT).extract().body().jsonPath().prettyPrint();
    }

    public static JsonPath getBuilds() {
        return sendGet(BUILD_ENDPOINT).extract().body().jsonPath();
    }

    public String getSessions() {
        return sendGet(SESSION_LIST_ENDPOINT).extract().body().jsonPath().prettyPrint();
    }

    public String getSessionDetails() {
        return sendGet(SESSION_DETAILS_ENDPOINT, sessionId).extract().body().jsonPath().prettyPrint();
    }

    public String getSessionLog() {
        return sendGet(SESSION_LOGS_ENDPOINT, sessionId).extract().body().asString();
    }

    public String getSessionNetworkLog() {
        return sendGet(SESSION_NETWORK_LOGS_ENDPOINT, sessionId).extract().body().asString();
    }

    public String getSessionConsoleLog() {
        return sendGet(SESSION_CONSOLE_LOGS_ENDPOINT, sessionId).extract().body().asString();
    }

    public String getSessionSeleniumLog() {
        return sendGet(SESSION_SELENIUM_LOGS_ENDPOINT, sessionId).extract().body().asString();
    }

    public String updateSessionName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return sendPut(SESSION_DETAILS_ENDPOINT, params, sessionId).extract().body().jsonPath().prettyPrint();
    }

    public String updateSessionStatus(SessionStatus status, String reason) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        if (reason != null && !reason.isEmpty()) {
            params.put("reason", reason);
        }

        return sendPut(SESSION_DETAILS_ENDPOINT, params, sessionId).extract().body().jsonPath().prettyPrint();
    }

    public String updateSessionStatusFailed(String reason) {
        return updateSessionStatus(SessionStatus.FAILED, reason);
    }

    public String updateSessionStatusPassed(String reason) {
        return updateSessionStatus(SessionStatus.PASSED, reason);
    }
}