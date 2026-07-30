package exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void testAddSimpleNumbers() {
        // Arrange
        StringCalculator calc = new StringCalculator();

        // Act
        int result = calc.add("5,3");

        // Assert
        assertEquals(8, result);
    }

    @Test
    public void testMultiplySimpleNumbers() {
        // Arrange
        StringCalculator calc = new StringCalculator();

        // Act
        int result = calc.multiply("2,3,4");

        // Assert
        assertEquals(24, result);
    }

    @Test
    public void testMultiplyEmptyOrNull() {
        // Arrange
        StringCalculator calc = new StringCalculator();

        // Act
        int resultEmpty = calc.multiply("");
        int resultNull = calc.multiply(null);

        // Assert
        assertEquals(0, resultEmpty);
        assertEquals(0, resultNull);
    }

    @Test
    public void testDivideSimpleNumbers() {
        // Arrange
        StringCalculator calc = new StringCalculator();

        // Act
        int result = calc.divide("10,2");

        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testDivideByZeroThrowsException() {
        // Arrange
        StringCalculator calc = new StringCalculator();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calc.divide("10,0")
        );
        assertEquals("Division by zero is undefined.", exception.getMessage());
    }
}