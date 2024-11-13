package com.wanted.clone.oneport.core.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class IdGenerator {
    private final static String CHARS = "0123456789";
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();


    public static String generateId(int length) {
        String yyyyMMdd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomNumber = makeConcurrentRandomNumber(length - yyyyMMdd.length());
        return yyyyMMdd + randomNumber;
    }

    private static String makeConcurrentRandomNumber(int numberLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberLength; i++) {
            int randomNumber = getRandomOnlyOneNumber();
            sb.append(randomNumber);
        }
        return sb.toString();
    }

    private static int getRandomOnlyOneNumber() {
        return random.nextInt(10);
    }
}
