import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ClockTree {

    public static boolean done = false;
    public static boolean works = false;

    public static void dfs (int cur, int[] pos, List<List<Integer>> adj){
        if (done){
            return;
        }
        for (int i : adj.get(cur)){
            if (done){
                return;
            }
            if (pos[i] != 12){
                if (pos[cur] > pos[i]){
                    int dif = 12-pos[cur];
                    pos[cur] = 12;
                    pos[i] += dif+1;
                    dfs (i, pos, adj);
                    pos[cur] = 12-dif;
                    pos[i] -= dif+1;
                }
                else {
                    int dif = 12-pos[i];
                    pos[i] = 12;
                    pos[cur] += dif;
                    dfs (cur, pos, adj);
                    pos[i] = 12-dif;
                    pos[cur] -= dif;
                }
            }
        }
        boolean valid = true;
        for (int j=1; j < pos.length; j++){
            if (pos[j] != 12){
                valid = false;
                break;
            }
        }
        if (valid){
            done = true;
            works = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("clocktree.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] pos = new int[n+1];
        int[] temp = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<List<Integer>> adj = new ArrayList<>(n+1);
        adj.add(0, new ArrayList<>());
        for (int i=0; i < n; i++){
            pos[i+1] = Integer.parseInt(st.nextToken());
            temp[i+1] = pos[i+1];
            adj.add(i+1, new ArrayList<>());
        }
        for (int i=0; i < n-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            done = false;
            works = false;
            dfs (i, temp, adj);
            if (works){
                ans++;
            }
            for (int j=1; j <= n; j++){
                temp[j] = pos[j];
            }
        }
        pw.println(ans);
        pw.close();
    }
}
