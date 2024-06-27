package org.example.practice.impls.transfer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class UserAccount {

    private final String accountId;
    private BigDecimal balance;

    public UserAccount(String accountId, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.balance = createInitialBalance(initialBalance);
    }

    private BigDecimal createInitialBalance(BigDecimal initialBalance) {
        validatePositiveAmount(initialBalance);
        return initialBalance.setScale(2, RoundingMode.FLOOR);
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
        return balance;
    }

    public BigDecimal withdraw(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.subtract(amount);
        return balance;
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if(Objects.nonNull(amount) && amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
    }
}
