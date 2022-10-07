import java.io.*;
import java.util.*;

public class LaserPhones {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        char[][] grid = new char[row][col];
        int[][] visited = new int[row][col];
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < row; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j = 0; j < col; j++) {
                grid[i][j] = temp.charAt(j);
                if (grid[i][j] == 'C') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        Queue<Integer> rowindex = new LinkedList<>();
        Queue<Integer> colindex = new LinkedList<>();
        rowindex.add(startRow);
        colindex.add(startCol);
        visited[startRow][startCol]= 1;
        int counter = 0;
        grid[startRow][startCol] = 'D';
        while (!rowindex.isEmpty()) {
            int temp = rowindex.size();
            for (int i = 0; i < temp; i++) {
                int tempx = rowindex.remove();
                int tempy = colindex.remove();
                int north = 1;
                int south = 1;
                int east = 1;
                int west = 1;
                while (tempx >= 0 && tempx < row && tempy + north >= 0 && tempy + north < col && grid[tempx][tempy + north] != '*' && visited[tempx][tempy + north] <= counter+1) {
                    if (visited[tempx][tempy + north] == 0){
                        visited[tempx][tempy + north] = counter + 1;
                        rowindex.add(tempx);
                        colindex.add(tempy + north);
                    }
                    if (grid[tempx][tempy + north] == 'C') {
                        System.out.println(counter);
                        return;
                    }
                    north++;
                }
                while (tempx >= 0 && tempx < row && tempy - south >= 0 && tempy - south < col && grid[tempx][tempy - south] != '*' && visited[tempx][tempy - south] <= counter+1) {
                    if (visited[tempx][tempy - south]==0) {
                        visited[tempx][tempy - south] = counter + 1;
                        rowindex.add(tempx);
                        colindex.add(tempy - south);

                    }
                    if (grid[tempx][tempy - south] == 'C') {
                        System.out.println(counter);
                        return;
                    }
                    south++;
                }
                while (tempx + east >= 0 && tempx + east < row && tempy >= 0 && tempy < col && grid[tempx + east][tempy] != '*' && visited[tempx + east][tempy] <= counter+1) {
                    if (visited[tempx + east][tempy] == 0) {
                        visited[tempx + east][tempy] = counter + 1;
                        rowindex.add(tempx + east);
                        colindex.add(tempy);
                    }
                    if (grid[tempx + east][tempy] == 'C') {
                        System.out.println(counter);
                        return;
                    }
                    east++;
                }
                while (tempx - west >= 0 && tempx - west < row && tempy >= 0 && tempy < col && grid[tempx - west][tempy] != '*' && visited[tempx - west][tempy] <= counter+1) {
                    if (visited[tempx - west][tempy] == 0) {
                        visited[tempx - west][tempy] = counter + 1;
                        rowindex.add(tempx - west);
                        colindex.add(tempy);
                    }
                    if (grid[tempx - west][tempy] == 'C') {
                        System.out.println(counter);
                        return;
                    }
                    west++;
                }
            }
            counter++;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(-1);
        pw.close();
    }
}
