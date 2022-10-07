import java.io.*;
import java.util.*;

class Run implements Comparable<Run> {
    int pos;
    int speed;
    public Run(int pos, int speed){
        this.pos = pos;
        this.speed = speed;
    }

    @Override
    public int compareTo(Run run) {
        return run.pos - this.pos;
    }
}

public class CowJog {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Run> jog = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            Run temp = new Run(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
            jog.add(temp);
        }
        Collections.sort(jog);
        int ans = 0;
        int slow = 1000000001;
        for (Run i : jog){
            if (i.speed <= slow){
                ans++;
                slow = i.speed;
            }
        }
        pw.println(ans);
        pw.close();
    }

}
