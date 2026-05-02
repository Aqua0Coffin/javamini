import java.io.Serializable;

/**
 * Abstract base class representing a person in the academic system.
 * Demonstrates: abstract classes, encapsulation, and Serializable for file storage.
 */
public abstract class Person implements Serializable, Displayable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private int age;

    public Person(String id, String name, String email, int age) {
        if (id == null || id.isBlank())    throw new IllegalArgumentException("ID cannot be empty.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty.");
        if (age < 1 || age > 120)          throw new IllegalArgumentException("Age must be between 1 and 120.");
        this.id    = id;
        this.name  = name;
        this.email = email;
        this.age   = age;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }
    public int    getAge()   { return age; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) {
        if (age < 1 || age > 120) throw new IllegalArgumentException("Age must be between 1 and 120.");
        this.age = age;
    }

    /**
     * Abstract method: each role provides its own role label.
     */
    public abstract String getRole();

    /**
     * Abstract method: each role provides extended role-specific details.
     */
    public abstract String getRoleDetails();

    /**
     * Default display implementation — can be overridden (Displayable).
     */
    @Override
    public void displayInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf( "│  Role  : %-31s│%n", getRole());
        System.out.printf( "│  ID    : %-31s│%n", id);
        System.out.printf( "│  Name  : %-31s│%n", name);
        System.out.printf( "│  Email : %-31s│%n", email);
        System.out.printf( "│  Age   : %-31s│%n", age);
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println(getRoleDetails());
        System.out.println("└─────────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (ID: %s)", getRole(), name, id);
    }
}
