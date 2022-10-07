import java.io.*;
        import java.util.StringTokenizer;

public class Profits {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] profit = new int[n+1];
        for (int i=1; i <= n; i++){
            profit[i] = Integer.parseInt(br.readLine());
        }
        int ans = Integer.MIN_VALUE;
        for (int i=1; i <= n; i++){
            profit[i] = Math.max(profit[i], profit[i-1]+profit[i]);
            ans = Math.max(ans, profit[i]);
        }
        System.out.println(ans);
    }
}
