import java.io.*;
import java.util.*;

public class CowTip {

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowtip.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        char[][] grid = new char[n][n];
        boolean done = true;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < n; j++){
                grid[i][j] = temp.charAt(j);
                if (grid[i][j] == '1'){
                    done = false;
                }
            }
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = n-1; j >= 0; j--) {
                if(grid[i][j] == '1') {
                    ans++;
                    for(int a = 0; a <= i; a++) {
                        for(int b = 0; b <= j; b++) {
                            // flip each entry in that rectangle
                            if(grid[a][b] == '1') {
                                grid[a][b] = '0';
                            }
                            else {
                                grid[a][b] = '1';
                            }
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
