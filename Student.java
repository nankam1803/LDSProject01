import java.io.*;
import java.util.*;

public class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " (" + score + ")";
    }
}

class StudentData {
    public static List<Student> readCSV(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header\
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                students.add(new Student(parts[1].trim(), Integer.parseInt(parts[2].trim())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
