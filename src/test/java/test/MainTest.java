package test;

import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void testMainClass() {

        Assertions.assertDoesNotThrow(() -> {
            Class.forName("org.example.Main");
        });
    }
}
