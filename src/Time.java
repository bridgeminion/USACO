import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Time {

    static class Edge {
        int a;
        int b;
        public Edge (int a, int b){
            this.a = a;
            this.b = b;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("time.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("time.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] val = new int[n+1];
        List<Edge> list = new ArrayList<>();
        StringTokenizer s = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            val[i] = Integer.parseInt(s.nextToken());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            list.add(new Edge (a, b));
        }
        int[] cur = new int[n+1];
        int[] last = new int[n+1];
        for (int i=1; i <= n; i++){
            last[i] = -1;
            cur[i] = -1;
        }
        int ans = 0;
        last[1] = 0;
        for (int i=1; i <= 1000; i++){
            for (Edge j : list){
                if (last[j.a] != -1){
                    cur[j.b] = Math.max(cur[j.b], last[j.a] + val[j.b]);
                }
            }
            ans = Math.max(ans, cur[1] - c*i*i);
            for (int j=1; j <= n; j++){
                last[j] = cur[j];
            }
        }
        pw.println(ans);
        pw.close();
    }
}
