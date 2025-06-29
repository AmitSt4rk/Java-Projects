public class Student {
    private int rollNo;
    private String name;
    private String email;
    private int[] marks;
    private int total;
    private double percentage;
    private String result;

    Student(int roll, String name, String email, int[] marks){
        this.rollNo = roll;
        this.name = name;
        this.email = email;
        this.marks = marks;
        calculateTotal();
        calculatePercentage();
        calculateResult();
    }

    private void calculateTotal(){
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
    }

    private void calculatePercentage(){
        percentage = (total/5);
    }

    private void calculateResult(){
        for (int mark : marks) {
            if (mark < 33) {
                result = "Fail";
                return;
            }
        }
        result = "Pass";
    }

    public int getRollNumber(){return rollNo;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public int[] getMarks(){return marks;}
    public int getTotal(){return total;}
    public double getPercentage(){return percentage;}
    public String getResult(){return result;}
}