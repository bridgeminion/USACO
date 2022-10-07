import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gravity {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[num_rows][num_cols];
        boolean[][] visited = new boolean[num_rows][num_cols];
        int startrow = 0;
        int startcol = 0;
        int endrow = 0;
        int endcol = 0;
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < num_cols; j++){
                if (temp.charAt(j) == '#'){
                    grid[i][j] = true;
                }
                else if (temp.charAt(j) == 'C'){
                    startrow = i;
                    startcol = j;
                }
                else if (temp.charAt(j) == 'D'){
                    endrow = i;
                    endcol = j;
                }
            }
        }
        while (startrow >= 0 && startrow < num_rows-1 && !grid[startrow+1][startcol]){
            startrow++;
        }
        if (startrow == num_rows-1){
            pw.println(-1);
            pw.close();
            return;
        }
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        boolean gravity = true;
        row.add(startrow);
        col.add(startcol);
        visited[startrow][startcol] = true;
        int counter = 0;
        while (!row.isEmpty()){
            int temp = row.size();
            for (int i=0; i < temp; i++){
                Queue<Integer> rowtemp = new LinkedList<>();
                Queue<Integer> coltemp = new LinkedList<>();
                rowtemp.add(row.remove());
                coltemp.add(col.remove());
                while (!rowtemp.isEmpty()){
                    int currow = rowtemp.remove();
                    int curcol = coltemp.remove();
                    System.out.println("counter = "+counter);
                    System.out.println("row = "+currow);
                    System.out.println("col = "+curcol);
                    System.out.println("gravity = "+gravity);
                    if (currow == endrow && curcol == endcol){
                        pw.println(counter);
                        pw.close();
                        return;
                    }
                    if (gravity){
                        // left (col decreases)
                        int newcol = curcol-1;
                        if (newcol >= 0 && newcol < num_cols && currow < num_rows - 1 && !grid[currow][newcol] && !visited[currow][newcol]){
                            for (int j=currow+1; j < num_rows; j++){
                                if (grid[j][newcol] && !visited[j-1][newcol] && !grid[j-1][newcol]){
                                    visited[j-1][newcol] = true;
                                    rowtemp.add(j-1);
                                    coltemp.add(newcol);
                                    break;
                                }
                                if (j == endrow && curcol == endcol){
                                    pw.println(counter);
                                    pw.close();
                                    return;
                                }
                            }
                        }
                        // right (col increases)
                        newcol = curcol+1;
                        if (newcol >= 0 && newcol < num_cols && currow < num_rows - 1 && !grid[currow][newcol] && !visited[currow][newcol]){
                            for (int j=currow+1; j < num_rows; j++){
                                if (grid[j][newcol] && !visited[j-1][newcol] && !grid[j-1][newcol]){
                                    visited[j-1][newcol] = true;
                                    rowtemp.add(j-1);
                                    coltemp.add(newcol);
                                    break;
                                }
                                if (j == endrow && curcol == endcol){
                                    pw.println(counter);
                                    pw.close();
                                    return;
                                }
                            }
                        }
                        // falling (row increases)
                        for (int j=currow; j < num_rows-1; j++){
                            if (!grid[j][curcol] && grid[j+1][curcol] && !visited[j][curcol]){
                                visited[j][curcol] = true;
                                rowtemp.add(j);
                                coltemp.add(curcol);
                                break;
                            }
                            else if (grid[j][curcol]){
                                break;
                            }
                            if (j == endrow && curcol == endcol){
                                pw.println(counter);
                                pw.close();
                                return;
                            }
                        }
                        for (int j=currow; j > 0; j--){
                            if (!grid[j][curcol] && grid[j-1][curcol] && !visited[j][curcol]){
                                visited[j][curcol] = true;
                                row.add(j);
                                col.add(curcol);
                                break;
                            }
                            else if (grid[j][curcol]){
                                break;
                            }
                            if (j == endrow && curcol == endcol){
                                pw.println(counter+1);
                                pw.close();
                                return;
                            }
                        }
                    }
                    else {
                        // left (col decreases)
                        int newcol = curcol-1;
                        if (newcol >= 0 && newcol < num_cols && currow > 0 && !grid[currow][newcol] && !visited[currow][newcol]){
                            for (int j=currow-1; j >= 0; j--){
                                if (grid[j][newcol] && !visited[j+1][newcol] && !grid[j+1][newcol]){
                                    visited[j+1][newcol] = true;
                                    rowtemp.add(j+1);
                                    coltemp.add(newcol);
                                    break;
                                }
                                if (j == endrow && curcol == endcol){
                                    pw.println(counter);
                                    pw.close();
                                    return;
                                }
                            }
                        }
                        // right (col increases)
                        newcol = curcol+1;
                        if (newcol >= 0 && newcol < num_cols && currow > 0 && !grid[currow][newcol] && !visited[currow][newcol]){
                            for (int j=currow-1; j >= 0; j--){
                                if (grid[j][newcol] && !visited[j+1][newcol] && !grid[j+1][newcol]){
                                    visited[j+1][newcol] = true;
                                    rowtemp.add(j+1);
                                    coltemp.add(newcol);
                                    break;
                                }
                                if (j == endrow && curcol == endcol){
                                    pw.println(counter);
                                    pw.close();
                                    return;
                                }
                            }
                        }
                        // falling (row decreases)
                        for (int j=currow; j > 0; j--){
                            if (!grid[j][curcol] && grid[j-1][curcol] && !visited[j][curcol]){
                                visited[j][curcol] = true;
                                rowtemp.add(j);
                                coltemp.add(curcol);
                                break;
                            }
                            else if (grid[j][curcol]){
                                break;
                            }
                            if (j == endrow && curcol == endcol){
                                pw.println(counter);
                                pw.close();
                                return;
                            }
                        }
                        for (int j=currow; j < num_rows-1; j++){
                            if (!grid[j][curcol] && grid[j+1][curcol] && !visited[j][curcol]){
                                visited[j][curcol] = true;
                                row.add(j);
                                col.add(curcol);
                                break;
                            }
                            else if (grid[j][curcol]){
                                break;
                            }
                            if (j == endrow && curcol == endcol){
                                pw.println(counter+1);
                                pw.close();
                                return;
                            }
                        }
                    }
                }
            }
            counter++;
            gravity = !gravity;
        }
        pw.println(-1);
        pw.close();
    }
}
