package lab04;

import lab03.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5", "5, 14, 19"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
        // arrange
        Calculator calc = new Calculator();
        int expectedResult = c;

        // act
        int result = calc.add(a, b);

        // assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "{0} multiple {1} should be equal {2}")
    @CsvSource({"2, 2, 4", "4, 5, 20"})
    public void multiple_multipleAB_returnsC(int firstNumber, int secondNumber, int result) {
        Calculator calculator = new Calculator();
        int actualResult = calculator.multiply(firstNumber, secondNumber);
        assertEquals(result, actualResult);
    }
}
