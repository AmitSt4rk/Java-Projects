package Games;

import java.util.Random;
import java.util.Scanner;

public class rock_paper_scissors {
    public static void main(String[] args) {
        Random rand = new Random();
        int computer = rand.nextInt(3);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \"0\" for Rock\nEnter \"1\" for Paper\nEnter \"2\" for Scissors.");
        System.out.print("Enter your Choice : ");
        int user = sc.nextInt();
        sc.close();

        System.out.println("\nComputer v/s User:");
        System.out.println("\"" + computer + " v/s " + user + "\"\n");

        if (computer == user) {
            System.out.println("The Game is Draw.");
        }
        else if ((user == 0 && computer == 2) || (user == 1 && computer == 0) || (user == 2 && computer == 1)) {
            System.out.println("User is the Winner.");
        }
        else {
            System.out.println("Computer is the Winner.");
        }
    }
}
