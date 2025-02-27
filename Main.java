import java.util.*;

public class Main {
    private static boolean isSorted = false; // Track sorting status

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = StudentData.readCSV("students.csv");

        while (true) {
            System.out.println("\nWelcome to the Student Search & Sort System!");
            System.out.println("1. Sort students by name");
            System.out.println("2. Sort students by score");
            System.out.println("3. Search for a student (Binary Search)");
            System.out.println("4. Compare sorting performance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectionSortByName(students); // Use any implemented sorting algorithm
                    isSorted = true;
                    System.out.println("Sorted by name: " + students);
                    break;

                case 2:
                    selectionSortByScore(students);
                    isSorted = true;
                    System.out.println("Sorted by score: " + students);
                    break;

                case 3:
                    if (!isSorted) {
                        System.out.println("The dataset is not sorted. Do you want to sort it now? (yes/no)");
                        String response = scanner.nextLine().trim().toLowerCase();
                        if (response.equals("yes")) {
                            selectionSortByName(students); // Sorting before Binary Search
                            isSorted = true;
                        } else {
                            System.out.println("Binary Search requires a sorted dataset.");
                            break;
                        }
                    }

                    System.out.print("Enter student name to search: ");
                    String name = scanner.nextLine();
                    String[] namesArray = students.stream().map(s -> s.name).toArray(String[]::new);
                    int index = SearchAlgorithms.binarySearch(namesArray, name, 0, namesArray.length - 1);
                    System.out.println(index != -1 ? "Student found at index " + index : "Not found");
                    break;

                case 4:
                    measureSortingPerformance();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Selection Sort by Name
    private static void selectionSortByName(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).name.compareTo(students.get(minIndex).name) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(students, i, minIndex);
        }
    }

    // Selection Sort by Score
    private static void selectionSortByScore(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).score < students.get(minIndex).score) {
                    minIndex = j;
                }
            }
            Collections.swap(students, i, minIndex);
        }
    }

    // Performance Benchmarking Placeholder
    private static void measureSortingPerformance() {
        System.out.println("Performance benchmarking to be implemented...");
    }
}
