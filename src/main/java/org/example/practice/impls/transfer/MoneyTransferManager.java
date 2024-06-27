package org.example.practice.impls.transfer;

import java.math.BigDecimal;
import java.util.Objects;

public class MoneyTransferManager {

    public void transfer(UserAccount userAccount1, UserAccount userAccount2, BigDecimal amount) {
        validateAmount(amount);
        synchronized (userAccount1) {
            validateEnoughFunds(amount, userAccount1);
            synchronized (userAccount2) {
                userAccount1.withdraw(amount);
                userAccount2.deposit(amount);
            }
        }
    }

    private void validateAmount(BigDecimal amount) {
        if(Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0 || amount.scale() > 2) {
            throw new IllegalArgumentException();
        }
    }

    private void validateEnoughFunds(BigDecimal amount, UserAccount userAccount) {
        if(userAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException();
        }
    }

}
