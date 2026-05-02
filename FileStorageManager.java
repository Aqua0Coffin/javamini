import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Handles saving and loading Person objects via Java Object Serialization.
 *
 * Storage layout:
 *   data/students.dat  — serialized List<Student>
 *   data/teachers.dat  — serialized List<Teacher>
 */
public class FileStorageManager {

    private static final String DATA_DIR      = "data";
    private static final String STUDENTS_FILE = DATA_DIR + "/students.dat";
    private static final String TEACHERS_FILE = DATA_DIR + "/teachers.dat";

    public FileStorageManager() {
        try { Files.createDirectories(Paths.get(DATA_DIR)); }
        catch (IOException e) { System.err.println("Warning: " + e.getMessage()); }
    }

    @SuppressWarnings("unchecked")
    public List<Student> loadStudents() { return (List<Student>) loadList(STUDENTS_FILE); }
    public void saveStudents(List<Student> s) { saveList(s, STUDENTS_FILE); }

    @SuppressWarnings("unchecked")
    public List<Teacher> loadTeachers() { return (List<Teacher>) loadList(TEACHERS_FILE); }
    public void saveTeachers(List<Teacher> t) { saveList(t, TEACHERS_FILE); }

    private void saveList(List<?> list, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)))) {
            oos.writeObject(list);
            System.out.println("  ✔  Saved " + list.size() + " record(s) → " + path);
        } catch (IOException e) {
            System.err.println("  ✘  Save failed: " + e.getMessage());
        }
    }

    private List<?> loadList(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("  ℹ  No file at " + path + " — starting fresh.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            List<?> list = (List<?>) ois.readObject();
            System.out.println("  ✔  Loaded " + list.size() + " record(s) ← " + path);
            return list;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("  ✘  Load failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void clearAll() {
        for (String p : new String[]{STUDENTS_FILE, TEACHERS_FILE}) {
            try { Files.deleteIfExists(Paths.get(p)); } catch (IOException ignored) {}
        }
        System.out.println("  ✔  All data files cleared.");
    }
}
