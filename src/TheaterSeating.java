import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Seat implements Comparable<Seat> {
    int dist;
    int row;
    int col;

    public Seat(int dist, int row, int col){
        this.dist = dist;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Seat seat) {
        if (this.dist == seat.dist){
            if (this.row > seat.row){
                return (-1);
            }
            else if (this.row < seat.row){
                return (1);
            }
            else{
                if (this.col < seat.col){
                    return (-1);
                }
                else if (this.col > seat.col){
                    return (1);
                }
                else{
                    return (0);
                }
            }
        }
        return (this.dist - seat.dist);
    }
}

public class TheaterSeating {

    public static int distance (int x1, int y1, int x2, int y2){
        return ((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1- y2));
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cols = Integer.parseInt(st.nextToken());
        int num_rows = Integer.parseInt(st.nextToken());
        int best_row = num_rows-1;
        int best_col = (num_cols-1)/2;
        List<Seat> theater = new ArrayList<>();
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                Seat temp = new Seat(0, 0, 0);
                temp.dist = distance(i, j, best_row, best_col);
                temp.row = i;
                temp.col = j;
                theater.add(temp);
            }
        }
        Collections.sort(theater);
        int[][] chart = new int[num_cols][num_rows];
        int counter = 1;
        for (Seat i : theater){
            int row = i.row;
            int col = i.col;
            chart[col][row] = counter;
            counter++;
        }
        for (int i=0; i < num_rows; i++){
            for (int j=0; j < num_cols; j++){
                pw.print(chart[j][i]);
                pw.print(" ");
            }
            pw.println();
        }
        pw.close();
    }

}
