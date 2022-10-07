import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Checklist {

    static class Coordinate{
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static int dist (Coordinate a, Coordinate b){
        return Math.abs(a.x-b.x)*Math.abs(a.x-b.x) + Math.abs(a.y-b.y)*Math.abs(a.y-b.y);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("hps.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        List<Coordinate> hcow = new ArrayList<>(h+1);
        List<Coordinate> gcow = new ArrayList<>(g+1);
        for (int i=0; i < h; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            hcow.add(new Coordinate(x, y));
        }
        for (int i=0; i < g; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            gcow.add(new Coordinate(x, y));
        }
        int[][][] val = new int[h+1][g+1][2];
        val[1][1][1] = dist(hcow.get(0), gcow.get(0));
        for (int j=2; j <= g; j++){
            val[1][j][1] = val[1][j-1][1] + dist(gcow.get(j-1), gcow.get(j-2));
        }
        for (int i=2; i <= h; i++){
            val[i][0][0] = val[i-1][0][0] + dist(hcow.get(i-1), hcow.get(i-2));
        }
        for (int j=1; j <= g; j++) {
            val[2][j][0] = val[1][j][1] + dist(hcow.get(1), gcow.get(j - 1));
        }
        val[2][1][1] = val[2][0][0] + dist(hcow.get(1), gcow.get(0));
        for (int j=2; j <= g; j++){
            val[2][j][1] = Math.min(val[2][j-1][0]+dist(gcow.get(j-1), hcow.get(1)), val[2][j-1][1]+dist(gcow.get(j-1), gcow.get(j-2)));
        }
        for (int i=3; i <= h; i++){
            for (int j=1; j <= g; j++){
                for (int k=0; k < 2; k++){
                    if (k==0){
                        if (j>1){
                            val[i][j][k] = Math.min(val[i-1][j][0]+dist(hcow.get(i-1), hcow.get(i-2)), val[i-1][j][1]+dist(hcow.get(i-1), gcow.get(j-1)));
                        }
                        else {
                            val[i][j][k] = Math.min(val[i-1][j][1]+dist(hcow.get(i-1), gcow.get(j-1)), val[i-1][j][0]+dist(hcow.get(i-1), hcow.get(i-2)));
                        }
                    }
                    else {
                        if (j>1){
                            val[i][j][k] = Math.min(val[i][j-1][1]+dist(gcow.get(j-1), gcow.get(j-2)), val[i][j-1][0]+dist(gcow.get(j-1), hcow.get(i-1)));
                        }
                        else {
                            val[i][j][k] = val[i][j-1][0]+dist(gcow.get(j-1), hcow.get(i-1));
                        }
                    }
                }
            }
        }
        System.out.println(val[h][g][0]);
    }
}
