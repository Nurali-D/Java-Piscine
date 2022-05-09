package ex02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Program {

    public static int[] array;
    private static final int LIMIT_MODUL = 1000;
	private static final int MAX_ARRAY_SIZE = 2000000;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Input arguments \"--arraySize=x --threadsCount=y\"");
            System.out.println("x <= 2 000 000, y < x.");
            System.exit(-1);
        }
        int arraySize = parseInteger(args[0]);
        int threadCount = parseInteger(args[1]);
        if ((threadCount > arraySize) || (arraySize <= 0) 
				|| (threadCount <= 0) || (arraySize > MAX_ARRAY_SIZE)) {
            System.out.println("Invalid arguments");
            System.exit(-1);
        }
        array = new int[arraySize];
        fillArray();
        long sumOfArrayElements = calculateSum();
        System.out.println("Sum: " + sumOfArrayElements);
        splitArrayByThreads(threadCount);
    }

    private static void splitArrayByThreads(int threadCount) {
        int beginIndex = 0;
        int lastIndex = 0;
        int subArraysSize = array.length / (threadCount - 1);
        ArrayList<ArraySumCounter> threadList = new ArrayList<>();

        for (int i = 0; i < threadCount - 1; ++i) {
            beginIndex = i * subArraysSize;
            lastIndex = (i + 1) * subArraysSize;
            threadList.add(new ArraySumCounter(beginIndex, lastIndex));
        }
        beginIndex = (threadCount - 1) * subArraysSize;
        lastIndex = array.length;
        threadList.add(new ArraySumCounter(beginIndex, lastIndex));

        for (ArraySumCounter asc : threadList) {
            asc.start();
        }

        for (ArraySumCounter asc : threadList) {
            try {
                asc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads: " + ArraySumCounter.getSumOfArray());
    }

    private static int parseInteger(String str) {
        String[] splitByEqualSign = str.split("=");
        return Integer.parseInt(splitByEqualSign[1]);
    }

    private static void fillArray() {
        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        for (int i = 0; i < array.length; ++i) {
            int value = random.nextInt(LIMIT_MODUL);
            int sign = random.nextInt(LIMIT_MODUL);
            array[i] = (sign % 2 == 0) ? (-1) * value : value;
        }
    }

    private static long calculateSum() {
        long sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        return sum;
    }
}