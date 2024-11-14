package com.wanted.clone.oneport.core.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class IdGeneratorTests {
    @Test
    public void generateId_NormalTest_True() {
        String ids = IdGenerator.generateId(14);
        String idRegex = "\\d{4}\\d{2}\\d{2}\\d{6}";
        Assertions.assertTrue(ids.matches(idRegex));
    }

    @Test
    public void generateId_ConcurrentTest_returnAllDiffIds() throws InterruptedException {
        int threadCount = 80;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<String> generatedIds = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    String orderId = IdGenerator.generateId(14);
                    log.info("Generated ID ::: {}", orderId);
                    generatedIds.add(orderId);
                    //generatedIds.add(IdGenerator.generateId(14));
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        List<String> notDuplicatedIds = new ArrayList<>();
        for (String generatedId : generatedIds) {
            if (!notDuplicatedIds.contains(generatedId))
                notDuplicatedIds.add(generatedId);
            else Assertions.fail("Duplicated generated id: " + generatedId);
        }

        String idRegex = "\\d{4}\\d{2}\\d{2}\\d{6}";
        for (String notDuplicatedId : notDuplicatedIds) {
            Assertions.assertTrue(notDuplicatedId.matches(idRegex));
        }

    }

}
