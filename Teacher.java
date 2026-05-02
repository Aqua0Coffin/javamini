import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a teacher/faculty member in the academic management system.
 * Demonstrates: inheritance, method overriding, and role-specific state.
 */
public class Teacher extends Person {

    private static final long serialVersionUID = 3L;

    public enum Rank { LECTURER, ASSISTANT_PROFESSOR, ASSOCIATE_PROFESSOR, FULL_PROFESSOR, EMERITUS }

    private String       department;
    private Rank         rank;
    private double       salary;
    private List<String> coursesTaught;
    private String       researchArea;

    public Teacher(String id, String name, String email, int age,
                   String department, Rank rank, double salary, String researchArea) {
        super(id, name, email, age);
        setSalary(salary);
        this.department   = department;
        this.rank         = rank;
        this.researchArea = researchArea;
        this.coursesTaught = new ArrayList<>();
    }

    // ── Role-specific getters / setters ──────────────────────────────────────
    public String       getDepartment()    { return department;   }
    public Rank         getRank()          { return rank;         }
    public double       getSalary()        { return salary;       }
    public String       getResearchArea()  { return researchArea; }
    public List<String> getCoursesTaught() { return Collections.unmodifiableList(coursesTaught); }

    public void setDepartment(String department)  { this.department   = department;   }
    public void setRank(Rank rank)                { this.rank         = rank;         }
    public void setResearchArea(String area)      { this.researchArea = area;         }
    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative.");
        this.salary = salary;
    }

    public void addCourse(String course) {
        if (course != null && !course.isBlank() && !coursesTaught.contains(course))
            coursesTaught.add(course);
    }

    public boolean removeCourse(String course) {
        return coursesTaught.remove(course);
    }

    // ── Overridden abstract methods ───────────────────────────────────────────
    @Override
    public String getRole() { return "Teacher"; }

    @Override
    public String getRoleDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("│  Dept    : %-30s│%n", department));
        sb.append(String.format("│  Rank    : %-30s│%n", rank.toString().replace('_', ' ')));
        sb.append(String.format("│  Research: %-30s│%n", researchArea));
        sb.append(String.format("│  Courses : %-30s│%n",
                coursesTaught.isEmpty() ? "None assigned"
                                        : String.join(", ", coursesTaught)));
        return sb.toString();
    }

    // ── Override displayInfo for custom Teacher header ────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║           FACULTY PROFILE               ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.printf( "║  ID    : %-31s║%n", getId());
        System.out.printf( "║  Name  : %-31s║%n", getName());
        System.out.printf( "║  Email : %-31s║%n", getEmail());
        System.out.printf( "║  Age   : %-31s║%n", getAge());
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.printf( "║  Dept  : %-31s║%n", department);
        System.out.printf( "║  Rank  : %-31s║%n", rank.toString().replace('_', ' '));
        System.out.printf( "║  Res.  : %-31s║%n", researchArea);
        System.out.printf( "║  Salary: %-31s║%n", String.format("$%,.2f / year", salary));
        System.out.println("╠═════════════════════════════════════════╣");
        if (coursesTaught.isEmpty()) {
            System.out.println("║  Courses: None assigned                 ║");
        } else {
            System.out.println("║  Courses Taught:                        ║");
            for (String c : coursesTaught)
                System.out.printf("║    • %-35s║%n", c);
        }
        System.out.println("╚═════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return String.format("[Teacher] %s | Dept: %s | Rank: %s",
                getName(), department, rank.toString().replace('_', ' '));
    }
}
