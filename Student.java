import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a student in the academic management system.
 * Demonstrates: inheritance, method overriding, and role-specific state.
 */
public class Student extends Person {

    private static final long serialVersionUID = 2L;

    public enum Year { FIRST, SECOND, THIRD, FOURTH, GRADUATE }

    private String       major;
    private double       gpa;
    private Year         year;
    private List<String> enrolledCourses;

    public Student(String id, String name, String email, int age,
                   String major, double gpa, Year year) {
        super(id, name, email, age);
        setGpa(gpa);
        this.major           = major;
        this.year            = year;
        this.enrolledCourses = new ArrayList<>();
    }

    // ── Role-specific getters / setters ──────────────────────────────────────
    public String       getMajor()          { return major; }
    public double       getGpa()            { return gpa;   }
    public Year         getYear()           { return year;  }
    public List<String> getEnrolledCourses(){ return Collections.unmodifiableList(enrolledCourses); }

    public void setMajor(String major) { this.major = major; }
    public void setYear(Year year)     { this.year  = year;  }
    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        this.gpa = gpa;
    }

    public void enrollInCourse(String course) {
        if (course != null && !course.isBlank() && !enrolledCourses.contains(course))
            enrolledCourses.add(course);
    }

    public boolean dropCourse(String course) {
        return enrolledCourses.remove(course);
    }

    // ── Overridden abstract methods ───────────────────────────────────────────
    @Override
    public String getRole() { return "Student"; }

    @Override
    public String getRoleDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("│  Major   : %-30s│%n", major));
        sb.append(String.format("│  Year    : %-30s│%n", year));
        sb.append(String.format("│  GPA     : %-30s│%n", String.format("%.2f / 4.00", gpa)));
        sb.append(String.format("│  Courses : %-30s│%n",
                enrolledCourses.isEmpty() ? "None enrolled"
                                          : String.join(", ", enrolledCourses)));
        return sb.toString();
    }

    // ── Override displayInfo for custom Student header ────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║           STUDENT PROFILE               ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.printf( "║  ID    : %-31s║%n", getId());
        System.out.printf( "║  Name  : %-31s║%n", getName());
        System.out.printf( "║  Email : %-31s║%n", getEmail());
        System.out.printf( "║  Age   : %-31s║%n", getAge());
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.printf( "║  Major : %-31s║%n", major);
        System.out.printf( "║  Year  : %-31s║%n", year);
        System.out.printf( "║  GPA   : %-31s║%n", String.format("%.2f / 4.00  %s", gpa, getAcademicStatus()));
        System.out.println("╠═════════════════════════════════════════╣");
        if (enrolledCourses.isEmpty()) {
            System.out.println("║  Courses: None enrolled                 ║");
        } else {
            System.out.println("║  Enrolled Courses:                      ║");
            for (String c : enrolledCourses)
                System.out.printf("║    • %-35s║%n", c);
        }
        System.out.println("╚═════════════════════════════════════════╝");
    }

    /** Returns an academic standing label based on GPA. */
    public String getAcademicStatus() {
        if (gpa >= 3.7) return "[Dean's List]";
        if (gpa >= 3.0) return "[Good Standing]";
        if (gpa >= 2.0) return "[Satisfactory]";
        return "[Academic Probation]";
    }

    @Override
    public String toString() {
        return String.format("[Student] %s | Major: %s | GPA: %.2f | Year: %s",
                getName(), major, gpa, year);
    }
}
