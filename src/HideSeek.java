import java.io.*;
import java.util.*;

public class HideSeek {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        int counter = 0;
        int ans_index = 0;
        int ans_counter = 0;
        int ans_num = 0;
        boolean reset = true;
        while (!q.isEmpty()){
            reset = true;
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                for (int j : adj.get(cur)){
                    if (!visited[j]){
                        if (reset){
                            reset = false;
                            ans_num = 0;
                            ans_index = n;
                        }
                        visited[j] = true;
                        q.add(j);
                        ans_index = Math.min(ans_index, j);
                        ans_counter = counter+1;
                        ans_num++;
                    }
                }
            }
            counter++;
        }
        System.out.print(ans_index + " " + ans_counter + " " + ans_num);
    }
}
