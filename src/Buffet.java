import java.io.*;
import java.util.*;

public class Buffet {

    static class Patch {
        int taste;
        List<Integer> adj;
        int index;
        public Patch(int taste, List<Integer> adj, int index) {
            this.taste = taste;
            this.adj = adj;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        Patch[] arr = new Patch[n+1];
        Patch[] sorted = new Patch[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            List<Integer> temp = new ArrayList<>(d);
            for (int j=0; j < d; j++){
                temp.add(Integer.parseInt(st1.nextToken()));
            }
            arr[i+1] = new Patch (q, temp, i+1);
            sorted[i] = arr[i+1];
        }
        int[][] cost = new int[n+1][n+1];
        for (int i=1; i <= n; i++){
            for (int j=1; j <= n; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i=1; i <= n; i++){
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            int counter = 0;
            boolean[] visited = new boolean[n+1];
            visited[i] = true;
            while (!q.isEmpty()){
                int temp = q.size();
                for (int j=0; j < temp; j++){
                    int cur = q.remove();
                    cost[i][cur] = counter*e;
                    for (int k : arr[cur].adj){
                        if (!visited[k]){
                            visited[k] = true;
                            q.add(k);
                        }
                    }
                }
                counter++;
            }
        }
        Arrays.sort(sorted, new Comparator<Patch>() {
            @Override
            public int compare(Patch o1, Patch o2) {
                if (o1.taste == o2.taste){
                    return 1;
                }
                return o1.taste - o2.taste;
            }
        });
        int[] dp = new int[n+1];
        int ans = 0;
        for (Patch i : sorted){
            dp[i.index] = arr[i.index].taste;
            for (int j=1; j <= n; j++){
                if (arr[j].taste < arr[i.index].taste){
                    dp[i.index] = Math.max(dp[i.index], dp[j] - cost[i.index][j] + arr[i.index].taste);
                }
            }
//            System.out.println("index: " + i.index + " val: " + dp[i.index]);
        }
        for (int i=1; i <= n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
