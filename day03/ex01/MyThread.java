package ex01;

public class MyThread extends Thread {
    private Type type;
    private String message;
    private int counter;
    private static final Printer printer = new Printer();

    public MyThread(String message, int counter, Type type) {
        this.message = message;
        this.counter = counter;
        this.type = type;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < this.counter; ++i) {
            try {
                printer.printMessage(this.message, this.type);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

enum Type {
    PRODUCER,
    CONSUMER
}
