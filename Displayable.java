/**
 * Interface for objects that can display their own information.
 * Demonstrates: interface usage and polymorphic behavior.
 */
public interface Displayable {

    /**
     * Display role-specific information to the console.
     */
    void displayInfo();

    /**
     * Default method: returns a short summary line.
     * Demonstrates: Java 8+ default interface methods.
     */
    default String getSummary() {
        return "Displayable entity — call displayInfo() for full details.";
    }
}
