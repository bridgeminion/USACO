import java.io.*;
import java.util.*;

public class TotalFlow {

    static String lowercase = "abcdefghijklmnopqrstuvwxyz";

    public static int convert (String a){
        if (lowercase.contains(a)){
            return a.toCharArray()[0] - 'a';
        }
        else {
            return a.toCharArray()[0] - 'A' + 26;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] cost = new int[52][52];
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = convert(st1.nextToken());
            int b = convert(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            cost[a][b] += d;
            cost[b][a] += d;
            adj.putIfAbsent(a, new HashSet<>());
            adj.putIfAbsent(b, new HashSet<>());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        while (adj.size() > 2){
            for (int i=0; i <= 51; i++){
                if (i != 26 && i != 51){
                    if (adj.containsKey(i) && adj.get(i).size() == 2){
                        Iterator it = adj.get(i).iterator();
                        int a = (int)it.next();
                        int b = (int)it.next();
                        int temp = Math.min(cost[a][i], cost[b][i]);
                        cost[a][b] += temp;
                        cost[b][a] += temp;
                        adj.get(a).remove(i);
                        adj.get(a).add(b);
                        adj.get(b).remove(i);
                        adj.get(b).add(a);
                        adj.remove(i);
                    }
                    if (adj.containsKey(i) && adj.get(i).size() == 1){
                        Iterator it = adj.get(i).iterator();
                        int a = (int)it.next();
                        adj.get(a).remove(i);
                        adj.remove(i);
                    }
                    else if (adj.containsKey(i) && adj.get(i).size() == 0){
                        adj.remove(i);
                    }
                }
            }
        }
        System.out.println(cost[26][51]);

    }
}
