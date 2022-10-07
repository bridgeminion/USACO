import java.io.*;
import java.util.*;

public class Time_Failed {

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    static int shortest (int start, Nodes[] adj, int n){
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                if (cur == 1){
                    return ans;
                }
                for (int j : adj[cur].list){
                    if (!visited[j]){
                        visited[j] = true;
                        q.add(j);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    static class State {
        int index;
        int time;
        int money;

        public State(int index, int time, int money) {
            this.index = index;
            this.time = time;
            this.money = money;
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
        Nodes[] adj = new Nodes[n+1];
        StringTokenizer s = new StringTokenizer(br.readLine());
        for (int i=1; i <= n; i++){
            val[i] = Integer.parseInt(s.nextToken());
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj[a].list.add(b);
        }
        int[] shortest = new int[n+1];
        for (int i=1; i <= n; i++){
            shortest[i] = shortest (i, adj, n);
        }
        Queue<State> q = new LinkedList<>();
        q.add(new State (1, 0, 0));
        int ans = 0;
        while (!q.isEmpty()){
            State cur = q.remove();
            if (cur.index == 1){
                ans = Math.max(ans, cur.money - c*cur.time*cur.time);
            }
            for (int j : adj[cur.index].list){
                int temp = shortest[j] - shortest[cur.index] + 1;
                if (j == 1 || val[j] > c*(temp*temp + 2*temp+cur.time)){
                    q.add(new State (j, cur.time+1, cur.money + val[j]));
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
