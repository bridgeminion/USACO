import java.io.*;
import java.util.*;

public class Balancing {

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


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("balancing.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Coordinate> list = new ArrayList<>(n);
        Map<Integer, Integer> count = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) );
            count.putIfAbsent(list.get(i).y, 0);
            count.put(list.get(i).y, count.get(list.get(i).y)+1);
        }
        list.sort(new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return o1.x - o2.x;
            }
        });
        int ans = Integer.MAX_VALUE;
        for (int i=0; i < n; i++){
            int y = list.get(i).y+1;
            int above = 0;
            int below = 0;
            Map<Integer, Integer> up = new HashMap<>();
            for (Coordinate j : list){
                up.putIfAbsent(j.y, 0);
                if (j.y > y){
                    up.put(j.y, up.get(j.y)+1);
                    above++;
                }
                else {
                    below++;
                }
            }
            int topleft = above;
            int topright = 0;
            int botleft = below;
            int botright = 0;
            Set<Integer> seen = new HashSet<>();
            for (Coordinate j : list){
                if (seen.contains(j.y)){
                    continue;
                }
                seen.add(j.y);
                topleft -= up.get(j.y);
                topright += up.get(j.y);
                botleft -= count.get(j.y) - up.get(j.y);
                botright += count.get(j.y) - up.get(j.y);
                ans = Math.min(ans, Math.max(Math.max(topleft, topright), Math.max(botleft, botright)));
            }
        }
        pw.println(ans);
        pw.close();
    }
}
