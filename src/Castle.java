import java.io.*;
import java.util.*;

public class Castle {

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
        int[][] index = new int[num_rows][num_cols];
        int[] area = new int[a*b];
        int i1 = 0;
        int j1 = 0;
        String s = br.readLine();
        while (s != null && !s.equals("")){
            if (j1 == b){
                j1 = 0;
                i1++;
            }
            StringTokenizer st1 = new StringTokenizer(s);
            while (st1.hasMoreTokens()){
                int i = i1*2 + 1;
                int j = j1*2 + 1;
                int temp = Integer.parseInt(st1.nextToken());
                if (temp == 15){
                    grid[i+1][j] = true;
                    grid[i-1][j] = true;
                    grid[i][j-1] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 14){
                    grid[i+1][j] = true;
                    grid[i-1][j] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 13){
                    grid[i+1][j] = true;
                    grid[i][j-1] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 12){
                    grid[i+1][j] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 11){
                    grid[i+1][j] = true;
                    grid[i-1][j] = true;
                    grid[i][j-1] = true;
                }
                else if (temp == 10){
                    grid[i+1][j] = true;
                    grid[i-1][j] = true;
                }
                else if (temp == 9){
                    grid[i+1][j] = true;
                    grid[i][j-1] = true;
                }
                else if (temp == 8){
                    grid[i+1][j] = true;
                }
                else if (temp == 7){
                    grid[i-1][j] = true;
                    grid[i][j-1] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 6){
                    grid[i-1][j] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 5){
                    grid[i][j-1] = true;
                    grid[i][j+1] = true;
                }
                else if (temp == 4){
                    grid[i][j+1] = true;
                }
                else if (temp == 3){
                    grid[i-1][j] = true;
                    grid[i][j-1] = true;
                }
                else if (temp == 2){
                    grid[i-1][j] = true;
                }
                else if (temp == 1){
                    grid[i][j-1] = true;
                }
                j1++;
            }
            s = br.readLine();
        }
//        for (int i=0; i < num_rows; i++){
//            for (int j=0; j < num_cols; j++){
//                if (grid[i][j]){
//                    System.out.print("#");
//                }
//                else {
//                    System.out.print(".");
//                }
//            }
//            System.out.println();
//        }
        int counter = 0;
        int maxsize = 0;
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                if (!visited[i][j] && i%2 == 1 && j%2 == 1){
                    int size = 1;
                    visited[i][j] = true;
                    Queue<Integer> row = new LinkedList<>();
                    Queue<Integer> col = new LinkedList<>();
                    row.add(i);
                    col.add(j);
                    while (!row.isEmpty()){
                        int temp = row.size();
                        for (int k=0; k < temp; k++){
                            int currow = row.remove();
                            int curcol = col.remove();
                            index[currow][curcol] = counter;
//                            System.out.println("row = "+currow);
//                            System.out.println("col = "+curcol);
                            for (int x=0; x < 4; x++){
                                int r = currow + dr[x];
                                int c = curcol + dc[x];
                                if (r >= 0 && r < num_rows && c >= 0 && c < num_cols && !grid[r][c] && !visited[r][c]){
                                    if (dr[x] == 2){
                                        if (!grid[r-1][c]){
                                            visited[r][c] = true;
                                            row.add(r);
                                            col.add(c);
                                            size++;
                                        }
                                    }
                                    else if (dr[x] == -2){
                                        if (!grid[r+1][c]){
                                            visited[r][c] = true;
                                            row.add(r);
                                            col.add(c);
                                            size++;
                                        }
                                    }
                                    else if (dc[x] == 2){
                                        if (!grid[r][c-1]){
                                            visited[r][c] = true;
                                            row.add(r);
                                            col.add(c);
                                            size++;
                                        }
                                    }
                                    else if (dc[x] == -2){
                                        if (!grid[r][c+1]){
                                            visited[r][c] = true;
                                            row.add(r);
                                            col.add(c);
                                            size++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    area[counter] = size;
                    counter++;
                    maxsize = Math.max(maxsize, size);
                }
            }
        }
        int maxarea = 0;
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                if (i%2 == 1 || j%2 == 1 && grid[i][j]){
                    int temp = 0;
                    int nr1 = i-1;
                    int nr2 = i+1;
                    if (nr1 >= 0 && nr1 < num_rows && nr2 >= 0 && nr2 < num_rows && !grid[nr1][j] && !grid[nr2][j] && index[nr1][j] != index[nr2][j]){
                        temp += area[index[nr1][j]] + area[index[nr2][j]];
                    }
                    maxarea = Math.max(maxarea, temp);
                    temp = 0;
                    int nc1 = j-1;
                    int nc2 = j+1;
                    if (nc1 >= 0 && nc1 < num_cols && nc2 >= 0 && nc2 < num_cols && !grid[i][nc1] && !grid[i][nc2] && index[i][nc1] != index[i][nc2]){
                        temp += area[index[i][nc1]] + area[index[i][nc2]];
                    }
                    maxarea = Math.max(maxarea, temp);
                }
            }
        }
        System.out.println(counter);
        System.out.println(maxsize);
        System.out.println(maxarea);
        pw.close();
    }
}
