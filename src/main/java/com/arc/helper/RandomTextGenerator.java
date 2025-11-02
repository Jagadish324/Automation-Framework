package com.arc.helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class RandomTextGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//    private static final int NAME_LENGTH = 10;

    public static String generateRandomUniqueName(int nameLength) {
        StringBuilder randomName = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < nameLength; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomName.append(randomChar);
        }


        return randomName.toString();
    }

    public static String uniqueEmail() {
        return RandomStringUtils.randomAlphabetic(8) + "_test@gmail.com";
    }

    public static String uniqueClientId() {
        return UUID.randomUUID() + "";
    }

    public static String uniquePhoneNumber() {
        Random r = new Random();
        int i1 = r.nextInt(8);
        int i2 = r.nextInt(8);
        int i3 = r.nextInt(8);
        int i4 = r.nextInt(742);
        int i5 = r.nextInt(10000);
        return String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
    }

}
