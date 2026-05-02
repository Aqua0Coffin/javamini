import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║          Academic Role Management System — Interactive Menu      ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║  Features:                                                       ║
 * ║    • Add Student / Teacher interactively                         ║
 * ║    • View All, Students Only, Teachers Only                      ║
 * ║    • Save to file (Java Serialization)                           ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
public class Main {

    private static final Scanner sc      = new Scanner(System.in);
    private static final RoleManager mgr = new RoleManager();

    public static void main(String[] args) {

        // Load any previously saved data on startup
        mgr.loadAll();

        int choice;
        do {
            printMenu();
            choice = readInt("Choose: ");
            System.out.println();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> mgr.displayAll();
                case 4 -> mgr.displayAllStudents();
                case 5 -> mgr.displayAllTeachers();
                case 6 -> {
                    mgr.saveAll();
                    System.out.println("  ✔  Data saved successfully.");
                }
                case 0 -> System.out.println("\n  Goodbye!\n");
                default -> System.out.println("  ✘  Invalid option. Please enter 0-6.\n");
            }

        } while (choice != 0);
    }

    // ── Menu ─────────────────────────────────────────────────────────────────

    private static void printMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   === Academic Role Management ===   ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Add Student                      ║");
        System.out.println("║  2. Add Teacher                      ║");
        System.out.println("║  3. View All                         ║");
        System.out.println("║  4. View Students Only               ║");
        System.out.println("║  5. View Teachers Only               ║");
        System.out.println("║  6. Save to File                     ║");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ── Add Student ───────────────────────────────────────────────────────────

    private static void addStudent() {
        System.out.println("┌─── Add New Student ───────────────────┐");

        String id    = readString("  ID          : ");
        String name  = readString("  Name        : ");
        String email = readString("  Email       : ");
        int    age   = readInt   ("  Age         : ");
        String major = readString("  Major       : ");
        double gpa   = readDouble("  GPA (0-4.0) : ");

        System.out.println("  Year options: 1=FIRST  2=SECOND  3=THIRD  4=FOURTH  5=GRADUATE");
        int yearChoice = readInt("  Year        : ");
        Student.Year year = switch (yearChoice) {
            case 2 -> Student.Year.SECOND;
            case 3 -> Student.Year.THIRD;
            case 4 -> Student.Year.FOURTH;
            case 5 -> Student.Year.GRADUATE;
            default -> Student.Year.FIRST;
        };

        try {
            Student s = new Student(id, name, email, age, major, gpa, year);

            // Optional courses
            System.out.print("  Enroll in courses? (y/n): ");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("  Enter course names one per line. Blank line to stop:");
                while (true) {
                    System.out.print("    Course: ");
                    String course = sc.nextLine().trim();
                    if (course.isEmpty()) break;
                    s.enrollInCourse(course);
                }
            }

            mgr.addStudent(s);
            System.out.println("└───────────────────────────────────────┘");
            System.out.println("  ✔  Student added: " + s + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✘  Error: " + e.getMessage() + "\n");
        }
    }

    // ── Add Teacher ───────────────────────────────────────────────────────────

    private static void addTeacher() {
        System.out.println("┌─── Add New Teacher ───────────────────┐");

        String id     = readString("  ID           : ");
        String name   = readString("  Name         : ");
        String email  = readString("  Email        : ");
        int    age    = readInt   ("  Age          : ");
        String dept   = readString("  Department   : ");
        double salary = readDouble("  Salary ($)   : ");
        String research = readString("  Research Area: ");

        System.out.println("  Rank options:");
        System.out.println("    1 = Lecturer");
        System.out.println("    2 = Assistant Professor");
        System.out.println("    3 = Associate Professor");
        System.out.println("    4 = Full Professor");
        System.out.println("    5 = Emeritus");
        int rankChoice = readInt("  Rank         : ");
        Teacher.Rank rank = switch (rankChoice) {
            case 2 -> Teacher.Rank.ASSISTANT_PROFESSOR;
            case 3 -> Teacher.Rank.ASSOCIATE_PROFESSOR;
            case 4 -> Teacher.Rank.FULL_PROFESSOR;
            case 5 -> Teacher.Rank.EMERITUS;
            default -> Teacher.Rank.LECTURER;
        };

        try {
            Teacher t = new Teacher(id, name, email, age, dept, rank, salary, research);

            // Optional courses
            System.out.print("  Add courses taught? (y/n): ");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("  Enter course names one per line. Blank line to stop:");
                while (true) {
                    System.out.print("    Course: ");
                    String course = sc.nextLine().trim();
                    if (course.isEmpty()) break;
                    t.addCourse(course);
                }
            }

            mgr.addTeacher(t);
            System.out.println("└───────────────────────────────────────┘");
            System.out.println("  ✔  Teacher added: " + t + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✘  Error: " + e.getMessage() + "\n");
        }
    }

    // ── Input Helpers ─────────────────────────────────────────────────────────

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  ✘  Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  ✘  Please enter a valid decimal number.");
            }
        }
    }
}
