import java.io.*;
import java.util.StringTokenizer;

public class FinickyGrazers {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] pos = new int[n];
        for (int i=0; i < n; i++){
            pos[i] = Integer.parseInt(br.readLine());
        }
        int d = (l-1)/n;
        int[][] dp = new int[n+1][l+1];
        dp[1][0] = pos[0];
        for (int i=2; i <= n; i++){
            for (int j=d*i; j <= (d+1)*i; j++){
                dp[i][j] = Integer.MAX_VALUE;
                if (j-d >= 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-d] + Math.abs(pos[i-1]-j));
                }
                if (j-d-1 >= 0){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-d-1] + Math.abs(pos[i-1]-j));
                }
            }
        }
        System.out.println(dp[n][l]);
    }
}
