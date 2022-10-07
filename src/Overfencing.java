import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Overfencing {

    static int[] dr = {-2, 2, 0, 0};
    static int[] dc = {0, 0, -2, 2};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int num_rows = a*2 + 1;
        int num_cols = b*2 + 1;
        boolean[][] grid = new boolean[num_rows][num_cols];
        boolean[][] visited = new boolean[num_rows][num_cols];
        int[][] moves = new int[num_rows][num_cols];
        int startrow1 = 0;
        int startcol1 = 0;
        int startrow2 = 0;
        int startcol2 = 0;
        boolean found = false;
        for (int i=0; i < num_rows; i++){
            String temp = br.readLine();
            for (int j=0; j < num_cols; j++){
                moves[i][j] = -1;
                if (i%2 == 1 || j%2 == 1){
                    if (temp.charAt(j) != ' '){
                        grid[i][j] = true;
                    }
                }
                if ((i == 0 || i == num_rows-1 || j == 0 || j == num_cols - 1) && temp.charAt(j) == ' '){
                    if (!found){
                        found = true;
                        startrow1 = i;
                        startcol1 = j;
                    }
                    else {
                        startrow2 = i;
                        startcol2 = j;
                    }
                }
            }
        }
//        for (int i=0; i < num_rows; i++){
//            for (int j=0; j < num_cols; j++){
//                if (grid[i][j]){
//                    System.out.print("#");
//                }
//                else{
//                    System.out.print(".");
//                }
//            }
//            System.out.println();
//        }
        if (startrow1 == 0){
            startrow1++;
        }
        else if (startcol1 == 0){
            startcol1++;
        }
        else if (startrow1 == num_rows-1){
            startrow1--;
        }
        else {
            startcol1--;
        }
        if (startrow2 == 0){
            startrow2++;
        }
        else if (startcol2 == 0){
            startcol2++;
        }
        else if (startrow2 == num_rows-1){
            startrow2--;
        }
        else {
            startcol2--;
        }
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        row.add(startrow1);
        col.add(startcol1);
        visited[startrow1][startcol1] = true;
        int counter = 1;
        while (!row.isEmpty()){
            int temp = row.size();
            for (int i=0; i < temp; i++){
                int currow = row.remove();
                int curcol = col.remove();
                for (int j=0; j < 4; j++){
                    int nr = currow + dr[j];
                    int nc = curcol + dc[j];
                    if (nr >= 0 && nr < num_rows && nc >= 0 && nc < num_cols && !visited[nr][nc] && !grid[nr][nc]){
                        if (dr[j] == 2){
                            if (!grid[nr-1][nc]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = counter;
                            }
                        }
                        else if (dr[j] == -2){
                            if (!grid[nr+1][nc]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = counter;
                            }
                        }
                        else if (dc[j] == 2){
                            if (!grid[nr][nc-1]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = counter;
                            }
                        }
                        else if (dc[j] == -2){
                            if (!grid[nr][nc+1]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = counter;
                            }
                        }
                    }
                }
            }
            counter++;
        }
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                visited[i][j] = false;
            }
        }
        row = new LinkedList<>();
        col = new LinkedList<>();
        row.add(startrow2);
        col.add(startcol2);
        visited[startrow2][startcol2] = true;
        counter = 1;
        int ans = 0;
        while (!row.isEmpty()){
            int temp = row.size();
            for (int i=0; i < temp; i++){
                int currow = row.remove();
                int curcol = col.remove();
                for (int j=0; j < 4; j++){
                    int nr = currow + dr[j];
                    int nc = curcol + dc[j];
                    if (nr >= 0 && nr < num_rows && nc >= 0 && nc < num_cols && !visited[nr][nc] && !grid[nr][nc]){
                        if (dr[j] == 2){
                            if (!grid[nr-1][nc]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = Math.min(moves[nr][nc], counter);
                                ans = Math.max(ans, moves[nr][nc]);
                            }
                        }
                        else if (dr[j] == -2){
                            if (!grid[nr+1][nc]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = Math.min(moves[nr][nc], counter);
                                ans = Math.max(ans, moves[nr][nc]);
                            }
                        }
                        else if (dc[j] == 2){
                            if (!grid[nr][nc-1]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = Math.min(moves[nr][nc], counter);
                                ans = Math.max(ans, moves[nr][nc]);
                            }
                        }
                        else if (dc[j] == -2){
                            if (!grid[nr][nc+1]){
                                visited[nr][nc] = true;
                                row.add(nr);
                                col.add(nc);
                                moves[nr][nc] = Math.min(moves[nr][nc], counter);
                                ans = Math.max(ans, moves[nr][nc]);
                            }
                        }
                    }
                }
            }
            counter++;
        }
        pw.println(ans+1);
        pw.close();
    }
}
