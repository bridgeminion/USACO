import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Y implements Comparable<Y> {
    int y;
    boolean start;
    public Y(int y, boolean start){
        this.y = y;
        this.start = start;
    }
    @Override
    public int compareTo(Y x) {
        return x.y - this.y;
    }
}

class Rectangle implements Comparable<Rectangle> {
    int x1;
    Y y1;
    int x2;
    Y y2;
    public Rectangle(int x1, Y y1, int x2, Y y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public int compareTo(Rectangle x) {
        if (x.x1 == this.x1){
            return x.y1.y - this.y1.y;
        }
        return x.x1 - this.x1;
    }
}

public class Overplanting {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int left = 10001;
        int right = -10001;
        List<Rectangle> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st1.nextToken());
            Y y1 = new Y(Integer.parseInt(st1.nextToken()), true);
            int x2 = Integer.parseInt(st1.nextToken());
            Y y2 = new Y(Integer.parseInt(st1.nextToken()), false);
            list.add(new Rectangle(x1, y1, x2, y2));
            if (x1 < left){
                left = x1;
            }
            if (x2 > right){
                right = x2;
            }
        }
        int num_active = 0;
        int top = -10001;
        int ans = 0;
        List<Y> current = new ArrayList<>();
        for (int i=left; i <= right; i++){
            for (Rectangle j : list){
                if (j.x1 <= i && j.x2 > i){
                    current.add(j.y1);
                    current.add(j.y2);
                }
            }
            Collections.sort(current);
            for (Y temp : current){
                if (temp.start){
                    num_active++;
                    if (temp.y > top){
                        top = temp.y;
                    }
                }
                else{
                    num_active--;
                }
                if (num_active == 0){
                    ans += top - temp.y;
                    top = -10001;
                }
            }
            current.clear();
        }
        System.out.println(ans);
    }
}
