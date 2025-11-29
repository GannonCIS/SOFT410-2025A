package test;

import org.example.BalanceInquiry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BalanceInquiryUnitTest {

    @Test
    void existenceTest(){
        BalanceInquiry balanceInquiry = new BalanceInquiry();
        assertNotNull(balanceInquiry);
    }
}