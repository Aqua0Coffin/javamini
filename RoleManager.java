import java.util.*;
import java.util.stream.*;

/**
 * Central registry managing Students and Teachers.
 * Demonstrates: generic collections, streams, polymorphism.
 */
public class RoleManager {

    private final Map<String, Student> students = new LinkedHashMap<>();
    private final Map<String, Teacher> teachers = new LinkedHashMap<>();
    private final FileStorageManager   storage  = new FileStorageManager();

    // ── Add / Remove ──────────────────────────────────────────────────────────

    public void addStudent(Student s) {
        if (students.containsKey(s.getId()))
            throw new IllegalArgumentException("Student ID already exists: " + s.getId());
        students.put(s.getId(), s);
    }

    public void addTeacher(Teacher t) {
        if (teachers.containsKey(t.getId()))
            throw new IllegalArgumentException("Teacher ID already exists: " + t.getId());
        teachers.put(t.getId(), t);
    }

    public boolean removeStudent(String id) { return students.remove(id) != null; }
    public boolean removeTeacher(String id) { return teachers.remove(id) != null; }

    // ── Lookup ────────────────────────────────────────────────────────────────

    public Optional<Student> findStudent(String id) { return Optional.ofNullable(students.get(id)); }
    public Optional<Teacher> findTeacher(String id) { return Optional.ofNullable(teachers.get(id)); }

    /** Search by name (case-insensitive partial match) across both roles. */
    public List<Person> searchByName(String query) {
        String q = query.toLowerCase();
        List<Person> results = new ArrayList<>();
        students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(q))
                .forEach(results::add);
        teachers.values().stream()
                .filter(t -> t.getName().toLowerCase().contains(q))
                .forEach(results::add);
        return results;
    }

    // ── Display all ───────────────────────────────────────────────────────────

    public void displayAllStudents() {
        System.out.println("\n══════════ ALL STUDENTS (" + students.size() + ") ══════════");
        if (students.isEmpty()) { System.out.println("  No students registered."); return; }
        students.values().forEach(s -> { s.displayInfo(); System.out.println(); });
    }

    public void displayAllTeachers() {
        System.out.println("\n══════════ ALL TEACHERS (" + teachers.size() + ") ══════════");
        if (teachers.isEmpty()) { System.out.println("  No teachers registered."); return; }
        teachers.values().forEach(t -> { t.displayInfo(); System.out.println(); });
    }

    /** Polymorphic display — works on any Person. */
    public void displayAll() {
        List<Person> all = new ArrayList<>();
        all.addAll(students.values());
        all.addAll(teachers.values());
        System.out.println("\n══════════ ALL PERSONNEL (" + all.size() + ") ══════════");
        all.forEach(p -> { p.displayInfo(); System.out.println(); });
    }

    // ── Statistics ────────────────────────────────────────────────────────────

    public void printSummary() {
        System.out.println("\n┌──── REGISTRY SUMMARY ───────────────────┐");
        System.out.printf( "│  Students : %-28d│%n", students.size());
        System.out.printf( "│  Teachers : %-28d│%n", teachers.size());
        OptionalDouble avgGpa = students.values().stream()
                .mapToDouble(Student::getGpa).average();
        System.out.printf( "│  Avg GPA  : %-28s│%n",
                avgGpa.isPresent() ? String.format("%.2f", avgGpa.getAsDouble()) : "N/A");
        long deansList = students.values().stream()
                .filter(s -> s.getGpa() >= 3.7).count();
        System.out.printf( "│  Dean's List students: %-17d│%n", deansList);
        System.out.println("└─────────────────────────────────────────┘");
    }

    // ── Persistence ───────────────────────────────────────────────────────────

    public void saveAll() {
        System.out.println("\n── Saving to disk ──");
        storage.saveStudents(new ArrayList<>(students.values()));
        storage.saveTeachers(new ArrayList<>(teachers.values()));
    }

    public void loadAll() {
        System.out.println("\n── Loading from disk ──");
        storage.loadStudents().forEach(s -> students.put(s.getId(), s));
        storage.loadTeachers().forEach(t -> teachers.put(t.getId(), t));
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public Collection<Student> getStudents() { return Collections.unmodifiableCollection(students.values()); }
    public Collection<Teacher> getTeachers() { return Collections.unmodifiableCollection(teachers.values()); }
}
