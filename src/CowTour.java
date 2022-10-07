import java.io.*;
import java.util.*;

public class CowTour {

    public static double dist (Coordinate a, Coordinate b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static List<Set<Integer>> findField (List<Coordinate> list, boolean[][] adj){
        boolean[] visited = new boolean[list.size()];
        List<Set<Integer>> ans = new ArrayList<>();
        for (int i=0; i < list.size(); i++){
            if (!visited[i]){
                visited[i] = true;
                Set<Integer> temp = new HashSet<>();
                temp.add(i);
                for (int j=0; j < list.size(); j++){
                    if (i != j && adj[i][j] && !visited[j]){
                        temp.add(j);
                        visited[j] = true;
                    }
                }
                ans.add(temp);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowtour.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Coordinate> pos = new ArrayList<>(n);
        boolean[][] adj = new boolean[n+1][n+1];
        double[][] dist = new double[n+1][n+1];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos.add(new Coordinate(x, y));
        }
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j=0; j < n; j++){
                if (s.charAt(j) == '1'){
                    adj[i][j] = true;
                    dist[i][j] = dist(pos.get(i), pos.get(j));
                }
            }
        }
        for (int k=0; k < n; k++){
            for (int i=0; i < n; i++){
                for (int j=0; j < n; j++){
                    if (adj[i][k] && adj[k][j]){
                        if (!adj[i][j] || dist[i][j] > dist[i][k] + dist[k][j]){
                            adj[i][j] = true;
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        List<Set<Integer>> fields = findField(pos, adj);
        double[] maxdist = new double[n];
        for (int i=0; i < n; i++){
            double max = 0;
            for (int j=0; j < n; j++){
                if (i != j){
                    max = Math.max(max, dist[i][j]);
                }
            }
            maxdist[i] = max;
        }
        double[] diameter = new double[n];
        for (int i=0; i < fields.size(); i++){
            double cur = 0;
            for (int j : fields.get(i)){
                cur = Math.max(cur, maxdist[j]);
            }
            for (int j : fields.get(i)){
                diameter[j] = cur;
            }
        }
        double min = 0;
        boolean updated = false;
        for (int i=0; i < fields.size(); i++){
            for (int j=0; j < fields.size(); j++){
                if (i != j){
                    for (int a : fields.get(i)){
                        for (int b : fields.get(j)){
                            double temp = Math.max(maxdist[a] + maxdist[b] + dist(pos.get(a), pos.get(b)), diameter[a]);
                            temp = Math.max(temp, diameter[b]);
                            if (!updated || min > temp){
                                updated = true;
                                min = temp;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(String.format("%.6f", min));
    }
}
