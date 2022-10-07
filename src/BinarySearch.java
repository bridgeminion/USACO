import java.io.IOException;

public class BinarySearch {

    public static boolean works (int n){
        return false;
    }

    public static void main(String[] args) throws IOException {
        int low = 0;
        int high = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (high+low)/2;
            if (works (mid)){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

}
