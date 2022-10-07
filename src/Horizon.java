import java.io.*;
import java.util.*;

class Building implements Comparable<Building>{
    boolean start;
    long index;
    long height;
    public Building(boolean start, long index, long height){
        this.start = start;
        this.index = index;
        this.height = height;
    }
    public int compareTo(Building x) {
        return (int)(index - x.index);
    }
}


public class Horizon {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Building> list = new ArrayList<>(2*n);
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());
            long height = Integer.parseInt(st.nextToken());
            list.add(new Building(true, start, height));
            list.add(new Building(false, end, height));
        }
        Collections.sort(list);
        PriorityQueue<Long> heights = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long integer, Long t1) {
                return (int)(t1 - integer);
            }
        });
        long area = 0;
        long last = 0;
        heights.add(0L);
        for (int i=0; i < list.size(); i++){
            Building cur = list.get(i);
            if (cur.start){
                area += heights.peek()*(cur.index - last);
                heights.add(cur.height);
                last = cur.index;
            }
            else {
                area += heights.peek()*(cur.index - last);
                heights.remove(cur.height);
                last = cur.index;
            }
        }
        System.out.println(area);
    }
}
