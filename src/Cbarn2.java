import java.io.*;
import java.util.StringTokenizer;

public class Cbarn2 {

    public static long dp (int start, int[] amt, int n, int k){
        long[][] dp = new long[k+1][n];
        for (int i=0; i <= k; i++){
            for (int j=0; j < n; j++){
                dp[i][j] = -1;
            }
        }
        dp[1][0] = 0;
        long ans = Long.MAX_VALUE;
        for (int i=1; i < k; i++){
            for (int j=0; j < n; j++){
                if (dp[i][j] != -1){
                    long cost = 0;
                    for (int a=j+1; a < n; a++){
                        cost += amt[(start+a-1)%n]*(a-j-1);
                        if (dp[i+1][a] == -1 || dp[i+1][a] > dp[i][j] + cost){
                            dp[i+1][a] = dp[i][j] + cost;
                        }
                    }
                }
            }
        }
        for (int i=1; i < n; i++){
            int cost = 0;
            for (int j=i+1; j < n; j++){
                cost += amt[(start+j)%n]*(j-i);
            }
            ans = Math.min(ans, dp[k][i] + cost);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] amt = new int[n];
        for (int i=0; i < n; i++){
            amt[i] = Integer.parseInt(br.readLine());
        }
        long ans = Long.MAX_VALUE;
        for (int i=0; i < n; i++){
            ans = Math.min(ans, dp (i, amt, n, k));
        }
        System.out.println(ans);
    }
}
