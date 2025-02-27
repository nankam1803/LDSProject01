import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = StudentData.readCSV("students.csv");

        while (true) {
            System.out.println("1. Sort students by name");
            System.out.println("2. Sort students by score");
            System.out.println("3. Search for a student");
            System.out.println("4. Compare sorting performance");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    students.sort((a, b) -> a.name.compareTo(b.name));
                    System.out.println("Sorted by name: " + students);
                    break;
                case 2:
                    students.sort((a, b) -> Integer.compare(a.score, b.score));
                    System.out.println("Sorted by score: " + students);
                    break;
                case 3:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    int index = SearchAlgorithms.linearSearch(students.stream().map(s -> s.name).toArray(String[]::new), name);
                    System.out.println(index != -1 ? "Student found at index " + index : "Not found");
                    break;
                case 4:
                    // Measure sorting performance
                    break;
                case 5:
                    return;
            }
        }
    }
}
