package org.example;

import java.io.IOException;

//Factory class (Factory pattern)
public class BankServiceFactory {

    public BalanceInquiry createBalanceInquiry() throws IOException {
        return new BalanceInquiry();
    }

    public Login createLogin() throws IOException {
        return new Login();
    }

    public Transaction createTransaction() throws IOException {
        return new Transaction();
    }

    public AccountDetails createAccountDetails() throws IOException {
        return new AccountDetails();
    }

    public BankStatement createBankStatement() throws IOException {
        return new BankStatement();
    }


    public Deletion createDeletion() throws IOException {
        return new Deletion();
    }

    public Creation createCreation() throws IOException {
        return new Creation();
    }

}
