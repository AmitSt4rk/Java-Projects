import java.util.Scanner;

public class Simple_ToDoList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        while (taskCount < 100) {
            System.out.println("1. Add Task \n2. View Task \n3. Remove Task \n4. Exit");
            System.out.print("Choose your Option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Task: ");
                    tasks[taskCount] = sc.nextLine();
                    taskCount++;
                    System.out.println("Task Added.");
                    break;

                case 2:
                    if (taskCount == 0) {
                        System.out.println("Tasks Not added yet!");
                    }else{
                        for(int i = 0; i < taskCount; i++){
                            System.out.println((i+1)+". "+tasks[i]);
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.print("Enter task Number: ");
                    int r = sc.nextInt();
                    if (r-1 >= 0 && r-1 < taskCount ) {
                        for(int i = r-1; i < taskCount-1; i++){
                            tasks[i] = tasks[i+1];
                        }
                        taskCount--;
                        System.out.println("task removed.");
                    }else{
                        System.out.println("There is no Task!");
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;
            
                default:System.out.println("Invalid! Option.");
                    break;
            }
        }
        sc.close();
    }
}
