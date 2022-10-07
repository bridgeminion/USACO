import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class APPractice {





    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        long[][] num = new long[row][col];
        for (int i=0; i < row; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < col; j++){
                num[i][j] = Long.parseLong(st1.nextToken());
            }
        }
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < col; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                int count = 0;
                for (int k = 0; k < j; k++) {
                    if (num[i][j] != num[i - 1][k]) {
                        count += dp[i - 1][k];
                    }
                }
                dp[i][j] = count;
            }
        }
        int answer = 0;
        for (int i=1; i<col-1; i++) {
            answer += dp[row - 2][i];
        }
        System.out.println(answer);
    }
}
