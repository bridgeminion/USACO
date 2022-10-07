import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Cowpatibility {

    static class S {
        Set<Integer> set = new HashSet<>();
        public S (){}
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowpatibility.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        S[] fav = new S[1000001];
        S[] adj = new S[n+1];
        for (int i=0; i < 1000001; i++){
            fav[i] = new S();
        }
        for (int i=1; i <= n; i++){
            adj[i] = new S();
        }
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < 5; j++){
                int temp = Integer.parseInt(st.nextToken());
                adj[i+1].set.addAll(fav[temp].set);
                fav[temp].set.add(i+1);
            }
        }
        int com = 0;
        for (int i=1; i <= n; i++){
            com += adj[i].set.size();
        }
        int ans = (n*(n-1))/2 - com;
        pw.println(ans);
        pw.close();
    }
}
