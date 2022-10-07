import java.io.*;
import java.util.StringTokenizer;

public class MixMilk {
    public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
                PrintWriter pw = new PrintWriter(new FileWriter("mixmilk.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[] capacity = new int[3];
        int[] amount = new int[3];
        for (int i=0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            capacity[i] = Integer.parseInt(st.nextToken());
            amount[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=0; i < 100; i++){
            if (amount[i%3] + amount[(i+1)%3] <= capacity[(i+1)%3]){
                amount[(i+1)%3] += amount[i%3];
                amount[i%3] = 0;
            }
            else{
                amount[i%3] = amount[i%3] + amount[(i+1)%3] - capacity[(i+1)%3];
                amount[(i+1)%3] = capacity[(i+1)%3];
            }
        }
        for (int i=0; i < 3; i++){
            pw.println(amount[i]);
        }
        pw.close();
    }
}
