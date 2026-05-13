import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person[] people = new Person[100];
        int personCount = 0;
        boolean running = true;

        System.out.println("=== Academic Role Management System ===");

        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a Student");
            System.out.println("2. Add a Teacher");
            System.out.println("3. Display all roles");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        if (personCount >= people.length) {
                            System.out.println("System is full! Cannot add more records.");
                            break;
                        }
                        System.out.print("Enter name: ");
                        String sName = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int sAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter student ID: ");
                        String sId = scanner.nextLine();
                        System.out.print("Enter major: ");
                        String sMajor = scanner.nextLine();
                        people[personCount] = new Student(sName, sAge, sId, sMajor);
                        personCount++;
                        System.out.println("Student added successfully!");
                        break;
                    case "2":
                        if (personCount >= people.length) {
                            System.out.println("System is full! Cannot add more records.");
                            break;
                        }
                        System.out.print("Enter name: ");
                        String tName = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int tAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter employee ID: ");
                        String tId = scanner.nextLine();
                        System.out.print("Enter subject taught: ");
                        String tSubject = scanner.nextLine();
                        people[personCount] = new Teacher(tName, tAge, tId, tSubject);
                        personCount++;
                        System.out.println("Teacher added successfully!");
                        break;
                    case "3":
                        if (personCount == 0) {
                            System.out.println("No records found.");
                        } else {
                            System.out.println("\n--- All Records ---");
                            for (int i = 0; i < personCount; i++) {
                                people[i].displayRoleDetails();
                            }
                        }
                        break;
                    case "4":
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for age.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
