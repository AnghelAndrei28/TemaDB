package com.dbma.thymeleaf.account.transferServices;

public interface TransferMoneyServices {
    String sendMoneyTo(String sndIban, int sum, String source);
}
