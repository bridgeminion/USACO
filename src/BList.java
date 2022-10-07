import java.io.*;
import java.util.StringTokenizer;

public class BList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("blist.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("blist.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[] time = new int[1001];
        int ans = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int bucket = Integer.parseInt(st.nextToken());
            for (int j=start; j <= end; j++){
                time[j] += bucket;
            }
        }
        for (int i=0; i < 1000; i++){
            if (time[i] > ans){
                ans = time[i];
            }
        }
        pw.println(ans);
        pw.close();
    }
}
