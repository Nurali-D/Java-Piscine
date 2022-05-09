import java.util.Scanner;

public class Program {

	private static final int MAX_STUDENTS = 10;
	private static final int MAX_CLASSES_WEEK = 10;
	private static final String[][] CALENDAR = {{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
			{"", "", "1", "2", "3", "4", "5"},
			{"6", "7", "8", "9", "10", "11", "12"},
			{"13", "14", "15", "16", "17", "18", "19"},
			{"20", "21", "22", "23", "24", "25", "26"},
			{"27", "28", "29", "30", "", "", ""}};

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] studentsList = readInput(scanner, MAX_STUDENTS);
		String[] timetable = readInput(scanner, MAX_CLASSES_WEEK);
		String[][] tabularTimetable = createTabularTimetable(studentsList, timetable, scanner);
		for (int i = 0; i < tabularTimetable.length; ++i) {
			for (int j = 0; j < tabularTimetable[0].length; ++j) {
				if (i == 0 && j > 0) {
					String[] splitted = splitString(tabularTimetable[i][j], ' ');
					System.out.printf("%4s%3s%3s|", splitted[0], splitted[1], splitted[2]);
				} else if (i == 0 && j == 0) {
					System.out.printf("%10s", "");
				} else if (j == 0 && i > 0) {
					System.out.printf("%10s", tabularTimetable[i][j]);
				} else if (i > 0 && j > 0) {
					System.out.printf("%10s|", (tabularTimetable[i][j] != null) ? tabularTimetable[i][j] : "");
				}
			}
			System.out.println();
		}
	}

	private static String[] readInput(Scanner scanner, int arrayLength) {
		String[] result = new String[arrayLength];
		String line = scanner.nextLine();
		int i = 0;
		while (!line.equals(".")) {
			result[i] = line;
			line = scanner.nextLine();
			++i;
		}
		String[] resizedResult = new String[i];
		for (int j = 0; j < i; ++j) {
			resizedResult[j] = result[j];
		}
		return resizedResult;
	}

	private static String[][] createTabularTimetable(String[] studentsList,
													 String[] timetable, Scanner scanner) {
		String[] monthTimetable = makeMonthTimetable(timetable);
		String[][] result = new String[studentsList.length + 1][monthTimetable.length + 1];
		result[0][0] = "";
		for (int i = 0; i < monthTimetable.length; ++i) {
			result[0][i + 1] = monthTimetable[i];
		}
		for (int i = 0; i < studentsList.length; ++i) {
			result[i + 1][0] = studentsList[i];
		}
		String line = scanner.nextLine();
		while (!line.equals(".")) {
			putAttendaceToTimetable(line, result);
			line = scanner.nextLine();
		}
		return result;
	}

	private static void putAttendaceToTimetable(String str, String[][] array) {
		String[] splittedStr = splitString(str, ' ');
		String[] splitted = null;
		int i = 1;
		for ( ; i < array.length; ++i) {
			if (array[i][0].equals(splittedStr[0])) {
				break;
			}
		}
		int j = 1;
		for ( ; j < array[0].length; ++j) {
			splitted = splitString(array[0][j], ' ');
			if (splitted[0].equals(splittedStr[1] + ":00") && splitted[2].equals(splittedStr[2])) {
				break;
			}
		}
		array[i][j] = (splittedStr[3].equals("HERE")) ? "1" : "-1";
	}

	private static String[] makeMonthTimetable(String[] array) {
		sortByWeek(array);
		sortByTime(array);
		String firstLine = "";
		for (int i = 0; i < array.length; ++i) {
			String[] splittedElement = splitString(array[i], ' ');
			int j = 0;
			for ( ; j < 7; ++j) {
				if (splittedElement[1].equals(CALENDAR[0][j])) {
					break;
				}
			}
			for (int k = 1; k < 6; ++k) {
				if (!CALENDAR[k][j].equals("")) {
					firstLine += splittedElement[0] + ":00 " + splittedElement[1] + " " + CALENDAR[k][j] + "|";
				}
			}
		}
		String[] result = splitString(firstLine, '|');
		sortByDay(result);
		return result;

	}

	private static void sortByDay(String[] array) {
		int size = array.length;
		for (int i = 0; i < size - 1; ++i) {
			boolean swapped = false;
			for (int j = 0; j < (size - i - 1); ++j) {
				String[] splittedElement1 = splitString(array[j], ' ');
				String[] splittedElement2 = splitString(array[j + 1], ' ');
				int day1 = parseDay(splittedElement1[2]);
				int day2 = parseDay(splittedElement2[2]);
				if (day1 > day2) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

	private static int parseDay(String str) {
		char[] array = str.toCharArray();
		int day = array[0] - 48;
		if (array.length == 2) {
			day = day * 10 + (array[1] - 48);
		}
		return day;
	}

	private static void sortByWeek(String[] array) {
		int size = array.length;
		for (int i = 0; i < size - 1; ++i) {
			boolean swapped = false;
			for (int j = 0; j < (size - i - 1); ++j) {
				String[] splittedElement1 = splitString(array[j], ' ');
				String[] splittedElement2 = splitString(array[j + 1], ' ');
				if (getDayNumber(splittedElement1[1]) < getDayNumber(splittedElement2[1])) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

	private static void sortByTime(String[] array) {
		int size = array.length;
		for (int i = 0; i < size - 1; ++i) {
			boolean swapped = false;
			for (int j = 0; j < (size - i - 1); ++j) {
				String[] splittedElement1 = splitString(array[j], ' ');
				String[] splittedElement2 = splitString(array[j + 1], ' ');
				char[] ar1 = splittedElement1[0].toCharArray();
				char[] ar2 = splittedElement2[0].toCharArray();
				int time1 = ar1[0] - 48;
				int time2 = ar2[0] - 48;
				if (time1 > time2) {
					String temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

	private static int getDayNumber(String day) {
		for (int i = 0; i < 7; ++i) {
			if (CALENDAR[0][i].equals(day)) {
				return i;
			}
		}
		return -1;
	}

	private static String[] splitString(String str, char delimiter) {
		int length = str.length();
		char[] array = str.toCharArray();
		int spaceCounter = 0;
		for (int i = 0; i < length; ++i) {
			if (array[i] == delimiter) {
				++spaceCounter;
			}
		}
		int newLength = spaceCounter + 1;
		if (array[length - 1] == delimiter) {
			newLength = spaceCounter;
		}
		String[] result = new String[newLength];
		for (int k = 0; k < result.length; ++k) {
			result[k] = "";
		}
		int j = 0;

		for (int i = 0; i < newLength; ++i) {
			if (j < length &&  array[j] == delimiter) {
				j++;
			}
			for (; j < length; ++j) {
				if (array[j] == delimiter) {
					break;
				}
				result[i] += array[j];
			}
		}
		return result;
	}
}
