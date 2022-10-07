import java.io.*;
import java.util.StringTokenizer;

public class SMount {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(br.readLine());
        }
        int cur = 1;
        int last = 1;
        boolean increasing = true;
        int ans = 0;
        for (int i=1; i < n; i++){
            if (h[i] > h[i-1]){
                if (increasing){
                    cur++;
                }
                else {
                    increasing = true;
                    ans = Math.max(ans, cur);
                    cur = last+1;
                    last = 1;
                }
            }
            else if (h[i] < h[i-1]){
                increasing = false;
                cur++;
            }
            else {
                if (increasing){
                    cur++;
                }
                else {
                    cur++;
                    last++;
                }
            }
        }
        ans = Math.max(ans, cur);
        ans = Math.max(ans, last);
        System.out.println(ans);
    }
}
