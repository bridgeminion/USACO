import java.io.*;
import java.util.StringTokenizer;

public class Snakes {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("snakes.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int[] amt = new int[n];
        int[][] dp = new int[n+1][k+1];
        int high = 0;
        int total = 0;
        for (int i=0; i < n; i++){
            amt[i] = Integer.parseInt(st1.nextToken());
            high = Math.max(high, amt[i]);
            dp[i+1][0] = high*(i+1);
            total += amt[i];
        }
        for (int i=1; i <= n; i++){
            for (int j=1; j <= k; j++){
                dp[i][j] = Integer.MAX_VALUE;
                int max = 0;
                for (int a=i-1; a >= 0; a--){
                    max = Math.max(max, amt[a]);
                    dp[i][j] = Math.min(dp[i][j], dp[a][j-1] + (i-a)*max);
                }
            }
        }
        pw.println(dp[n][k]-total);
        pw.close();
    }
}
