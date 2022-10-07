import java.io.*;
import java.util.*;

public class CowJump {

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int index;
        public Point (int x, int y, int index){
            this.x = x;
            this.y = y;
            this.index = index;
        }

        @Override
        public int compareTo(Point point) {
            if (this.x == point.x){
                if (this.y == point.y){
                    return this.index - point.index;
                }
                return this.y - point.y;
            }
            return this.x - point.x;
        }
    }

    public static boolean check (Point a1, Point a2, Point b1, Point b2){
        return true;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Point> list = new ArrayList<>(n*2);
        Point[] first = new Point[n];
        Point[] second = new Point[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st1.nextToken());
            int y1 = Integer.parseInt(st1.nextToken());
            int x2 = Integer.parseInt(st1.nextToken());
            int y2 = Integer.parseInt(st1.nextToken());
            if (x1 > x2){
                second[i] = new Point(x1, y1, i);
                first[i] = new Point(x2, y2, i);
            }
            else {
                first[i] = new Point(x1, y1, i);
                second[i] = new Point(x2, y2, i);
            }
            list.add(new Point(x1, y1, i));
            list.add(new Point(x2, y2, i));
        }
        Collections.sort(list);
        Set<Integer> cur = new HashSet<>();
        boolean done = false;
        Point a1;
        Point a2;
        Point b1;
        Point b2;
        for (Point i : list){
            if (done){
                break;
            }
            if (cur.contains(i.index)){
                cur.remove(i.index);
                for (int j : cur){
                    if (check(first[i.index], second[i.index], first[j], second[j])){
                        done = true;
                        a1 = first[i.index];
                        a2 = second[i.index];
                        b1 = first[j];
                        b2 = second[j];
                        break;
                    }
                }
            }
        }
    }
}
