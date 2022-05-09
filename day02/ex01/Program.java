package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Program {

    private static ArrayList<String> file1List;
    private static ArrayList<String> file2List;
    private static TreeSet<String> dictionary;
    private static final String DICTIONARY_FILE = "dictionary.txt";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Need 2 files");
            return;
        }
        file1List = new ArrayList<String>();
        file2List = new ArrayList<String>();
        dictionary = new TreeSet<String>();
        readParseFiles(args);
        double similarity = calculateSimilarity();
        String str = String.format("%.3f", similarity);
        System.out.println("Similarity = " + str.substring(0, str.length() - 1));
    }

    private static void readParseFiles(String[] args) {
        try (BufferedReader br1 = new BufferedReader(new FileReader(args[0]));
                BufferedReader br2 = new BufferedReader(new FileReader(args[1]))) {
            readFileToList(br1, file1List);
            readFileToList(br2, file2List);
            writeDictionaryToFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFileToList(BufferedReader br, List<String> fileList) throws IOException {
        String line = "";
        while (br.ready()) {
            line = br.readLine();
            String[] array = line.split(" ");
            List<String> list = Arrays.asList(array);
            fileList.addAll(list);
            dictionary.addAll(list);
        }
    }

    private static void writeDictionaryToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DICTIONARY_FILE))) {
            for (String word : dictionary) {
                bw.write(word + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static double calculateSimilarity() {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();


        calculateOccurrence(list1, file1List);
        calculateOccurrence(list2, file2List);
        double numerator = calculateNumerator(list1, list2);
        double denominator = calculateDenominator(list1, list2);
        if (numerator == 0 || denominator == 0) {
            return 0;
        }
        double similarity = numerator / denominator;
        return similarity;
    }

    private static void calculateOccurrence(List<Integer> list, List<String> fileList) {
        int i = 0;
        int occurrence = 0;

        for (String word : dictionary) {
            for (String s : fileList) {
                if (s.equals(word)) {
                    occurrence++;
                }
            }
            list.add(i, occurrence);
            occurrence = 0;
            ++i;
        }
    }

    public static int calculateNumerator(List<Integer> a, List<Integer> b) {
        int multiplySum = 0;
        for (int i = 0; i < dictionary.size(); i++)
            multiplySum += a.get(i) * b.get(i);
        return multiplySum;
    }

    public static double calculateDenominator(List<Integer> a, List<Integer> b) {
        double sumA = sumOfSquaredElements(a);
        double sumB = sumOfSquaredElements(b);

        return Math.sqrt(sumA) * Math.sqrt(sumB);
    }

    private static double sumOfSquaredElements(List<Integer> list) {
        double sum = 0;
        for (Integer n : list) {
            sum += n * n;
        }
        return sum;
    }
}