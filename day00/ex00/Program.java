public class Program {

    private static final int BASE = 10;

    public static void main(String[] args) {
int sixDigitNumber = 479598;
        int sumOfDigits = 0;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        sumOfDigits += sixDigitNumber % BASE;
        sixDigitNumber /= BASE;
        System.out.println(sumOfDigits);
    }
}
