public class Teacher extends Person {
    private String employeeId;
    private String subject;

    public Teacher(String name, int age, String employeeId, String subject) {
        super(name, age);
        this.employeeId = employeeId;
        this.subject = subject;
    }

    @Override
    public void displayRoleDetails() {
        System.out.println("Role: Teacher");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Subject Taught: " + subject);
        System.out.println("-------------------------");
    }
}
