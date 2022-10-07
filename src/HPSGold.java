import java.io.*;
import java.util.StringTokenizer;

public class HPSGold {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("hps.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] moves = new int[n+1];
        int[][][] dp = new int[n+1][k+1][3];
        int[] counter = new int[3];
        for (int i=1; i <= n; i++){
            String temp = br.readLine();
            if (temp.equals("P")){
                moves[i] = 1;
            }
            else if (temp.equals("S")){
                moves[i] = 2;
            }
            counter[(moves[i]+1)%3]++;
            for (int j=0; j < 3; j++){
                dp[i][0][j] = counter[j];
            }
        }
        for (int i=1; i <= n; i++){
            for (int j=1; j <= k; j++){
                for (int a=0; a < 3; a++){
                    if (a == moves[i]+1 || (a==0 && moves[i]==2)){
                        dp[i][j][a] = Math.max(dp[i-1][j][a], Math.max(dp[i-1][j-1][(a+1)%3], dp[i-1][j-1][(a+2)%3])) + 1;
                    }
                    else {
                        dp[i][j][a] = Math.max(dp[i-1][j][a], Math.max(dp[i-1][j-1][(a+1)%3], dp[i-1][j-1][(a+2)%3]));
                    }
                }
            }
        }
        pw.println(Math.max(dp[n][k][0], Math.max(dp[n][k][1], dp[n][k][2])));
        pw.close();
    }
}
