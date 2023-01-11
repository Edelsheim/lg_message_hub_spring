package com.example.msghub.common.util;

import jakarta.validation.constraints.NotBlank;

public class Auth {
    public static boolean validBearer(String authToken) {
        if (authToken == null)
            return false;
        if (authToken.isBlank())
            return false;
        return authToken.matches("^Bearer .*");
    }

    public static String getBearerToken(@NotBlank String authToken) {
        if (Auth.validBearer(authToken)) {
            return authToken.replaceAll("^Bearer()*", "");
        }
        return "";
    }
}
