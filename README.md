# Academic Role Management System

A Java console application that manages Students and Teachers using
core Object-Oriented Programming principles including abstraction,
inheritance, polymorphism, and file persistence.

---

## 📁 Project Files

| File | Description |
|---|---|
| `Person.java` | Abstract base class for all persons |
| `Student.java` | Student subclass (GPA, major, courses) |
| `Teacher.java` | Teacher subclass (rank, department, salary) |
| `Displayable.java` | Interface for displaying role info |
| `RoleManager.java` | Central registry for students and teachers |
| `FileStorageManager.java` | Saves and loads data using Java Serialization |
| `Main.java` | Entry point — interactive console menu |

---

## ⚙️ Requirements

- **Java JDK 17 or higher**
- A terminal / command prompt

To check your Java version:
```bash
java -version
```

---

## 🔨 How to Compile

Open a terminal in the project folder (where the `.java` files are),
then run:

```bash
javac *.java
```

This compiles all source files at once and generates `.class` files.

---

## ▶️ How to Run

After compiling, start the program with:

```bash
java Main
```

You will see the interactive menu:

```
╔══════════════════════════════════════╗
║   === Academic Role Management ===   ║
╠══════════════════════════════════════╣
║  1. Add Student                      ║
║  2. Add Teacher                      ║
║  3. View All                         ║
║  4. View Students Only               ║
║  5. View Teachers Only               ║
║  6. Save to File                     ║
║  0. Exit                             ║
╚══════════════════════════════════════╝
```

---

## 💾 Data Persistence

- Selecting **option 6** saves all data to the `data/` folder:
  - `data/students.dat`
  - `data/teachers.dat`
- Data is **automatically loaded** the next time you run the program.

---

## 🗂️ Compile & Run (One-liner)

**Windows (PowerShell):**
```powershell
javac *.java; java Main
```

**macOS / Linux (Bash):**
```bash
javac *.java && java Main
```

---

## 🧹 Clean Compiled Files

To remove all `.class` files and start fresh:

**Windows (PowerShell):**
```powershell
Remove-Item *.class
```

**macOS / Linux:**
```bash
rm *.class
```