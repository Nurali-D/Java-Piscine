import java.util.Scanner;

public class Program {

	private static final int MAX_WEEK = 18;
	private static final int TESTS_NUMBER = 5;
	private static final int MAX_GRADE = 9;
	private static final int BASE = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int weekCount = 1;
		long weeksMinGrades = 0;

		String line = scanner.nextLine();

		while (!line.equals("42") && weekCount != MAX_WEEK + 1) {
			if (!line.equals("Week " + weekCount)) {
				System.exit(putIllegalArgument());
			}
			weekCount++;
			int min = 9;

			for (int i = 0; i < TESTS_NUMBER; ++i) {
				if (!scanner.hasNextInt()) {
					System.exit(putIllegalArgument());
				}
				int tmp = scanner.nextInt();
				if (!(tmp > 0 && tmp <= MAX_GRADE)) {
					System.exit(putIllegalArgument());
				}
				if (tmp < min) {
					min = tmp;
				}
			}
			weeksMinGrades = weeksMinGrades * BASE + min;
			scanner.nextLine();
			line = scanner.nextLine();
		}
		printResult(weeksMinGrades);
	}

	private static void printResult(long result) {
		long reverseResult = 0;

		while (result != 0) {
			reverseResult = reverseResult * BASE + result % BASE;
			result /= BASE;
		}
		int week = 1;

		while (reverseResult != 0) {
			int min = (int) (reverseResult % BASE);
			System.out.print("Week " + week + " ");
			for (int i = 0; i < min; ++i) {
				System.out.print("=");
			}
			System.out.println(">");
			++week;
			reverseResult /= BASE;
		}
	}

	private static int putIllegalArgument() {
		System.err.println("IllegalArgument");
		return (-1);
	}
}
