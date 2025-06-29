import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTesterTool {

    private static final String FILE_HISTORY = "regex_history.txt";

    private static String testRegex(String Inputpattern, String Inputtext) {

        String str = "";
        try {

            Pattern pattern = Pattern.compile(Inputpattern);
            Matcher matcher = pattern.matcher(Inputtext);

            boolean found = false;
            while (matcher.find()) {
                str += ("Matched: \"" + matcher.group() + "\" from index: " + matcher.start() + " to "
                        + (matcher.end() - 1))+"\n";
                found = true;
            }

            if (!found) {
                str = ("No Match found.");
            }

        } catch (PatternSyntaxException e) {
            str = ("Invalid Regex Pattern! Error: " + e.getDescription());
        }

        return str;
    }

    private static void saveToHistory(String pattern, String text, String result){
        try (FileWriter writer = new FileWriter(FILE_HISTORY, true)) {
            writer.write("Pattern: "+pattern+"\n");
            writer.write("Text: "+text+"\n");
            writer.write("Result: "+result+"\n");
            writer.write("----------------\n");
        } catch (IOException e) {
            System.out.println("Error! saving History: "+ e.getMessage());
        }
    }

    private static void clearHistory(){
        try (FileWriter writer = new FileWriter(FILE_HISTORY, false)){
            writer.write("");
            System.out.println("History Cleared Successfully!");
        } catch (IOException e) {
            System.out.println("Error: clearing History: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n========REGEX Tester Tool========");

        while (true) {
            System.out.print("\n(type 'clear' to delete history! and type 'exit' to quit.)\nEnter Regex Pattern: ");
            String patternInput = sc.nextLine();

            if (patternInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting... Thank you!");
                sc.close();
                return;
            }else if(patternInput.equalsIgnoreCase("clear")){
                System.out.println("Clearing... History!");
                clearHistory();
                continue;
            }

            System.out.print("Enter text to test against: ");
            String textInput = sc.nextLine();

            String result = testRegex(patternInput, textInput);
            System.out.println("\n----Matches Found----");
            System.out.println(result);
            saveToHistory(patternInput, textInput, result);
        }
    }
}