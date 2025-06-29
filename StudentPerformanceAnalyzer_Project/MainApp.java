import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Reading Student Records from file...");

        ArrayList<Student> students = FileHandler.readStudentsFromFile("StudentRecord.txt");

        if (students.isEmpty()) {
            System.out.println("No Valid Student Data found.");
            return;
        }

        System.out.println("\nLoaded Student Records:\n");
        for (Student student : students) {
            System.out.println("Roll Number: "+student.getRollNumber()
            + ", Name: "+student.getName()
            + ", Email: "+student.getEmail()
            + ", Marks: "+student.getMarks()
            + ", Total: "+student.getTotal()
            + ", Percentage: "+student.getPercentage()
            + ", Result: "+student.getResult());
        }

        System.out.println("Starting Performation Analysis using Threads...");
        Analyzer analyzer = new Analyzer(students);
        analyzer.runAnalysis();
    }
}
