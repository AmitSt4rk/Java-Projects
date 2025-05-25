package Practice_Projects;

import java.util.*;

class Student {
    String name;
    int rollNo;
    String course;

    public Student(String name, int roll, String course){
        this.name = name;
        this.rollNo = roll;
        this.course = course;
    }

    public void display(){
        System.out.println();
        System.out.println("Name: "+name);
        System.out.println("Roll Number: "+rollNo);
        System.out.println("Course: "+course);
    }
}


public class StudentRecordManagementSystem {
    public static void main(String[] args) {
        ArrayList<Student> studends = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            System.out.println("\n----Student Record Management System----");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student by Roll Number");
            System.out.println("5. View List of all Students");
            System.out.println("6. Exit");
            System.out.print("\nEnter your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Course: ");
                    String course = sc.nextLine();

                    studends.add(new Student(name, roll, course));

                    System.out.println("Student Added Successfully.");
                    break;
                
                case 2:
                    System.out.print("Enter Roll Number to Update: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();
                    boolean updated = false;

                    for(Student s : studends){
                        if(s.rollNo == updateRoll){
                            System.out.print("Enter new Name: ");
                            s.name = sc.nextLine();
                            System.out.print("Enter new Course: ");
                            s.course = sc.nextLine();

                            System.out.println("Student Updated Successfully.");
                            updated = true;
                            break;
                        }
                    }
                    if(!updated){
                        System.out.println("Student Not Found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Delete: ");
                    int deleteRoll = sc.nextInt();
                    boolean Deleted = false;

                    for(int i = 0; i < studends.size(); i++){
                        if (studends.get(i).rollNo == deleteRoll) {
                            studends.remove(i);
                            System.out.println("Student Deleted Successfully.");
                            Deleted = true;
                            break;
                        }
                    }
                    if (!Deleted) {
                        System.out.println("Student not Found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll Number to Search: ");
                    int rolln = sc.nextInt();
                    boolean found = false;

                    for(Student s: studends){
                        if (s.rollNo == rolln) {
                            s.display();
                            found = true;
                            break;
                        }
                    }

                    if(!found){
                        System.out.println("Student not Found.");
                    }
                    break;

                case 5:
                    System.out.println("----List of All Students----");
                    for(Student s : studends){
                        s.display();
                    }
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;
            
                default:System.out.println("Wrong! Choice.");
                    break;
            }

        }while(choice != 6);
        sc.close();
    }
}
