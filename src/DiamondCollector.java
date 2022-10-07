import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DiamondCollector {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("diamond.out"));
//       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] size = new int[n];
        for (int i=0; i < n; i++){
            size[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(size);
        int[] Lans = new int[n];
        int[] Rans = new int[n];
        int l = 0;
        int r = 0;
        while (l <= r){
            while (r < n && size[r] - size[l] <= k){
                Lans[r] = Math.max(Lans[r], r-l+1);
                r++;
            }
            l++;
        }
        for (int i=1; i < n; i++){
            Lans[i] = Math.max(Lans[i], Lans[i-1]);
        }
        l = n-1;
        r = n-1;
        while (l <= r){
            while (l >= 0 && size[r] - size[l] <= k){
                Rans[l] = Math.max(Rans[l], r-l+1);
                l--;
            }
            r--;
        }
        for (int i=n-2; i >= 0; i--){
            Rans[i] = Math.max(Rans[i], Rans[i+1]);
        }
        int ans = 1;
        for (int i=0; i < n-1; i++){
            ans = Math.max(ans, Lans[i] + Rans[i+1]);
        }
//        for (int i=0; i < n; i++){
//            System.out.println(Lans[i] + " " + Rans[i]);
//        }
        pw.println(ans);
        pw.close();
    }
}
