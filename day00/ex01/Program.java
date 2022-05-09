import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		if (number <= 1) {
			System.exit(putIllegalArgument());
		}
		int steps = 0;
		if (number == 2 || number == 3) {
			steps++;
			System.out.println("true " + steps);
			return;
		}
		if (number % 2 == 0) {
			steps++;
			System.out.println("false " + steps);
			return;
		}
		steps += 2;
		for (int i = 3; i * i <= number; i += 2) {
			steps++;
			if (number % i == 0) {
				System.out.println("false " + steps);
				return;
			}
		}
		System.out.println("true " + steps);

	}

	private static int putIllegalArgument() {
		System.err.println("IllegalArgument");
		return (-1);
	}
}


