import java.io.*;
import java.util.StringTokenizer;

public class Shell {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shell.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("shell.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        boolean[] pebble1 = new boolean[3];
        boolean[] pebble2 = new boolean[3];
        boolean[] pebble3 = new boolean[3];
        pebble1[0] = true;
        pebble2[1] = true;
        pebble3[2] = true;
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            int guess = Integer.parseInt(st.nextToken());
            boolean one;
            one = pebble1[temp1-1];
            pebble1[temp1-1] = pebble1[temp2-1];
            pebble1[temp2-1] = one;
            if (pebble1[guess-1]){
                counter1++;
            }
            boolean two;
            two = pebble2[temp1-1];
            pebble2[temp1-1] = pebble2[temp2-1];
            pebble2[temp2-1] = two;
            if (pebble2[guess-1]){
                counter2++;
            }
            boolean three;
            three = pebble3[temp1-1];
            pebble3[temp1-1] = pebble3[temp2-1];
            pebble3[temp2-1] = three;
            if (pebble3[guess-1]){
                counter3++;
            }
        }
        int place = java.lang.Math.max(counter1, counter2);
        int ans = java.lang.Math.max(place, counter3);
        pw.println(ans);
        pw.close();
    }
}
