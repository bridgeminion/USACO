import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Money {

    public static long dp (int n, int c, int[] coins, boolean[][] visited, long[][] val){
        if (n==0){
            if (c==0){
                return 1;
            }
            return 0;
        }
        if (visited[n][c]){
            return val[n][c];
        }
        visited[n][c] = true;
        if (coins[n] > c){
            val[n][c] = dp (n-1, c, coins, visited, val);
            return val[n][c];
        }
        val[n][c] = dp (n, c - coins[n], coins, visited, val) +  dp (n-1, c, coins, visited, val);
        return val[n][c];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] coins = new int[n+1];
        for (int i=1; i <= n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(dp(n, target, coins, new boolean[n+1][target+1], new long[n+1][target+1]));
    }
}
