public class SearchAlgorithms {

    // Linear Search
    public static int linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search (Recursive)
    public static int binarySearch(String[] arr, String key, int left, int right) {
        if (left > right) return -1;
        
        int mid = left + (right - left) / 2;
        int cmp = arr[mid].compareTo(key);

        if (cmp == 0) return mid;
        else if (cmp > 0) return binarySearch(arr, key, left, mid - 1);
        else return binarySearch(arr, key, mid + 1, right);
    }
}
