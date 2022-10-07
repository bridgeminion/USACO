import java.io.*;
import java.util.*;

public class MooParticle {

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
        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Coordinate> list = new ArrayList<>(n);
        for (int i=0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Coordinate(x, y));
        }
        list.sort(new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate coordinate, Coordinate t1) {
                if (coordinate.x == t1.x) {
                    return coordinate.y-t1.y;
                }
                return coordinate.x - t1.x;
            }
        });
        List<Integer> cur = new ArrayList<>();
        for (Coordinate i : list){
            int index = Collections.binarySearch(cur, i.y);
            if (index >= 0){
                while (index < cur.size() && cur.get(index) == i.y){
                    index++;
                }
            }
            if (index < 0){
                index = -(index+1);
            }
            if (index == 0){
                cur.add(0, i.y);
            }
            else {
                int least = cur.get(0);
                cur.subList(0, index).clear();
                cur.add(0, least);
            }
        }
        pw.println(cur.size());
        pw.close();
    }
}
