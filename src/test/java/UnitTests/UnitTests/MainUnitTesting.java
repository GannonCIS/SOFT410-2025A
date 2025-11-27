package UnitTests;

import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainUnitTesting {

    @Test
    void menuTest(){
        assertDoesNotThrow(() -> Main.menu(1001));
    }

    @Test
    void mainTest(){
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }



}
