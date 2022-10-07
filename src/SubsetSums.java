import java.io.*;
import java.util.*;

public class SubsetSums {

    public static long dp(int a, int b, boolean[][] visited, long[][] val, int[] arr){
        if (a==0){
            if (b==0){
                return 1;
            }
            return 0;
        }
        if (b < 0){
            return 0;
        }
        if (visited[a][b]){
            return val[a][b];
        }
        visited[a][b] = true;
        val[a][b] = dp (a-1, b, visited, val, arr) + dp (a-1, b-arr[a], visited, val, arr);
        return val[a][b];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int sum = n*(n+1)/2;
        if (sum%2==1){
            System.out.println(0);
            return;
        }
        int[] arr = new int[n+1];
        for (int i=0; i < n; i++){
            arr[i+1] = i+1;
        }
        System.out.println(dp (n, sum/2, new boolean[n+1][sum/2+1], new long[n+1][sum/2+1], arr)/2);
    }
}
