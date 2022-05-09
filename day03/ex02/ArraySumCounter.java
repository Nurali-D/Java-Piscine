package ex02;

public class ArraySumCounter extends Thread {

    private static long sumOfArray = 0;
    private int start;
    private int end;

    public ArraySumCounter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int i = start; i < end; ++i) {
            sum += Program.array[i];
        }
        System.out.println(Thread.currentThread().getName() +
                ": from " + start + " to " + end + " sum is " + sum);
        addToSumOfArray(sum);
    }

    public static long getSumOfArray() {
        return sumOfArray;
    }

    private static synchronized void addToSumOfArray(long sum) {
        sumOfArray += sum;
    }
}
