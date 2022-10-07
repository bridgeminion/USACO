import java.io.*;
import java.util.*;

public class CowTravel {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[r][c];
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < c; j++){
                grid[i][j] = temp.charAt(j) == '*';
            }
        }
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int startr = Integer.parseInt(st1.nextToken());
        int startc = Integer.parseInt(st1.nextToken());
        int endr = Integer.parseInt(st1.nextToken());
        int endc = Integer.parseInt(st1.nextToken());
        int[][][] val = new int[r][c][t+1];
        val[startr-1][startc-1][0] = 1;
        for (int k=0; k < t; k++){
            for (int i=0; i < r; i++){
                for (int j=0; j < c; j++){
                    for (int a=0; a < 4; a++){
                        int nr = i+dr[a];
                        int nc = j+dc[a];
                        if (nr >= 0 && nr < r && nc >= 0 && nc < c && !grid[nr][nc]){
                            val[nr][nc][k+1] += val[i][j][k];
                        }
                    }
                }
            }
        }
        System.out.println(val[endr-1][endc-1][t]);
    }
}
