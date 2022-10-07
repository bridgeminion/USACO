import java.io.*;
import java.util.*;

class Rest implements Comparable<Rest> {
    long pos;
    long taste;
    public Rest(long pos, long taste){
        this.pos = pos;
        this.taste = taste;
    }

    @Override
    public int compareTo(Rest rest) {
        return (int)(rest.taste - this.taste);
    }
}

public class RestStops {


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        int fjRate = Integer.parseInt(st.nextToken());
        int bRate = Integer.parseInt(st.nextToken());
        int time = fjRate - bRate;
        Rest[] stops = new Rest[num];
        for (int i=0; i < num; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            stops[i] = new Rest(Long.parseLong(st1.nextToken()), Long.parseLong(st1.nextToken()));
        }
        Arrays.sort(stops);
        long ans = 0;
        long farthest = 0;
        for (int i=0; i < num; i++){
            if (stops[i].pos > farthest){
                ans += time*stops[i].taste*(stops[i].pos - farthest);
                farthest = stops[i].pos;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
