import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Hopscotch {

    static boolean[] found = new boolean[10000000];
    static List<Integer> ans = new ArrayList<>();
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void recurse(int row, int col, int[][] array, int counter, int current){
        if (counter == 6){
            if (!found[current]){
                found[current] = true;
                ans.add(current);
            }
            return;
        }
        for(int i=0; i < 4; i++){
            int r = row + dr[i];
            int c = col + dc[i];
            if (r >=0 && r < 5 && c >=0 && c < 5){
                int temp = array[r][c];
                recurse(r, c, array, counter + 1, current*10 + temp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] num = new int[5][5];
        for (int i=0; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < 5; j++){
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<5; i++){
            for (int j=0; j < 5; j++){
                recurse(i, j, num, 0, 0);
            }
        }
        System.out.println(ans.size());
    }
}
