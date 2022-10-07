import java.io.*;
import java.util.*;

public class MoocastGold {

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

    public static int cost (Coordinate a, Coordinate b){
        return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moocast.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Coordinate(x, y));
        }
        int[][] cost = new int[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                cost[i][j] = cost(list.get(i), list.get(j));
            }
        }
        int[] key = new int[n];
        for (int i=0; i < n; i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[0] = 0;
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        for (int i=0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j=0; j < n; j++){
                if (!visited.contains(j) && key[j] < min){
                    min = key[j];
                    index = j;
                }
            }
            visited.add(index);
            ans = Math.max(ans, key[index]);
            for (int j=0; j < n; j++){
                if (!visited.contains(j)){
                    key[j] = Math.min(key[j], cost[index][j]);
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
