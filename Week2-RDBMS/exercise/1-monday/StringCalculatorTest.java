import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringCalculator Test Suite")
class StringCalculatorTest {

    @Test
    @DisplayName("add: Empty string returns zero")
    void testAddEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    @DisplayName("add: Null input throws IllegalArgumentException")
    void testAddNullInput() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(null));
    }

    @Test
    @DisplayName("add: Negative values handled correctly")
    void testAddNegativeValues() {
        assertEquals(-5, StringCalculator.add("-10,5"));
    }

    @Test
    @DisplayName("add: Trailing comma throws IllegalArgumentException")
    void testAddTrailingComma() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("1,2,"));
    }

    @Test
    @DisplayName("add: Spaces trimmed correctly")
    void testAddSpaces() {
        assertEquals(10, StringCalculator.add(" 2 , 3 , 5 "));
    }

    @Test
    @DisplayName("add: Non-numeric values throw IllegalArgumentException")
    void testAddNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("1,abc,3"));
    }

    @Test
    @DisplayName("add: Consecutive commas throw IllegalArgumentException")
    void testAddConsecutiveCommas() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("1,,2"));
    }

    @Test
    @DisplayName("divide: Division by zero throws IllegalArgumentException")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.divide("10,0"));
    }

    @Test
    @DisplayName("divide: Sequential division order matters")
    void testDivideSequentialOrder() {
        assertEquals(5, StringCalculator.divide("100,2,10"));
    }

    @Test
    @DisplayName("divide: Spaces trimmed, sequential division correct")
    void testDivideSpaces() {
        assertEquals(4, StringCalculator.divide(" 8 , 2 , 1 "));
    }
}