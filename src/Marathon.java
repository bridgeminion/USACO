import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Marathon {

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
        return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("loan.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("loan.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Coordinate> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            list.add(new Coordinate(x, y));
        }
        int[][] val = new int[n+1][k+1];
        int sum = 0;
        for (int i=1; i < n; i++){
            val[i][0] = sum;
            sum += dist(list.get(i-1), list.get(i));
        }
        for (int i=2; i <= n; i++){
            for (int j=1; j <= k; j++){
                val[i][j] = 1000000;
                for (int a=1; a < i; a++){
                    if (i-a <= j+1){
                        val[i][j] = Math.min(val[i][j], dist(list.get(i-1), list.get(a-1)) + val[a][j-(i-a)+1]);
                    }
                }
            }
        }
        System.out.println(val[n][k]);
    }
}
