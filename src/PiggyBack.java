import java.io.*;
import java.util.*;

public class PiggyBack {

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("piepie.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bes = Integer.parseInt(st.nextToken());
        int els = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
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
            adj[a].list.add(b);
            adj[b].list.add(a);
        }
        int[] bessie = new int[n+1];
        boolean[] visited1 = new boolean[n+1];
        int counter1 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(1);
        visited1[1] = true;
        while (!q1.isEmpty()){
            int temp = q1.size();
            for (int i=0; i < temp; i++){
                int cur = q1.remove();
                bessie[cur] = counter1;
                for (int j : adj[cur].list){
                    if (!visited1[j]){
                        visited1[j] = true;
                        q1.add(j);
                    }
                }
            }
            counter1++;
        }
        int[] elsie = new int[n+1];
        boolean[] visited2 = new boolean[n+1];
        int counter2 = 0;
        Queue<Integer> q2 = new LinkedList<>();
        q2.add(2);
        visited2[2] = true;
        while (!q2.isEmpty()){
            int temp = q2.size();
            for (int i=0; i < temp; i++){
                int cur = q2.remove();
                elsie[cur] = counter2;
                for (int j : adj[cur].list){
                    if (!visited2[j]){
                        visited2[j] = true;
                        q2.add(j);
                    }
                }
            }
            counter2++;
        }
        int[] end = new int[n+1];
        boolean[] visited3 = new boolean[n+1];
        int counter3 = 0;
        Queue<Integer> q3 = new LinkedList<>();
        q3.add(n);
        visited3[n] = true;
        while (!q3.isEmpty()){
            int temp = q3.size();
            for (int i=0; i < temp; i++){
                int cur = q3.remove();
                end[cur] = counter3;
                for (int j : adj[cur].list){
                    if (!visited3[j]){
                        visited3[j] = true;
                        q3.add(j);
                    }
                }
            }
            counter3++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i=1; i <= n; i++){
            int temp = bessie[i]*bes + elsie[i]*els + end[i]*p;
            ans = Math.min(ans, temp);
        }
        System.out.println(ans);
    }
}
