import java.io.*;
import java.util.*;

public class SilverCowParty {

    static class Node {
        int end;
        int dist;
        public Node (int end, int dist){
            this.end = end;
            this.dist = dist;
        }
    }

    static class Nodes{
        List<Node> list;
        public Nodes (List<Node> list){
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        Nodes[] adj1 = new Nodes[n+1];
        Nodes[] adj2 = new Nodes[n+1];
        for (int i=1; i <= n; i++){
           adj1[i] = new Nodes(new ArrayList<>());
           adj2[i] = new Nodes(new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            adj1[b].list.add(new Node (a, c));
            adj2[a].list.add(new Node (b, c));
        }
        int[] key1 = new int[n+1];
        for (int i=1; i <= n; i++){
            key1[i] = Integer.MAX_VALUE;
        }
        key1[x] = 0;
        Set<Integer> visited1 = new HashSet<>();
        while (visited1.size() < n){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i=1; i <= n; i++){
                if (!visited1.contains(i) && key1[i] < min){
                    min = key1[i];
                    index = i;
                }
            }
            visited1.add(index);
            for (Node i : adj1[index].list){
                if (!visited1.contains(i.end)){
                    key1[i.end] = Math.min(key1[i.end], key1[index] + i.dist);
                }
            }
        }
        int[] key2 = new int[n+1];
        for (int i=1; i <= n; i++){
            key2[i] = Integer.MAX_VALUE;
        }
        key2[x] = 0;
        Set<Integer> visited2 = new HashSet<>();
        while (visited2.size() < n){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i=1; i <= n; i++){
                if (!visited2.contains(i) && key2[i] < min){
                    min = key2[i];
                    index = i;
                }
            }
            visited2.add(index);
            for (Node i : adj2[index].list){
                if (!visited2.contains(i.end)){
                    key2[i.end] = Math.min(key2[i.end], key2[index] + i.dist);
                }
            }
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            ans = Math.max(ans, key1[i] + key2[i]);
        }
        System.out.println(ans);
    }
}
