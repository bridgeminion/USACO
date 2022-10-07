import java.io.*;
import java.util.*;

public class Moocast {

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

    static class Cow {
        Coordinate pos;
        int pow;
        public Cow (Coordinate pos, int pow){
            this.pos = pos;
            this.pow = pow;
        }
    }

    public static boolean adj (Cow a, Cow b){
        int dist = (a.pos.x - b.pos.x)*(a.pos.x - b.pos.x) + (a.pos.y-b.pos.y)*(a.pos.y-b.pos.y);
        return dist <= a.pow*a.pow;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moocast.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Cow> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int pow = Integer.parseInt(st.nextToken());
            list.add(new Cow(new Coordinate(x, y), pow));
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (adj (list.get(i), list.get(j))){
                    map.putIfAbsent(i, new ArrayList<>());
                    map.get(i).add(j);
                }
            }
        }
        int ans = 0;
        int counter = 0;
        for (int i=0; i < n; i++){
            counter = 0;
            boolean[] visited = new boolean[n];
            visited[i] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while (!q.isEmpty()){
                int cur = q.remove();
                counter++;
                if (map.containsKey(cur)){
                    for (int j : map.get(cur)){
                        if (!visited[j]){
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
            }
            ans = Math.max(ans, counter);
        }
        pw.println(ans);
        pw.close();
    }
}
