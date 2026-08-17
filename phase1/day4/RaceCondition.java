package phase1.day4;

public class RaceCondition {
    volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();

        int count = 0;
        while (!stop) {
            count++;
        }
        System.out.println("stopped after " + count + " iterations");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000); // give main thread time to enter the loop
        } catch (InterruptedException e) {
        }
        System.out.println("setting stop = true");
        RaceCondition.stop = true;
    }
}
