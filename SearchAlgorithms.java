public class SearchAlgorithms {

    public static int linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(String[] arr, String key, int low, int high) {
        if (low > high) 
        {
            return -1;
        }
        
        int mid = low + (high - low) / 2;
        int compare = arr[mid].compareTo(key);

        if (compare == 0) 
        {
            return mid;
        }

        else if (compare > 0) 
        {
            return binarySearch(arr, key, low, mid - 1);
        }

        else 
        {
            return binarySearch(arr, key, mid + 1, high);
        }
    }
}
