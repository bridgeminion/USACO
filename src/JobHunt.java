import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JobHunt {

    static class Edge{
        int start;
        int end;
        int cost;
        public Edge (int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        List<Edge> list = new ArrayList<>();
        for (int i=0; i < p; i++){
            StringTokenizer st1  = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            list.add(new Edge(a, b, -d));
        }
        for (int i=0; i < r; i++){
            StringTokenizer st1  = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            list.add(new Edge(a, b, c-d));
        }
        int[] key = new int[n+1];
        for (int i=1; i <= n; i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[start] = -d;
        boolean done;
        for (int i=0; i < n-1; i++){
            done = true;
            for (Edge j : list){
                if (key[j.end] > key[j.start] + j.cost){
                    key[j.end] = key[j.start] + j.cost;
                    done = false;
                }
            }
            if (done){
                break;
            }
        }
        for (Edge j : list){
            if (key[j.end] > key[j.start] + j.cost){
                System.out.println(-1);
                return;
            }
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            ans = Math.max(ans, -key[i]);
        }
        System.out.println(ans);
    }
}
