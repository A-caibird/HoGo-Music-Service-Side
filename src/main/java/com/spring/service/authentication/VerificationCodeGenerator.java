package com.spring.service.authentication;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Component
public class VerificationCodeGenerator {
    private static Map<String, String> verificationCodes = new HashMap<>();

    public static String generateVerificationCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }
        String code = codeBuilder.toString();
        return code;
    }

    public static void saveVerificationCode(String key, String code) {
        verificationCodes.put(key, code);
    }

    public static String getVerificationCode(String key) {
        return verificationCodes.get(key);
    }

    public static void deleCode(String key) {
        verificationCodes.remove(key);
    }
}