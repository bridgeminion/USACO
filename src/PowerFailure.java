import java.io.*;
import java.util.*;

public class PowerFailure {

    static class Coordinate{
        long x;
        long y;
        public Coordinate(long x, long y){
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

    public static double cost (Coordinate a, Coordinate b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        double m = Double.parseDouble(br.readLine());
        List<Coordinate> list = new ArrayList<>(n+1);
        list.add(new Coordinate(0, 0));
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            list.add(new Coordinate(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
        }
        double[][] cost = new double[n+1][n+1];
        for (int i=1; i <= n; i++){
            for (int j=1; j <= n; j++){
                cost[i][j] = cost(list.get(i), list.get(j));
            }
        }
        for (int i=0; i < w; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            cost[a][b] = 0;
            cost[b][a] = 0;
        }
        double[] key = new double[n+1];
        for (int i=1; i <= n; i++){
            key[i] = Double.MAX_VALUE;
        }
        key[1] = 0;
        Set<Integer> visited = new HashSet<>();
        while (true){
            double min = Double.MAX_VALUE;
            int index = 0;
            for (int i=1; i <= n; i++){
                if (!visited.contains(i) && key[i] < min){
                    index = i;
                    min = key[i];
                }
            }
            if (index == 0){
                System.out.println(-1);
                return;
            }
            if (index == n){
                System.out.println((int)(key[index]*1000));
                return;
            }
            visited.add(index);
            for (int i=1; i <= n; i++){
                if (!visited.contains(i) && cost[index][i] <= m){
                    key[i] = Math.min(key[i], cost[index][i] + key[index]);
                }
            }
        }
    }
}
