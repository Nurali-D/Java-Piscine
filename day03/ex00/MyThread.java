package ex00;

public class MyThread extends Thread {

    private String message;
    private int counter;

    public MyThread(String message, int counter) {
        this.message = message;
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < this.counter; ++i) {
            System.out.println(message);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
