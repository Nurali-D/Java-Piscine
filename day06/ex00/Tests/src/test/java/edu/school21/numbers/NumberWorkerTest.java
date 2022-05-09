package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    private NumberWorker nw;

    @BeforeEach
    void initializeNw() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {123457, 992111, 643213, 76541})
    void isPrimeForPrimes(int number) {
        assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {123333, 9921111, 6432132, 765414})
    void isPrimeForNotPrimes(int number) {
        assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -6, -154, -1453})
    void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> nw.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitSumForCorrectNumbers(int x, int y) {
        assertEquals(nw.digitsSum(x), y);
    }

}
