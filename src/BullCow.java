import java.io.*;
import java.util.StringTokenizer;

public class BullCow {

//    public static long dp(int num, int bull, int last, long[][] val, boolean[][] visited, int k, int n, int max_bull){
//        if (num==n){
//            return 1;
//        }
//        if (visited[num][bull]){
//            return val[num][bull];
//        }
//        visited[num][bull] = true;
//        if (Math.abs(num-last) >= k && bull < max_bull){
//            val[num][bull] += dp(num+1, bull+1, num, val, visited, k, n, max_bull);
//        }
//        val[num][bull] += dp(num+1, bull, last, val, visited, k, n, max_bull);
//        return val[num][bull];
//    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("censor.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
//        int max_bull = (n+1)/k;
//        long[][] val = new long[n+1][n+1];
////        long ans = dp (0, 0, -k, val, new boolean[n+1][max_bull+1], k, n, max_bull);
//        for (int i=1; i <= n; i++){
//            val[i][0] = 1;
//            val[i][1] = 1;
//        }
//        for (int i=1; i <= n; i++){
//            for (int j=1; j <= n; j++){
//                int temp = k;
//                while (j-temp >= 0){
//                    val[i][j] += val[i-1][j-temp];
//                    temp++;
//                }
//            }
//        }
        long[] val = new long[n+1];
        long cur = 0;
        for (int i=0; i <= k; i++){
            val[i] = 1;
        }
        for (int i=0; i < n-k; i++){
            cur += val[i];
            val[i+k+1] += cur%5000011;
        }
        long ans = 0;
        for (int i=0; i <= n; i++){
            ans += val[i]%5000011;
        }
        System.out.println(ans%5000011);
    }
}
