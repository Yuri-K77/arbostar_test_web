package com.arbostar.automation.web.rest;

import com.wimix.automation.zegoal.rest.auth.AuthService;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

@Tag("api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseRestTest {

    private static String accessToken;

    @Synchronized
    static String getAccessToken() {
        if (StringUtils.isBlank(accessToken)) {
            accessToken = AuthService.getAccessToken();
        }
        return accessToken;
    }

    static Map<String, Object> prepareRevokeTokenObject(String device_id, String type, String token, String client_id, String client_secret, int status_code) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("device_id", device_id);
        objectMap.put("type", type);
        objectMap.put("token", token);
        objectMap.put("client_id", client_id);
        objectMap.put("client_secret", client_secret);
        objectMap.put("status_code", status_code);
        return objectMap;
    }
}