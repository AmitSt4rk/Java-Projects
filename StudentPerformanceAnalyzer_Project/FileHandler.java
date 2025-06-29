import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<Student> readStudentsFromFile(String FILE_NAME) {
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length != 8) {
                    System.out.println("Invalid Record (wrong fields!): " + line);
                    continue;
                }

                try {
                    int rollNo = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    int[] marks = new int[5];
                    for (int i = 0; i < 5; i++) {
                        marks[i] = Integer.parseInt(parts[i+3].trim());
                    }

                    if (!Validator.isValidName(name) || !Validator.isValidEmail(email)) {
                        System.out.println("Invalid! Student Name or Email Data: "+line);
                        continue;
                    }

                    students.add(new Student(rollNo, name, email, marks));
                } catch (Exception e) {
                    System.out.println("Error! Parsing this line: "+line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error! Finding File: "+FILE_NAME);
        }
        return students;
    }
}


