package ex01;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.out.println("Input argument \"--count=some_number\"");
            System.exit(-1);
        }
        int counter = 0;
        try {
            counter = Integer.parseInt(args[0].substring(8));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Thread eggThread = new MyThread("Egg", counter, Type.CONSUMER);
        Thread henThread = new MyThread("Hen", counter, Type.PRODUCER);
        eggThread.start();
        henThread.start();
    }
}