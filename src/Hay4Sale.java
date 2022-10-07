import java.io.*;
import java.util.StringTokenizer;

public class Hay4Sale {

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
