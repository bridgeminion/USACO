import java.io.*;
import java.util.StringTokenizer;

public class BadMilk {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("badmilk.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("badmilk.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int num_friend, num_milk, transcript, num_sick;
        StringTokenizer st = new StringTokenizer(br.readLine());
        num_friend = Integer.parseInt(st.nextToken());
        num_milk = Integer.parseInt(st.nextToken());
        transcript = Integer.parseInt(st.nextToken());
        num_sick = Integer.parseInt(st.nextToken());
        boolean[][][] time = new boolean[num_friend][num_milk][100];
        boolean[][] sick = new boolean[num_friend][100];
        for (int i=0; i < transcript; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st1.nextToken());
            int milk = Integer.parseInt(st1.nextToken());
            int t = Integer.parseInt(st1.nextToken());
            for (int j=t-1; j < 100; j++){
                time[person-1][milk-1][j] = true;
            }
        }
        for (int i=0; i < num_sick; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st2.nextToken());
            int t = Integer.parseInt(st2.nextToken());
            for (int j=t-1; j < 100; j++){
                sick[person-1][j] = true;
            }
        }
        int ans = 0;
        for (int i=0; i < num_milk; i++){
            int counter = 0;
            boolean cause = true;
            for (int j = 0; j < num_friend && cause; j++){
                for (int t = 0; t < 100 && cause; t++){
                    if (sick[j][t] && !time[j][i][t]){
                        cause = false;
                    }
                }
            }
            if (cause){
                for (int j=0; j < num_friend; j++){
                    if (time[j][i][99]){
                        counter++;
                    }
                }
            }
            if (counter > ans){
                ans = counter;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
