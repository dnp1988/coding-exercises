package org.example.practice.impls.transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MoneyTransferManagerTest {

    private final MoneyTransferManager manager = new MoneyTransferManager();

    @Test
    public void testTransfer() {
        UserAccount userAccount1 = new UserAccount("user1", new BigDecimal("1020.235"));
        UserAccount userAccount2 = new UserAccount("user2", new BigDecimal("1045.29"));

        manager.transfer(userAccount1, userAccount2, new BigDecimal("20.23"));

        Assertions.assertEquals(new BigDecimal("1000.00"), userAccount1.getBalance());
        Assertions.assertEquals(new BigDecimal("1065.52"), userAccount2.getBalance());
    }

    @Test
    public void testTransferMultipleThreads() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        UserAccount userAccount1 = new UserAccount("user1", new BigDecimal("1020.235"));
        UserAccount userAccount2 = new UserAccount("user2", new BigDecimal("1045.29"));

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                manager.transfer(userAccount1, userAccount2, new BigDecimal("20.23"));
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertEquals(new BigDecimal("817.93"), userAccount1.getBalance());
        Assertions.assertEquals(new BigDecimal("1247.59"), userAccount2.getBalance());
    }

}
