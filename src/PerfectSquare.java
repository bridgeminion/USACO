import java.io.*;
import java.util.*;

public class PerfectSquare {

    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i=1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i=1; i <= n; i++){
            for (int j=1; j <= n; j++){
                if (i - j*j < 0){
                    break;
                }
                dp[i] = Math.min(dp[i-j*j] + 1, dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        System.out.println(numSquares(n));
    }
}
