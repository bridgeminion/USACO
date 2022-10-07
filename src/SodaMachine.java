import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SodaMachine {

    static class Point implements Comparable<Point>{
        int pos;
        int start0end1;
        public Point (int pos, int start0end1){
            this.pos = pos;
            this.start0end1 = start0end1;
        }

        @Override
        public int compareTo(Point point) {
            if (this.pos == point.pos){
                return point.start0end1 - this.start0end1;
            }
            return this.pos - point.pos;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Point> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            list.add(new Point(start, 0));
            list.add(new Point(end+1, 1));
        }
        Collections.sort(list);
        int ans = 1;
        int cur = 0;
        for (Point i : list){
            if (i.start0end1 == 0){
                cur++;
            }
            else {
                cur--;
            }
            ans = Math.max(ans, cur);
        }
        System.out.println(ans);
    }
}

