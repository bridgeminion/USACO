import java.io.*;
import java.util.StringTokenizer;

public class MaxCross2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("maxcross.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[] on = new boolean[n+1];
        for (int i=1; i <= n; i++){
            on[i] = true;
        }
        for (int i=0; i < b; i++){
            int temp = Integer.parseInt(br.readLine());
            on[temp] = false;
        }
        int needed = 0;
        for (int i=1; i <= k; i++){
            if (!on[i]) needed++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i=k+1; i <= n; i++){
            if (on[i]){
                needed--;
            }
            if (on[i-k]){
                needed++;
            }
            ans = Math.min(ans, needed);
        }
        pw.println(ans);
        pw.close();
    }
}
