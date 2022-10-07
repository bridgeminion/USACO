import java.io.*;
import java.util.StringTokenizer;

public class Diet {

    public static long dp (int n, int c, int[] weight, boolean[][] visited, long[][] val){
        if (n==0){
            return 0;
        }
        if (visited[n][c]){
            return val[n][c];
        }
        visited[n][c] = true;
        if (weight[n] > c){
            val[n][c] = dp (n-1, c, weight, visited, val);
            return val[n][c];
        }
        val[n][c] = Math.max(dp (n-1, c - weight[n], weight, visited, val) + weight[n], dp (n-1, c, weight, visited, val));
        return val[n][c];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] weight = new int[n+1];
        for (int i=1; i <= n; i++){
            weight[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(dp(n, target, weight, new boolean[n+1][target+1], new long[n+1][target+1]));
        long[] cur = new long[target+1];
        long[] prev = new long[target+1];
        for (int i=1; i <= n; i++){
            for (int j=1; j <= target; j++){
                if (weight[i] > j){
                    cur[j] = prev[j];
                }
                else {
                    cur[j] = Math.max(prev[j-weight[i]] + weight[i], prev[j]);
                }
            }
            for (int j=1; j <= target; j++){
                prev[j] = cur[j];
            }
        }
        System.out.println(cur[target]);
    }
}
