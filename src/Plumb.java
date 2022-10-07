import java.io.*;
import java.util.StringTokenizer;

public class Plumb {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] pond = new int[r][c];
        int[] dr = {1, 0, 1, 0, -1, -1, -1, -1};
        int[] dc = {0, 1, 1, -1, 0, 1, -1, 0};
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                pond[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        int ans = 0;
        for (int i=0; i < r; i++){
            for (int j=0; j < c; j++){
                for (int k=0; k < 8; k++){
                    if ((i + dr[k] < r) && (j + dc[k] < c)  && (i + dr[k] >= 0) && (j + dc[k] >= 0)){
                        int row = i + dr[k];
                        int col = j + dc[k];
                        if (pond[i][j] == pond[row][col]){
                            if (pond[i][j] > ans){
                                ans = pond[i][j];
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
