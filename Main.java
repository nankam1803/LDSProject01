import java.io.*;
import java.util.*;

public class Main {
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        try {
            loadCSV("student.csv");
        } catch (IOException e) {
            System.out.println("Error loading CSV file: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Student Search & Sort System!");
            System.out.println("1. Sort students by name");
            System.out.println("2. Sort students by score");
            System.out.println("3. Search for a student by name");
            System.out.println("4. Compare sorting performance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    sortByName(scanner);
                    break;
                case 2:
                    sortByScore(scanner);
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    benchmarkSortingPerformance();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    // Load CSV file into the students list
    private static void loadCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue; // Skip invalid lines
            String name = parts[1].trim();
            double score = Double.parseDouble(parts[2].trim());
            students.add(new Student(name, score));
        }
        br.close();
    }

    // ✅ Sort students by name and update the students list
    private static void sortByName(Scanner scanner) {
        System.out.println("Choose a sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Extract Names for Sorting
        String[] names = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            names[i] = students.get(i).name;
        }

        // Sort using the chosen algorithm
        switch (choice) {
            case 1:
                SortAlgorithms.selectionSort(names);
                break;
            case 2:
                SortAlgorithms.mergeSort(names, 0, names.length - 1);
                break;
            case 3:
                SortAlgorithms.quickSort(names, 0, names.length - 1);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Selection Sort.");
                SortAlgorithms.selectionSort(names);
        }

        // Update students list order
        students.sort(Comparator.comparing(s -> s.name));

        System.out.println("Sorted by Name:");
        for (Student s : students) {
            System.out.println(s.name);
        }
    }

    // ✅ Sort students by score and update the students list
    private static void sortByScore(Scanner scanner) {
        System.out.println("Choose a sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Extract Scores for Sorting
        double[] scores = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            scores[i] = students.get(i).score;
        }

        // Sort using the chosen algorithm
        switch (choice) {
            case 1:
                SortAlgorithms.selectionSort(scores);
                break;
            case 2:
                SortAlgorithms.mergeSort(scores, 0, scores.length - 1);
                break;
            case 3:
                SortAlgorithms.quickSort(scores, 0, scores.length - 1);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Selection Sort.");
                SortAlgorithms.selectionSort(scores);
        }

        // Update students list order
        students.sort(Comparator.comparingDouble(s -> s.score));

        System.out.println("Sorted Scores:");
        for (Student s : students) {
            System.out.println(s.name + " - " + s.score);
        }
    }

// ✅ Search for a student by name (Ensures sorted dataset before Binary Search)
// ✅ Search for a student by name (Ensures sorted dataset before Binary Search)
private static void searchStudent(Scanner scanner) {
    System.out.println("Choose a searching algorithm:");
    System.out.println("1. Linear Search");
    System.out.println("2. Binary Search (only works on sorted data)");
    System.out.print("Enter your choice: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    System.out.print("Enter the name of the student to search: ");
    String name = scanner.nextLine();

    // Convert student names into an array
    String[] names = new String[students.size()];
    for (int i = 0; i < students.size(); i++) {
        names[i] = students.get(i).name;
    }

    if (choice == 2) {
        // Check if the dataset is sorted before performing binary search
        if (!isSorted(names)) {
            System.out.println("\nThe dataset is not sorted. Please sort the data before trying binary search.");
            System.out.println("Returning to main menu...\n");
            return; // Exit the method to prevent incorrect binary search
        }
    }

    int index = -1;
    switch (choice) {
        case 1:
            index = SearchAlgorithms.linearSearch(names, name);
            break;

        case 2:
            index = SearchAlgorithms.binarySearch(names, name, 0, names.length - 1);
            break;

        default:
            System.out.println("Invalid choice! Defaulting to Linear Search.");
            index = SearchAlgorithms.linearSearch(names, name);
    }

    if (index == -1) {
        System.out.println("Student not found!");
    } else {
        System.out.println("Found: " + students.get(index) + ". Their Index is: " + index);
    }
}

// ✅ Fix for checking sorted dataset before Binary Search
private static boolean isSorted(String[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i].compareToIgnoreCase(arr[i + 1]) > 0) {
            return false; // Dataset is not sorted
        }
    }
    return true; // Dataset is sorted
}


    // ✅ Benchmark sorting performance
private static void benchmarkSortingPerformance() {
        System.out.println("\nSorting Performance Benchmark (Time in ms):");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-20s %-15s%n", "Algorithm", "Time (ms)");
        System.out.println("---------------------------------------------------");

        for (String algorithm : new String[]{"Selection Sort", "Merge Sort", "Quick Sort"}) {
            List<Student> tempList = new ArrayList<>(students);

            long startTime = System.nanoTime();
            tempList.sort(Comparator.comparing(s -> s.name));
            long endTime = System.nanoTime();

            System.out.printf("%-20s %-15d%n", algorithm, (endTime - startTime) / 1_000_000);
        }
}
}
