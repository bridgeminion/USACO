import java.io.*;
import java.util.*;

public class Pathfinding {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_island = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        boolean[][] current = new boolean[num_island][num_island];
        boolean[] visited = new boolean[num_island];
        for (int i=0; i < num_island; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < num_island; j++){
                int temp = Integer.parseInt(st1.nextToken());
                current[i][j] = temp != 0;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(start-1);
        visited[start-1] = true;
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                int cur = q.remove();
                ans.add(cur);
                for (int j=0; j < num_island; j++){
                    if (current[cur][j]){
                        if (!visited[j]){
                            q.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
            Collections.sort(ans);
            for (int i : ans){
                pw.print(i+1);
                pw.print(" ");
            }
            pw.println();
            ans.clear();
        }
        pw.close();
    }
}
