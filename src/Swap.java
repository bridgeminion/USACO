import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Swap {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("swap.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] pos = new int[n+1];
        for (int i=1; i <= n; i++){
            pos[i] = i;
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            for (int j=a; j <= (b+a)/2; j++){
                int temp = pos[j];
                pos[j] = pos[b-j+a];
                pos[b-j+a] = temp;
            }
        }
//        for (int i=1; i <= n; i++){
//            System.out.println(pos[i]);
//        }
//        int[] mod = new int[n+1];
//        for (int i=1; i <= n; i++){
//            int cur = pos[i];
//            mod[i]++;
//            while (cur != i){
//                cur = pos[cur];
//                mod[i]++;
//            }
//        }
//        for (int i=1; i <= n; i++){
//            int temp = (k-1)%mod[i];
//            int ans = pos[i];
//            for (int j=0; j < temp; j++){
//                ans = pos[ans];
//            }
//            pw.println(ans);
//        }
//        pw.close();
        boolean[] visited = new boolean[n+1];
        int[] id = new int[n+1];
        int[] extra = new int[n+1];
        List<List<Integer>> list = new ArrayList<>();
        int counter = 0;
        for (int i=1; i <= n; i++){
            if (!visited[i]){
                int temp = 0;
                list.add(new ArrayList<>());
                visited[i] = true;
                id[i] = counter;
                int cur = pos[i];
                while (cur != i){
                    temp++;
                    list.get(counter).add(cur);
                    visited[cur] = true;
                    id[cur] = counter;
                    extra[cur] = temp;
                    cur = pos[cur];
                }
                list.get(counter).add(i);
                counter++;
            }
        }
        int[] ans = new int[n+1];
        for (int i=1; i <= n; i++){
            int mod = list.get(id[i]).size();
            int temp = (k-1)%mod;
            ans[i] = list.get(id[i]).get((temp + extra[i])%mod);
        }
        for (int i=1; i <= n; i++){
            pw.println(ans[i]);
        }
        pw.close();
    }
}
