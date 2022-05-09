import java.util.Scanner;

public class Program {

	private static final int STOP_NUMBER = 42;
	private static final int BASE = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = 0;
		int coffeeTypeCounter = 0;
		while (true) {
			number = scanner.nextInt();
			if (number == STOP_NUMBER) {
				break;
			}
			if (isCoffeeType(number)) {
				coffeeTypeCounter++;
			}
		}
		System.out.println("Count of coffee-request - " + coffeeTypeCounter);
	}

	public static boolean isCoffeeType(int n) {
		int sumOfDigits = 0;
		while (n != 0) {
			sumOfDigits += n % BASE;
			n /= BASE;
		}
		return isPrime(sumOfDigits);
	}

	public static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; ++i) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
