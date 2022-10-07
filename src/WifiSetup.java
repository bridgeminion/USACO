import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WifiSetup {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double a = Integer.parseInt(st.nextToken());
        double b = Integer.parseInt(st.nextToken());
        int[] pos = new int[n];
        for (int i=0; i < n; i++) pos[i] = Integer.parseInt(br.readLine());
        Arrays.sort(pos);
        double[] dp = new double[n];
        dp[0] = a;
        for (int i=1; i < n; i++){
            dp[i] = dp[i-1] + Math.min(b/2*(pos[i] - pos[i-1]), a);
        }
        if (dp[n-1]*2 % 2 == 0){
            System.out.println((int)dp[n-1]);
        }
        else{
            System.out.println(dp[n-1]);

        }
    }
}
