import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OnTheRun {

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
        Arrays.sort(pos);
        int[][][] dp = new int[n][n][2];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (i+1 < n){
                    dp[i][j][0] = Math.min(dp[i+1][j][0] + Math.abs(pos[i] - pos[i+1]), dp[i+1][j][1] + Math.abs(pos[i] - pos[j]));
                }
                if (j-1 > 0){
                    dp[i][j][1] = Math.min(dp[i][j-1][1] + Math.abs(pos[j] - pos[j-1]), dp[i][j-1][0] + Math.abs(pos[i] - pos[j]));
                }
            }
        }
        System.out.println(Math.min(dp[0][n-1][0], dp[0][n-1][1]));
    }
}
