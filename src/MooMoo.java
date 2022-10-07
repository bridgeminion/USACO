import java.io.*;
import java.util.StringTokenizer;

public class MooMoo {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] vol = new int[b];
        for (int i=0; i < b; i++){
            vol[i] = Integer.parseInt(br.readLine());
        }
        int[] total = new int[n];
        for (int i=0; i < n; i++){
            total[i] = Integer.parseInt(br.readLine());
        }
        int size = 1000000;
        int[] min = new int[size];
        boolean[] valid = new boolean[size];
        valid[0] = true;
        for (int i=1; i < size; i++){
            min[i] = Integer.MAX_VALUE;
        }
        for (int i=1; i < size; i++){
            for (int j : vol){
                if (i-j >= 0 && valid[i-j]){
                    valid[i] = true;
                    min[i] = Math.min(min[i], min[i-j]+1);
                }
            }
        }
        int ans = 0;
        for (int i=n-1; i >= 1; i--){
            total[i] -= total[i-1];
            if (total[i-1] != 0){
                total[i]++;
            }
        }
        for (int i=0; i < n; i++){
            if (!valid[total[i]]){
                System.out.println(-1);
                return;
            }
            ans += min[total[i]];
        }
        System.out.println(ans);
    }
}
