package ex01;

public class Printer {
    private boolean isAnotherPrinted = true;

    public synchronized void printMessage(String message, Type type) throws InterruptedException {
        if (type == Type.CONSUMER) {
            while (!isAnotherPrinted) {
                wait();
            }
            isAnotherPrinted = false;
        } else {
            while (isAnotherPrinted) {
                wait();
            }
            isAnotherPrinted = true;
        }
        System.out.println(message);
        notify();
    }
}
