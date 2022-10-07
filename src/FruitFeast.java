import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FruitFeast {

    static int ans = 0;

    public static void dfs (int cur, boolean used, boolean[] valid, int n, int a, int b){
        ans = Math.max(ans, cur);
        if (cur+a <= n && !valid[cur+a]){
            valid[cur+a] = true;
            dfs (cur+a, used, valid, n, a, b);
        }
        if (cur+b <= n && !valid[cur+b]){
            valid[cur+b] = true;
            dfs (cur+b, used, valid, n, a, b);
        }
        if (!used && !valid[cur/2]){
            valid[cur/2] = true;
            dfs (cur/2, true, valid, n, a, b);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[] valid = new boolean[n+1];
        dfs (0, false, valid, n, a, b);
//        Queue<Integer> q = new LinkedList<>();
//        Queue<Boolean> q2 = new LinkedList<>();
//        q.add(0);
//        q2.add(false);
//        valid[0] = true;
//        int ans = 0;
//        while (!q.isEmpty()){
//            int temp = q.remove();
//            boolean used = q2.remove();
//            ans = Math.max(ans, temp);
//            if (temp+a <= n && !valid[temp+a]){
//                valid[temp+a] = true;
//                q.add(temp+a);
//                q2.add(used);
//            }
//            if (temp+b <= n && !valid[temp+b]){
//                valid[temp+b] = true;
//                q.add(temp+b);
//                q2.add(used);
//            }
//            if (!used && !valid[temp/2]){
//                valid[temp/2] = true;
//                q.add(temp/2);
//                q2.add(true);
//            }
//        }
        System.out.println(ans);
    }
}
