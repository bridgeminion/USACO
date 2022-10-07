import java.io.*;
import java.util.*;

public class Delivery {

    static class Point{
        int x;
        int y;

        public Point (int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int dist (Point a, Point b){
        return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }

    public static boolean inSeg (Point a, Point b, Point c){
        if (a.x == b.x){
            return a.x == c.x && ((a.y > c.y  && c.y >= b.y)||(a.y < c.y && c.y <= b.y));
        }
        if (a.y == b.y){
            return a.y == c.y && ((a.x > c.x && c.x >= b.x)||(a.x < c.x && c.x <= b.x));
        }
        return false;
    }

    public static boolean valid (Point a, Point b, List<Point> real){
        boolean good1 = true;
        for (Point i : real){
            if (!i.equals(a) && !i.equals(b)){
                if (inSeg(a, new Point(a.x, b.y), i) || inSeg(b, new Point(a.x, b.y), i)){
                    good1 = false;
                    break;
                }
            }
        }
        if (good1){
            return true;
        }
        for (Point i : real){
            if (!i.equals(a) && !i.equals(b)){
                if (inSeg(a, new Point(b.x, a.y), i) || inSeg(b, new Point(b.x, a.y), i)){
                    return false;
                }
            }
        }
        return true;
    }

    public static int shortest (Point a, Point b, List<Point> list, Map<Point, Integer> map, List<Point> real, Set<Point> realset){
        int[] key = new int[list.size()];
        int[] dist = new int[list.size()];
        for (int i=0; i < list.size(); i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[map.get(a)] = 0;
        Set<Integer> visited = new HashSet<>();
        while (true){
            int min_key = Integer.MAX_VALUE;
            int index = 0;
            for (int i=0; i < list.size(); i++){
                if (!visited.contains(i)){
                    if (key[i] < min_key){
                        min_key = key[i];
                        index = i;
                    }
                }
            }
            if (list.get(index).equals(b)){
                return min_key;
            }
            visited.add(index);
            dist[index] = min_key;
            for (int i=0; i < list.size(); i++){
                if (!visited.contains(i)){
                    if (valid(list.get(index), list.get(i), real) && key[i] > dist(list.get(index), list.get(i)) + min_key){
                        if (list.get(i).equals(b)){
                            return dist(list.get(index), list.get(i)) + min_key;
                        }
                        else if (!realset.contains(list.get(i))){
                            key[i] = dist(list.get(index), list.get(i)) + min_key;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Set<Point> set = new HashSet<>();
        List<Point> list = new ArrayList<>();
        Map<Point, Integer> map = new HashMap<>();
        List<Point> real = new ArrayList<>();
        Set<Point> realset = new HashSet<>();
        int counter = 0;
        boolean added;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            realset.add(new Point(x, y));
            real.add(new Point(x, y));
            added = set.add(new Point(x, y));
            if (added){
                list.add(new Point(x, y));
                map.put(new Point(x, y), counter);
                counter++;
            }
            for (int j=0; j < 4; j++){
                added = set.add(new Point(x+dx[j], y+dy[j]));
                if (added){
                    list.add(new Point(x+dx[j], y+dy[j]));
                    map.put(new Point(x+dx[j], y+dy[j]), counter);
                    counter++;
                }
            }
        }
        int ans = 0;
        for (int i=0; i < real.size()-1; i++){
            Point a = real.get(i);
            Point b = real.get(i+1);
            int temp = shortest(a, b, list, map, real, realset);
//            System.out.println("from " + i + " to " + (i+1) + ": " + temp);
            ans += temp;
        }
        int temp = shortest(real.get(real.size()-1), real.get(0), list, map, real, realset);
        ans += temp;
//        System.out.println("from " + (real.size()-1) + " to " + 0 + ": " + temp);
        System.out.println(ans);
    }
}
