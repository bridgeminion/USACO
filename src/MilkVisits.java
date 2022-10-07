import java.io.*;
import java.util.*;

public class MilkVisits {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("milkvisits.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String temp = br.readLine();
        boolean[] isG = new boolean[n + 1];
        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            isG[i] = temp.charAt(i - 1) == 'G';
        }
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i=0; i < n-1; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        Queue<Integer> q = new LinkedList<>();
        int groupID = 1;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                q.add(i);
                visited[i] = groupID;
                while (!q.isEmpty()) {
                    int cur = q.remove();
                    for (int j : adj.get(cur)) {
                        if (visited[j] == 0 && isG[cur] == isG[j]) {
                            q.add(j);
                            visited[j] = groupID;
                        }
                    }
                }
            }
            groupID++;
        }
        for (int i=0; i < m; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            char c = st1.nextToken().charAt(0);
            if (isG[a] != isG[b]) {
                pw.print("1");
            } else if (isG[a] == (c == 'G') || isG[b] == (c == 'G')) {
                pw.print("1");
            } else if (visited[a] != visited[b]) {
                pw.print("1");
            } else {
                pw.print("0");
            }
        }
        pw.close();
    }
}