import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CountyFair {

    static class Booth {
        int time;
        int index;

        public Booth(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Booth[] time = new Booth[n];
        for (int i=0; i < n; i++){
            time[i] = new Booth(Integer.parseInt(br.readLine()), i);
        }
        int[][] cost = new int[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                cost[i][j] = Integer.parseInt(br.readLine());
            }
        }
        int[] count = new int[n];
        int ans = 1;
        Arrays.sort(time, new Comparator<Booth>() {
            @Override
            public int compare(Booth o1, Booth o2) {
                if (o1.time == o2.time){
                    return 1;
                }
                return o1.time - o2.time;
            }
        });
        for (int i=0; i < n; i++){
            if (time[i].time >= cost[0][time[i].index]){
                count[i] = 1;
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (i != j && count[i] > 0 && time[i].time + cost[time[i].index][time[j].index] <= time[j].time){
                    count[j] = Math.max(count[i]+1, count[j]);
                    ans = Math.max(ans, count[j]);
                }
            }
        }
        System.out.println(ans);
    }
}
