import java.io.*;
import java.util.*;

public class Loan {

    public static long ans = 0;

    public static boolean test (long n, long k, long m, long x){
        long g = 0;
        while (k > 0 && g < n){
            long y = (n-g)/x;
            if (y < m){
                return m*k >= n-g;
            }
            long numdays = (n-g-x*y)/y + 1;
            if (numdays > k){
                numdays = k;
            }
            g += y*numdays;
            k -= numdays;
        }
        return g >= n;
    }

    public static void binarySearch (long n, long k, long m){
        long low = 0;
        long high = n/m + 1;
        while (low <= high) {
            long mid = (high+low)/2;
            if (test (n, k, m, mid)){
                ans = Math.max(ans, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("loan.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("loan.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        binarySearch(n, k, m);
        pw.println(ans);
        pw.close();
    }
}
