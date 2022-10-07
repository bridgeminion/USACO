import java.io.*;
import java.util.*;

class Path{
    Character a;
    int dist;
    public Path(Character a, int dist){
        this.a = a;
        this.dist = dist;

    }
}

public class ComeHome {

    static int ans = 1000000000;
    static char cow = ' ';

    public static void dfs (char og, char cur, Map<Character, List<Path>> adj, int dist, boolean[] visited, int[] d){
        visited[cur] = true;
        if (cur == 'Z'){
            if (dist < ans){
                ans = dist;
                cow = og;
            }
            return;
        }
        if (!adj.keySet().contains(cur)){
            return;
        }
        for (Path i : adj.get(cur)){
            if (!visited[i.a] || dist+i.dist < d[i.a]){
                d[i.a] = dist+i.dist;
                dfs (og, i.a, adj, dist + i.dist, visited, d);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Map<Character, List<Path>> adj = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Character a = st.nextToken().toCharArray()[0];
            Character b = st.nextToken().toCharArray()[0];
            int dist = Integer.parseInt(st.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Path(b, dist));
            adj.get(b).add(new Path(a, dist));
        }
        char[] start = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};
        for (char i : start){
            dfs (i, i, adj, 0, new boolean[1000], new int[1000]);
        }
        System.out.print(cow + " ");
        System.out.println(ans);
    }
}
