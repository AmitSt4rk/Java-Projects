package Basic_Projects;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter first Number: ");
            double num1 = sc.nextDouble();
            System.out.print("Enter Your Choice - (+,-,*,/,%): ");
            String Operator = sc.next();
            System.out.print("Enter second Number: ");
            double num2 = sc.nextDouble();

            double result = 0;
            switch (Operator) {
                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    result = num1 / num2;
                    break;

                case "%":
                    result = num1 % num2;
                    break;
            
                default:System.out.println("Invalid Operator!");
                    break;
            }
            System.out.println("Result : "+result);
            System.out.println("Wana Continue ? : (Yes or No)");
            if (sc.next().equals("No")) {
                break;
            }
        }
        sc.close();
    }
}
