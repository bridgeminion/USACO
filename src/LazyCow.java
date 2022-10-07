import java.io.*;
import java.util.StringTokenizer;

public class LazyCow {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        int k = Integer.parseInt(st1.nextToken());
        int[][][] dp = new int[k+1][n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                dp[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=1; i <= k; i++){
            for (int a=0; a < n; a++){
                for (int b=0; b < n; b++){
                    for (int j=0; j < 4; j++){
                        int nr = a+dr[j];
                        int nc = b+dc[j];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n){
                            dp[i][a][b] += dp[i-1][nr][nc];
                        }
                    }
                }
            }
        }
        int ans = 0;
        int r = 0;
        int c = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (dp[k][i][j] > ans){
                    ans = dp[k][i][j];
                    r = i;
                    c = j;
                }
            }
        }
        System.out.println(ans);
        System.out.println(r);
        System.out.println(c);
    }
}
