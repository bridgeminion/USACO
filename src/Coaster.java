import java.io.*;
import java.util.*;

public class Coaster {

    static class Part{
        int len;
        int fun;
        int cost;
        public Part (int len, int fun, int cost){
            this.len = len;
            this.fun = fun;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowrun.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowrun.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Map<Integer, List<Part>> map = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int len = Integer.parseInt(st1.nextToken());
            int fun = Integer.parseInt(st1.nextToken());
            int cost = Integer.parseInt(st1.nextToken());
            map.putIfAbsent(start, new ArrayList<>());
            map.get(start).add(new Part(len, fun, cost));
        }
        int[][] val = new int[l+1][b+1];
        for (int i=0; i <= l; i++){
            for (int j=0; j <= b; j++){
                val[i][j] = Integer.MIN_VALUE;
            }
        }
        val[0][0] = 0;
        for (int i=0; i < l; i++){
            for (int j=0; j <= b; j++){
                if (map.containsKey(i)){
                    for (Part k : map.get(i)){
                        if (j+k.cost <= b){
                            val[i+k.len][j+k.cost] = Math.max(val[i][j]+k.fun, val[i+k.len][j+k.cost]);
                        }
                    }
                }
            }
        }
        int ans = -1;
        for (int j=0; j <= b; j++){
            ans = Math.max(ans, val[l][j]);
        }
        System.out.println(ans);
    }
}
