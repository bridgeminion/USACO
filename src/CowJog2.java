import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CowJog2 {

    static class Run implements Comparable<Run>{
        Long pos;
        Long speed;
        public Run(Long pos, Long speed) {
            this.pos = pos;
            this.speed = speed;
        }

        @Override
        public int compareTo(Run run) {
            return run.pos.compareTo(this.pos);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Long t = Long.parseLong(st.nextToken());
        List<Run> jog = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            Run temp = new Run(Long.parseLong(st1.nextToken()), Long.parseLong(st1.nextToken()));
            jog.add(temp);
        }
        Collections.sort(jog);
        int ans = 0;
        long slowest = Long.MAX_VALUE;
        for (Run i : jog){
            if (i.pos + i.speed*t < slowest){
                ans++;
                slowest = i.pos + i.speed*t;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
