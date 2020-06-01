import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcTest {
    private Calculator calculator;

    @Test
    public void getLast4Array_correct() {
        calculator = new Calculator();
        int[] src = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] expect = { 1, 7};
        Assertions.assertArrayEquals(expect, calculator.getLast4Array(src));
    }

    @Test
    public void getLast4Array_endOf() {
        calculator = new Calculator();
        int[] src = {1, 2, 4, 4, 2, 3, 4};
        int[] expect = { };
        Assertions.assertArrayEquals(expect, calculator.getLast4Array(src));
    }


    @Test
    public void getLast4Array_beginning() {
        calculator = new Calculator();
        int[] src = { 4, 1, 2, 3};
        int[] expect = { 1, 2, 3};
        Assertions.assertArrayEquals(expect, calculator.getLast4Array(src));
    }

    @Test
    public void getLast4Array_exception() {
        calculator = new Calculator();
        int[] src = {1, 3, 3, 6};
        int[] expect = { 1, 7};
        Assertions.assertThrows(RuntimeException.class,
                ()->{ calculator.getLast4Array(src);});
    }


}