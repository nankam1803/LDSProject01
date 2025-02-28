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

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

    // Load CSV file into the students list (Now handles decimal scores)
    private static void loadCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // Skip header

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue; // Skip invalid lines
            String name = parts[1].trim();
            double score = Double.parseDouble(parts[2].trim()); // Fix: Now parsing doubles
            students.add(new Student(name, score));
        }
        br.close();
    }

    // Sort students by name using the chosen sorting algorithm
    private static void sortByName(Scanner scanner) {
        System.out.println("Choose a sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] names = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            names[i] = students.get(i).name;
        }

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

        System.out.println("Sorted by Name:");
        for (String name : names) {
            System.out.println(name);
        }
    }

    // Sort students by score using the chosen sorting algorithm
    private static void sortByScore(Scanner scanner) {
        System.out.println("Choose a sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double[] scores = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            scores[i] = students.get(i).score; // Fix: Using double[] now
        }

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

        System.out.println("Sorted Scores:");
        for (double score : scores) {
            System.out.println(score);
        }
    }

    // Search for a student using the chosen searching algorithm
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
    
        int index = -1;
        switch (choice) {
            case 1:
                index = SearchAlgorithms.linearSearch(names, name);
                break;
    
            case 2:
                if (!isSorted(names)) {
                    System.out.println("\nThe dataset is not sorted. Sorting now...");
    
                    students.sort(Comparator.comparing(s -> s.name));
    
                    System.out.println("Dataset sorted using Quick Sort.");
                }
    
                for (int i = 0; i < students.size(); i++) {
                    names[i] = students.get(i).name;
                }
    
                index = SearchAlgorithms.binarySearch(names, name, 0, names.length - 1);
                break;
    
            default:
                System.out.println("Invalid choice! Defaulting to Linear Search.");
                index = SearchAlgorithms.linearSearch(names, name);
        }
    
        if (index == -1) {
            System.out.println("Student not found!");
        } else {
            System.out.println("Found: " + students.get(index) +". Their Index is: " + index);
        }
    }
    
    private static boolean isSorted(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareToIgnoreCase(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static void benchmarkSortingPerformance() {
        String[] algorithms = {"Selection Sort", "Merge Sort", "Quick Sort"};

        System.out.println("\nSorting Performance Benchmark (Time in ms):");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-20s %-15s%n", "Algorithm", "Time (ms)");
        System.out.println("---------------------------------------------------");

        try {
            loadCSV("student.csv");
        } catch (IOException e) {
            System.out.println("Error loading CSV file: " + e.getMessage());
            return;
        }
        
        List<Student> dataset = new ArrayList<>(students);
        
        for (String algorithm : algorithms) {
            List<Student> tempList = new ArrayList<>(dataset);
            String[] names = new String[tempList.size()];
            double[] scores = new double[tempList.size()];
            
            for (int i = 0; i < tempList.size(); i++) {
                names[i] = tempList.get(i).name;
                scores[i] = tempList.get(i).score;
            }
            
            long startTime = System.nanoTime();
            
            switch (algorithm) {
                case "Selection Sort":
                    SortAlgorithms.selectionSort(names);
                    break;
                case "Merge Sort":
                    SortAlgorithms.mergeSort(names, 0, names.length - 1);
                    break;
                case "Quick Sort":
                    SortAlgorithms.quickSort(names, 0, names.length - 1);
                    break;
            }
            
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; 
            
            System.out.printf("%-20s %-15d%n", algorithm, duration);
        }
    }
    
}
