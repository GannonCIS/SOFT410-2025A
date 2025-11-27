package UnitTests;

import org.example.BalanceInquiry;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BalanceInquiryUnitTesting {
    private BalanceInquiry balanceInquiry;

    @BeforeEach
    void setup(){
        balanceInquiry = new BalanceInquiry();
    }

    @Test
    void getAccountNumberTest(){
        //account number = 1001, account balance = 1500
        String[] testData = {"1001", "1500"};

        //Actual number
        int actual = balanceInquiry.getAccountNumber(testData);

        assertEquals(1001, actual);
    }

    @Test
    void getBalanceTest(){
        String[] testData = {"1001", "1500"};

        int actual = balanceInquiry.getBalance(testData);

        assertEquals(1500, actual);

    }

}
