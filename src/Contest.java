import java.io.*;
import java.util.*;

public class Contest {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, Set<Integer>> better = new HashMap<>();
        Map<Integer, Set<Integer>> worse = new HashMap<>();
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            better.putIfAbsent(a, new HashSet<>());
            better.get(a).add(b);
            worse.putIfAbsent(b, new HashSet<>());
            worse.get(b).add(a);
        }
//        for (int i=1; i <= n; i++){
//            if (better.containsKey(i)){
//                Iterator it = better.get(i).iterator();
//                int cur = (int)it.next();
//                if (better.containsKey(cur)){
//                    better.get(i).addAll(better.get(cur));
//                }
//            }
//            if (worse.containsKey(i)){
//                Iterator it = worse.get(i).iterator();
//                int cur = (int)it.next();
//                if (worse.containsKey(cur)){
//                    worse.get(i).addAll(worse.get(cur));
//                }
//            }
//        }
        for (int k=1; k <= n; k++){
            for (int i=1; i <= n; i++){
                for (int j=1; j <= n; j++){
                    if (better.containsKey(i) && better.containsKey(k)){
                        if (better.get(i).contains(k) && better.get(k).contains(j)){
                            better.get(i).add(j);
                        }
                    }
                    if (worse.containsKey(i) && worse.containsKey(k)){
                        if (worse.get(i).contains(k) && worse.get(k).contains(j)){
                            worse.get(i).add(j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            Set<Integer> cur = new HashSet<>();
            if (better.containsKey(i)){
                cur.addAll(better.get(i));
            }
            if (worse.containsKey(i)){
                cur.addAll(worse.get(i));
            }
            if (cur.size() == n-1){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
