import java.util.Scanner;

public class Program {

	private static final int UNICODE_AMOUNT = 65535;
	private static final int HISTOGRAM_LINES = 12;
	private static final int HISTOGRAM_COLUMNS = 10;
	private static final int HASH_MAX = 10;
	private static final int MAX_OCCURENCES = 10;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		char[] textArray = text.toCharArray();
		int textLength = text.length();
		short[] unicodeArray = new short[UNICODE_AMOUNT];
		getCharOccurences(textArray, unicodeArray, textLength);
		short[][] tenMax = getTenMax(unicodeArray);
		short[] hashNumber = getHashNumber(tenMax);
		printHistogram(tenMax, hashNumber);
	}

	private static void printHistogram(short[][] tenMax, short[] hashNumber) {
		for (int i = 0; i < HISTOGRAM_LINES; ++i) {
			for (int j = 0; j < HISTOGRAM_COLUMNS; ++j) {
				if (hashNumber[j] == HASH_MAX - i && i < HISTOGRAM_LINES - 1) {
					if (tenMax[1][j] >= HASH_MAX) {
						System.out.print(" ");
					} else {
						System.out.print("  ");
					}
					if (tenMax[1][j] != 0) {
						System.out.print(tenMax[1][j]);
					}

				} else if (hashNumber[j] > HASH_MAX - i && i < HISTOGRAM_LINES - 1) {
					System.out.print("  #");
				} else if (i == HISTOGRAM_LINES - 1){
					System.out.print("  " + ((tenMax[0][j] == 0) ? " " : (char)tenMax[0][j]));
				}
			}
			System.out.println();
		}
	}

	private static short[] getHashNumber(short[][] array) {
		short[] result = new short[MAX_OCCURENCES];
		short maxValue = array[1][0];
		result[0] = HASH_MAX;
		for (int i = 1; i < result.length; ++i) {
			result[i] = (short)(array[1][i] * HASH_MAX / maxValue);
		}
		return result;
	}

	private static void getCharOccurences(char[] tArray, short[] uArray, int length) {
		for (int i = 0; i < length; ++i) {
			uArray[tArray[i]] += 1;
		}
	}

	private static short[][] getTenMax(short[] uArray) {
		short[] tenMaxValueIndices = getIndices(uArray);
		short[][] result = new short[2][MAX_OCCURENCES];
		for (int i = 0; i < MAX_OCCURENCES; ++i) {
			result[0][i] = tenMaxValueIndices[i];
			result[1][i] = uArray[tenMaxValueIndices[i]];
		}
		return result;
	}

	private static short[] getIndices(short[] uArray) {
		short[] result = new short[MAX_OCCURENCES];
		short maxValue = 0;

		for (int i = 0; i < UNICODE_AMOUNT; ++i) {
			if (uArray[i] == 0) {
				continue;
			}
			if (uArray[i] > maxValue) {

				for (int j = 0; j < result.length; ++j) {
					if (uArray[i] > uArray[result[j]]) {
						putMaxValueIndice(result, i, j);
						break;
					}
				}
				maxValue = 0;
			}
		}
		return result;
	}

	private static void putMaxValueIndice(short[] array, int replaceValue, int index) {
		short tmp = 0;
		short tmp2 = (short)replaceValue;
		for (int i = index; i < MAX_OCCURENCES; ++i) {
			tmp = array[i];
			array[i] = tmp2;
			tmp2 = tmp;
		}
	}
}
