import java.io.*;
import java.util.*;

public class Art {

    static class State {
        int minr = 11;
        int maxr = -1;
        int minc = 11;
        int maxc = -1;
        public State(){}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("art.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("art.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        State[] color = new State[10];
        Set<Integer> seen = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int i=1; i <= n*n; i++){
            ans.add(i);
        }
        for (int i=0; i < 10; i++){
            color[i] = new State();
        }
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                int temp = Integer.parseInt(st.nextToken());
                if (temp != 0){
                    seen.add(temp);
                    grid[i][j] = temp;
                    color[temp].minr = Math.min(color[temp].minr, i);
                    color[temp].minc = Math.min(color[temp].minc, j);
                    color[temp].maxr = Math.max(color[temp].maxr, i);
                    color[temp].maxc = Math.max(color[temp].maxc, j);
                }
            }
        }
        for (int c : seen){
            for (int i = color[c].minr; i <= color[c].maxr; i++){
                for (int j = color[c].minc; j <= color[c].maxc; j++){
                    int temp = grid[i][j];
                    if (temp!=c && temp != 0){
                        ans.remove(temp);
                    }
                }
            }
        }
        if (seen.size() > 0){
            pw.println(ans.size());
        }
        else {
            pw.println(0);
        }
        pw.close();
    }
}
