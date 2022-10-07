import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AGame {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int counter = 0;
        while (counter < n){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            while (st1.hasMoreTokens()){
                list.add(Integer.parseInt(st1.nextToken()));
                counter++;
            }
        }
        int[][][] dp = new int[n+1][n+1][2];
        // number of numbers left, starting point, 0=player1's turn 1=player2's turn
        for (int i=1; i <= n; i++){
            dp[1][i][0] = list.get(i);
        }
        for (int i=2; i <= n; i++){
            for (int j=1; j <= n-i+1; j++){
                dp[i][j][0] = Math.max(dp[i-1][j][1] + list.get(i+j-1), dp[i-1][j+1][1] + list.get(j));
                if (dp[i-1][j][1] + list.get(i+j-1) > dp[i-1][j+1][1] + list.get(j)){
                    dp[i][j][1] = dp[i-1][j][0];
                }
                else {
                    dp[i][j][1] = dp[i-1][j+1][0];
                }
            }
        }
        int tot = 0;
        for (int i : list){
            tot += i;
        }
        System.out.print(dp[n][1][0] + " " + (tot - dp[n][1][0]));
    }
}
