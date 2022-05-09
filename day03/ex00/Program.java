package ex00;

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
        Thread eggThread = new MyThread("Egg", counter);
        Thread henThread = new MyThread("Hen", counter);
        eggThread.start();
        henThread.start();
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < counter; ++i) {
            System.out.println("Human");
        }

    }
}