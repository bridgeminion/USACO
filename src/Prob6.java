import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Prob6 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = 30;
        int[][][] grid = new int[3][3][n];
        for (int i=0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < 3; j++){
                int temp = Integer.parseInt(st.nextToken());
                grid[i][j][0]=temp;
            }
        }
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                pw.print(grid[i][j][0]);
                pw.print(" ");
            }
            pw.println();
        }
        int counter = 1;
        pw.println("start");
        for (int i=0; i < n-1; i++){
            if (grid[0][0][i] >= 2){
                grid[0][0][i+1] -= 2;
                grid[0][1][i+1]++;
                grid[1][0][i+1]++;
            }
            if (grid[0][1][i] >= 3){
                grid[0][1][i+1] -= 3;
                grid[0][2][i+1]++;
                grid[0][0][i+1]++;
                grid[1][1][i+1]++;
            }
            if (grid[0][2][i] >= 2){
                grid[0][2][i+1] -= 2;
                grid[0][1][i+1]++;
                grid[1][2][i+1]++;
            }
            if (grid[1][0][i]  >= 3){
                grid[1][0][i+1] -= 3;
                grid[0][0][i+1]++;
                grid[1][1][i+1]++;
                grid[2][0][i+1]++;
            }
            if (grid[1][1][i]  >= 4){
                grid[1][1][i+1] -= 4;
                grid[0][1][i+1]++;
                grid[1][0][i+1]++;
                grid[1][2][i+1]++;
                grid[2][1][i+1]++;
            }
            if (grid[1][2][i]  >= 3){
                grid[1][2][i+1] -= 3;
                grid[0][2][i+1]++;
                grid[1][1][i+1]++;
                grid[2][2][i+1]++;
            }
            if (grid[2][0][i]  >= 2){
                grid[2][0][i+1] -= 2;
                grid[2][1][i+1]++;
                grid[1][0][i+1]++;
            }
            if (grid[2][1][i]  >= 3){
                grid[2][1][i+1] -= 3;
                grid[2][0][i+1]++;
                grid[1][1][i+1]++;
                grid[2][2][i+1]++;
            }
            if (grid[2][2][i] >= 2){
                grid[2][2][i+1] -= 2;
                grid[2][1][i+1]++;
                grid[1][2][i+1]++;
            }
            for (int k=0; k < 3; k++){
                for (int j=0; j < 3; j++){
                    grid[k][j][i+1] += grid[k][j][i];
                    pw.print(grid[k][j][i]);
                    pw.print(" ");
                }
                pw.println();
            }
            pw.println(i);
        }
        pw.close();
    }
}
