import java.io.*;
import java.util.*;

class Conv implements Comparable<Conv> {
    int arrive;
    int time;
    int index;
    public Conv(int arrive, int time, int index){
        this.arrive = arrive;
        this.time = time;
        this.index = index;
    }

    @Override
    public int compareTo(Conv x) {
        return this.time-x.time;
    }
}

public class Convention2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("convention2.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Conv> list = new PriorityQueue<>(Comparator.comparingInt(conv -> conv.arrive));
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int arrive = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            list.add(new Conv(arrive, time, i));
        }
        PriorityQueue<Conv> waiting = new PriorityQueue<>(Comparator.comparingInt(conv -> conv.index));
        Conv start = list.poll();
        int ans = 0;
        int cur = start.arrive;
        while (list.size() >= 1){
            cur += start.time;
            while (list.peek() != null && list.peek().arrive < cur){
                waiting.add(list.poll());
            }
            if (waiting.size() != 0){
                start = waiting.poll();
                if (ans < cur - start.arrive){
                    ans = cur - start.arrive;
                }
            }
            else {
                start = list.poll();
                cur = start.arrive;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
