package Basic_Projects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentsMarksRecorder {
    private static final String FILE_NAME = "students.txt";

    public static void writeToFile(String name, int marks) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("Student Name: " + name + ", Marks: " + marks + "\n");
        } catch (IOException e) {
            System.out.println("Error: writing to file - " + e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nStored Students records: ");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No records yet.");

        } catch (IOException e) {
            System.out.println("Error: reading from file - " + e.getMessage());
        }
    }

    public static void clearFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_NAME);
            writer.write("");
            System.out.println("File cleared successfully.");
        } catch (IOException e) {
            System.out.println("Error! clearing data: " + e.getMessage());
        } finally {
            try {
                if (writer!=null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error! closing file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n-:Student Marks Record:-");
            System.out.println("1. Add Record");
            System.out.println("2. View all Record");
            System.out.println("3. Clear all Records");
            System.out.println("4. Exit.");

            try {
                System.out.print("Enter your choice between 1 to 4: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter student Marks: ");
                        int marks = sc.nextInt();

                        if (marks < 0 || marks > 100) {
                            throw new IllegalArgumentException("Marks must be in between 0 to 100.");
                        }

                        writeToFile(name, marks);
                        System.out.println("Student added successfully.");
                        break;

                    case 2:
                        readFromFile();
                        break;

                    case 3:
                        clearFile();
                        break;

                    case 4:
                        running = false;
                        System.out.println("See ya...");
                        break;

                    default:System.out.println("Invalid input Enter your choice between 1 to 4.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invailid Input! Marks must be a number between 1 to 4.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}