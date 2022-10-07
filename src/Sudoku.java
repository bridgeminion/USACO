import java.io.*;
import java.util.StringTokenizer;

public class Sudoku {

    static boolean done = false;

    public static boolean check (int guess, int[][] grid, int row, int col){
        for (int i=0; i < 9; i++){
            if (i != col && grid[row][i] == guess){
                return false;
            }
            if (i != row && grid[i][col] == guess){
                return false;
            }
        }
        if (row <= 2 && col <= 2){
            for (int i=0; i <= 2; i++){
                for (int j=0; j <=2; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 2 && col <= 5){
            for (int i=0; i <= 2; i++){
                for (int j=3; j <= 5; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 2 && col <= 8){
            for (int i=0; i <= 2; i++){
                for (int j=6; j <=8; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 5 && col <= 2){
            for (int i=3; i <= 5; i++){
                for (int j=0; j <=2; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 5 && col <= 5){
            for (int i=3; i <= 5; i++){
                for (int j=3; j <=5; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 5 && col <= 8){
            for (int i=3; i <= 5; i++){
                for (int j=6; j <=8; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 8 && col <= 2){
            for (int i=6; i <= 8; i++){
                for (int j=0; j <=2; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else if (row <= 8 && col <= 5){
            for (int i=6; i <= 8; i++){
                for (int j=3; j <=5; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        else{
            for (int i=6; i <= 8; i++){
                for (int j=6; j <=8; j++){
                    if (grid[i][j] == guess){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void recurse (int[][] grid, int row, int col){
        if (row >= 9){
            for (int i=0; i < 9; i++){
                for (int j=0; j < 9; j++){
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            done = true;
            return;
        }
        if (grid[row][col] == 0){
            for (int guess=1; guess <= 9; guess++){
                if (check(guess, grid, row, col)){
                    grid[row][col] = guess;
                    if (col == 8){
                        recurse(grid, row+1, 0);
                    }
                    else{
                        recurse(grid, row, col + 1);
                    }
                }
                grid[row][col] = 0;
            }
        }
        else{
            if (col == 8){
                recurse(grid, row+1, 0);
            }
            else{
                recurse(grid, row, col + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] grid = new int[9][9];
        for (int i=0; i < 9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < 9; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recurse(grid, 0, 0);
        if (!done){
            System.out.println("NO SOLUTION");
        }
        pw.close();
    }
}
