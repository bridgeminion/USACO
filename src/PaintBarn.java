import org.w3c.dom.css.Rect;

import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    boolean start;
    int x;
    int y;
    int index;
    public Point(boolean start, int x, int y, int index){
        this.start = start;
        this.x = x;
        this.y = y;
        this.index = index;
    }

    @Override
    public int compareTo(Point x) {
        return this.x - x.x;
    }
}

public class PaintBarn {

    public static int total (List<Point> cur, int k){
        int num = 0;
        int ans = 0;
        int last = 0;
        for (Point j : cur){
            if (num == k){
                ans += j.y - last;
            }
            if (j.start){
                num++;
            }
            else {
                num--;
            }
            last = j.y;
        }
        return ans;
    }

//    public static void main(String[] args) throws IOException {
////        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
////        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int num_rectangles = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//        Point[] starts = new Point[num_rectangles];
//        Point[] ends = new Point[num_rectangles];
//        List<Point> list = new ArrayList<>(num_rectangles*2);
//        for (int i=0; i < num_rectangles; i++){
//            StringTokenizer st1 = new StringTokenizer(br.readLine());
//            Point first = new Point(true, Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()), i);
//            Point last = new Point(false, Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()), i);
//            list.add(first);
//            list.add(last);
//            starts[i] = first;
//            ends[i] = last;
//        }
//        Collections.sort(list);
//        int num = 0;
//        int last = 0;
//        int ans = 0;
//        List<Point> cur = new ArrayList<>();
//        for (Point i : list){
//            if (!i.start){
//                num--;
//            }
//            if (num == k){
//                Collections.sort(cur);
//                ans += total(cur, k)*(i.x-last);
//            }
//            if (i.start){
//                num++;
//                cur.add(i);
//                cur.add(ends[i.index]);
//            }
//            else {
//                cur.remove(i);
//                cur.remove(starts[i.index]);
//            }
//            last = i.x;
//        }
//        pw.println(ans);
//        pw.close();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rectangles = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] num = new int[1005][1005];
        List<Point> list = new ArrayList<>(num_rectangles*2);
        for (int i=0; i < num_rectangles; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st1.nextToken());
            int y1 = Integer.parseInt(st1.nextToken());
            int x2 = Integer.parseInt(st1.nextToken());
            int y2 = Integer.parseInt(st1.nextToken());
            for (int j=x1; j < x2; j++){
                num[j][y1]++;
                num[j][y2]--;
            }
        }
        int ans = 0;
        for (int i=0; i < 1000; i++){
            for (int j=0; j < 1000; j++){
                if (num[i][j] == k){
                    ans++;
                }
                num[i][j+1] += num[i][j];
            }
        }
        pw.println(ans);
        pw.close();
    }

}
