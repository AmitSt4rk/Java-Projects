import java.util.ArrayList;

public class Analyzer {
    private ArrayList<Student> students;

    Analyzer(ArrayList<Student> students){
        this.students = students;
    }

    public void runAnalysis(){
        Thread topperThread = new Thread(new TopperStudent());
        Thread subjectTopperThread = new Thread(new SubjectToppers());
        Thread passFailThread = new Thread(new PassFailStudentsCount());
        Thread averageThread = new Thread(new AverageCalculator());

        topperThread.start();
        subjectTopperThread.start();
        passFailThread.start();
        averageThread.start();

        try {
            topperThread.join();
            subjectTopperThread.join();
            passFailThread.join();
            averageThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    private class TopperStudent implements Runnable {
        public void run(){
            Student topper = students.get(0);
            for (Student student : students) {
                if (student.getTotal() > topper.getTotal()) {
                    topper = student;
                }
            }
            System.out.println("\n Topper: "+topper.getName()+" "+(topper.getPercentage())+"%");
        }
    }

    private class SubjectToppers implements Runnable {
        public void run(){
            System.out.println("Subject-wise Toppers: ");
            for (int i = 0; i < 5; i++) {
                Student subjectTopper = students.get(0);
                for (Student student : students) {
                    if (student.getMarks()[i] > subjectTopper.getMarks()[i]) {
                        subjectTopper = student;
                    }
                }
                System.out.println("Subject-"+(i+1)+": "+subjectTopper.getName()+" "+subjectTopper.getMarks());
            }
        }
    }

    private class PassFailStudentsCount implements Runnable{
        public void run(){
            int pass = 0;
            int fail = 0;
            for (Student student : students) {
                if (student.getResult().equals("Pass")) {
                    pass++;
                }else{
                    fail++;
                }
            }
            System.out.println("Pass: "+pass+", Fail: "+fail);
        }
    }

    private class AverageCalculator implements Runnable{
        public void run(){
            double totalPercentage = 0;
            for (Student student : students) {
                totalPercentage += student.getPercentage();
            }
            double average = totalPercentage/students.size();
            System.out.printf("Average class Percentage: %.2f\n",average);
        }
    }
}