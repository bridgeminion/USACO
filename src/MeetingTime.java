import java.io.*;
import java.util.*;

public class MeetingTime {

    static class Path {
        int end;
        int cost1;
        int cost2;

        public Path(int end, int cost1, int cost2) {
            this.end = end;
            this.cost1 = cost1;
            this.cost2 = cost2;
        }
    }

    static class Nodes{
        List<Path> list;
        public Nodes (List<Path> list){
            this.list = list;
        }
    }

    static class Temp{
        Set<Integer> set = new HashSet<>();
    }

    static class State {
        int id;
        int cost;
        public State (int id, int cost){
            this.id = id;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("meeting.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("meeting.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Nodes[] adj = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c1 = Integer.parseInt(st1.nextToken());
            int c2 = Integer.parseInt(st1.nextToken());
            adj[a].list.add(new Path (b, c1, c2));
        }
        Temp[] valid1 = new Temp[n+1];
        for (int i=1; i <= n; i++){
            valid1[i] = new Temp();
        }
        Queue<State> q1 = new LinkedList<>();
        q1.add(new State (1, 0));
        while (!q1.isEmpty()){
            State cur1 = q1.remove();
            for (Path i : adj[cur1.id].list){
                int temp = cur1.cost + i.cost1;
                if (!valid1[i.end].set.contains(temp)){
                    valid1[i.end].set.add(temp);
                    q1.add(new State (i.end, temp));
                }
            }
        }
        Temp[] valid2 = new Temp[n+1];
        for (int i=1; i <= n; i++){
            valid2[i] = new Temp();
        }
        Queue<State> q2 = new LinkedList<>();
        q2.add(new State (1, 0));
        while (!q2.isEmpty()){
            State cur2 = q2.remove();
            for (Path i : adj[cur2.id].list){
                int temp = cur2.cost + i.cost2;
                if (!valid2[i.end].set.contains(temp)){
                    valid2[i.end].set.add(temp);
                    q2.add(new State (i.end, temp));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : valid1[n].set){
            if (valid2[n].set.contains(i)){
                ans = Math.min(ans, i);
            }
        }
        if (ans != Integer.MAX_VALUE){
            pw.println(ans);
            pw.close();
        }
        else {
            pw.println("IMPOSSIBLE");
            pw.close();
        }
    }
}
