import java.io.*;
import java.util.*;

public class CowRace {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int bes_pos = 0;
        int els_pos = 0;
        int[] bes = new int[1000001];
        int[] els = new int[1000001];
        int total_time_bes = 0;
        int total_time_els = 0;
        int counter = 0;
        boolean bessieIsAhead = false;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int speed = Integer.parseInt(st1.nextToken());
            int time = Integer.parseInt(st1.nextToken());
            for (int j=total_time_bes; j < total_time_bes + time; j++){
                bes[j] = bes_pos;
                bes_pos += speed;
            }
            total_time_bes += time;
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int speed = Integer.parseInt(st1.nextToken());
            int time = Integer.parseInt(st1.nextToken());
            for (int j=total_time_els; j < total_time_els + time; j++){
                els[j] = els_pos;
                els_pos += speed;
            }
            total_time_els += time;
        }
        int index = 0;
        while (bes[index] == els[index] && index < 1000000){
            index++;
        }
        bessieIsAhead = bes[index] > els[index];
        for (int i=index; i < total_time_bes; i++){
            if (bes[i] < els[i] && bessieIsAhead){
                counter++;
                bessieIsAhead = false;
            }
            else if (bes[i] > els[i] && !bessieIsAhead){
                counter++;
                bessieIsAhead = true;
            }
        }
        System.out.println(counter);
    }
}
