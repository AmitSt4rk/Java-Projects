package Games;

import java.util.Scanner;

public class Guessing_Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = (int) (Math.random()*100)+1;
        int attempts = 0;
        System.out.println("Guess Number Between 1 and 100");
        while (true) {
            System.out.print("Enter Your Guess: ");
            int g = sc.nextInt();
            attempts++;
            if (g == target) {
                System.out.println("You Guessed it Right! It took you "+attempts+" Attempts.");
                break;
            }
            if (g < target) {
                System.out.println("Your Guess is Low.");
            }else{
                System.out.println("Your Guess is High.");
            }
        }
        sc.close();
    }
}
