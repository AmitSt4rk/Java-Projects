import java.util.Scanner;

class AdditionThread extends Thread {
    double a, b;

    AdditionThread(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        System.out.printf("%-24s%.2f\n", String.format("Addition of %.2f and %.2f :", a, b), (a + b));
    }
}

class SubtractionThread extends Thread {
    double a, b;

    SubtractionThread(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        System.out.printf("%-24s%.2f\n", String.format("Subtraction of %.2f and %.2f :", a, b), (a - b));
    }
}

class MultiplicationThread extends Thread {
    double a, b;

    MultiplicationThread(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        System.out.printf("%-24s%.2f\n", String.format("Multiplication of %.2f and %.2f :", a, b), (a * b));
    }
}

class DivisionThread extends Thread {
    double a, b;

    DivisionThread(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        if (b == 0) {
            System.out.println("Division: Cannot divide by zero");
        } else {
            System.out.printf("%-24s%.2f\n", String.format("Division of %.2f and %.2f :", a, b), (a / b));
        }
    }
}

class RemainderThread extends Thread {
    double a, b;

    RemainderThread(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        System.out.printf("%-24s%.2f\n", String.format("Remainder of %.2f and %.2f :", a, b), (a % b));
    }
}

public class MultiThreadedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double n1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double n2 = scanner.nextDouble();

        System.out.print("(Enter \\'Exit\\' to stop the program.)\nChoose Operation: \'+,-,*,/,%\':");
        while (true) {

            scanner.nextLine();
            String s = scanner.nextLine();

            if (s.equalsIgnoreCase("Exit")) {
                break;
            }

            if (s.equals("+")) {
                Thread add = new AdditionThread(n1, n2);
                add.start();
            } else if (s.equals("-")) {
                Thread sub = new SubtractionThread(n1, n2);
                sub.start();
            } else if (s.equals("*")) {
                Thread mul = new MultiplicationThread(n1, n2);
                mul.start();
            } else if (s.equals("/")) {
                Thread div = new DivisionThread(n1, n2);
                div.start();
            } else if (s.equals("%")) {
                Thread rem = new RemainderThread(n1, n2);
                rem.start();
            } else {
                System.out.println("!Wrong choice...");
            }
        }
        scanner.close();
    }
}
