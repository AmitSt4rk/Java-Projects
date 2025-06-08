//package Basic_Projects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTesterTool {

    private static final String FILE_HISTORY = "regex_history.txt";

    private static void testRegex(String Inputpattern, String Inputtext) {

        try {

            Pattern pattern = Pattern.compile(Inputpattern);
            Matcher matcher = pattern.matcher(Inputtext);

            boolean found = false;
            System.out.println("\n----Matches Found----");
            while (matcher.find()) {
                System.out.println("Matched: \"" + matcher.group() + "\" from index: " + matcher.start() + " to "
                        + (matcher.end() - 1));
                found = true;
            }

            if (!found) {
                System.out.println("No Match found.");
            }

        } catch (PatternSyntaxException e) {
            System.out.println("Invalid Regex Pattern! Error: " + e.getDescription());
        }
    }

    private static void saveToHistory(String pattern, String text){
        try (FileWriter writer = new FileWriter(FILE_HISTORY, true)) {
            writer.write("Pattern: "+pattern+"\n");
            writer.write("Text: "+text+"\n");
            writer.write("----------------\n");
        } catch (IOException e) {
            System.out.println("Error! saving History: "+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n========REGEX Tester Tool========");

        while (true) {
            System.out.print("\nEnter Regex Pattern (or type 'exit' to quit.): ");
            String patternInput = sc.nextLine();

            if (patternInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting... Thank you!");
                sc.close();
                return;
            }

            System.out.print("Enter text to test against: ");
            String textInput = sc.nextLine();

            testRegex(patternInput, textInput);
            saveToHistory(patternInput, textInput);
        }
    }
}