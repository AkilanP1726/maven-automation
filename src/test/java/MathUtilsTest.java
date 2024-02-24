import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {
    MathUtils cal;

    @BeforeEach
    void setUp() {
        cal = new MathUtils();
    }

    @AfterEach
    void tearDown() {
        cal = null;
    }

    @Test
    void add() {
        assertEquals(5, cal.add(2,3));
    }

    @Test
    void subtract() {
        assertEquals(0, cal.subtract(2,2));
    }

    @Test
    void multiply() {
        assertEquals(10, cal.multiply(2,5));
    }

    @Test
    void divide() {
        assertEquals(-1.0, cal.divide(2, 0));
    }
}