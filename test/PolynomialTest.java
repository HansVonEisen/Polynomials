import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    static Polynomial p, p2;

    @BeforeAll
    static void setUp() {
        p = new Polynomial();
        int c = 1;
        for (int exp = 0; exp <= 3; exp++) {
            p.setCoefficient(exp, c);
            c++;
        }

        p2 = new Polynomial();
        c = 5;
        for (int exp = 0; exp <= 5; exp++) {
            p2.setCoefficient(exp, c);
            c -= 2;
        }
    }

    @Test
    void getDegree() {
        assertEquals(3, p.getDegree());
        assertEquals(5, p2.getDegree());
    }

    @Test
    void getCoefficient() {
        assertEquals(1, p.getCoefficient(0));
        assertEquals(2, p.getCoefficient(1));
        assertEquals(3, p.getCoefficient(2));
        assertEquals(4, p.getCoefficient(3));

        assertEquals(5, p2.getCoefficient(0));
        assertEquals(3, p2.getCoefficient(1));
        assertEquals(1, p2.getCoefficient(2));
        assertEquals(-1, p2.getCoefficient(3));
        assertEquals(-3, p2.getCoefficient(4));
        assertEquals(-5, p2.getCoefficient(5));
    }

    @Test
    void stringTest() {
        String expectedString = "4.0x^3 + 3.0x^2 + 2.0x + 1.0";
        String actualString = p.toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    void getY() {
        assertEquals(216.25, p.getY(3.5));
        assertEquals(-3091.40625, p2.getY(3.5));
    }

    @Test
    void sum() {
        Polynomial expectedSum = new Polynomial();
        expectedSum.setCoefficient(0,6);
        expectedSum.setCoefficient(1,5);
        expectedSum.setCoefficient(2,4);
        expectedSum.setCoefficient(3,3);
        expectedSum.setCoefficient(4,-3);
        expectedSum.setCoefficient(5,-5);
        Polynomial actualSum = p.sum(p2);
        assertTrue(expectedSum.equals(actualSum));
        expectedSum.setCoefficient(4, 98);
        assertFalse(expectedSum.equals(actualSum));
    }

    @Test
    void diff() {
        Polynomial expectedDiff = new Polynomial();
        expectedDiff.setCoefficient(0,-4);
        expectedDiff.setCoefficient(1,-1);
        expectedDiff.setCoefficient(2,2);
        expectedDiff.setCoefficient(3,5);
        expectedDiff.setCoefficient(4,3);
        expectedDiff.setCoefficient(5,5);
        Polynomial actualDiff = p.diff(p2);
        assertTrue(expectedDiff.equals(actualDiff));
        expectedDiff.setCoefficient(4, 98);
        assertFalse(expectedDiff.equals(actualDiff));
    }

    @Test
    void derivative() {
        Polynomial expectedDerivative = new Polynomial();
        expectedDerivative.setCoefficient(0, 2);
        expectedDerivative.setCoefficient(1, 6);
        expectedDerivative.setCoefficient(2, 12);
        expectedDerivative.setCoefficient(3, 0);

        Polynomial actualDerivative = p.derivative();
        assertTrue(expectedDerivative.equals(actualDerivative));
    }
}