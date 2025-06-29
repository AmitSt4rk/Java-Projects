import java.time.LocalTime;
import java.util.Scanner;

class ClockThread extends Thread {
    LocalTime time = LocalTime.now();
    private int hour = time.getHour();
    private int minute = time.getMinute();
    private int second = time.getSecond();

    public void display() {
        System.out.printf("\nTime: %02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                }
                if (minute == 60) {
                    minute = 0;
                    hour++;
                }
                if (hour == 24) {
                    hour = 0;
                }

                display();
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Clock Stopped.");
    }
}

public class DigitalClock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClockThread thread = new ClockThread();
        thread.start();

        System.out.println("Press \'Enter\' to Stop Time.");
        sc.nextLine();
        thread.interrupt();
        System.out.println("Time is Over!");
        sc.close();
    }
}
