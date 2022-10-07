import java.io.*;
import java.util.StringTokenizer;

public class Taming {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("taming.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] input = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            input[i] = Integer.parseInt(st1.nextToken());
        }
        for (int i=n-1; i >= 1; i--){
            if (input[i] > 0){
                if (input[i-1] == -1){
                    input[i-1] = input[i] - 1;
                }
                else if (input[i-1] != input[i] - 1){
                    pw.println(-1);
                    pw.close();
                    return;
                }
            }
        }
        input[0] = 0;
        int counter_min = 0;
        int counter_max = 0;
        for (int i=0; i < n; i++){
            System.out.println(input[i]);
            if (input[i] == 0){
                counter_max++;
                counter_min++;
            }
            if (input[i] == -1){
                counter_max++;
            }
        }
        pw.print(counter_min);
        pw.print(" ");
        pw.print(counter_max);
        pw.close();
    }
}
