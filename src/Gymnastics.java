import java.io.*;
import java.util.StringTokenizer;

public class Gymnastics {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("gymnastics.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_practice = Integer.parseInt(st.nextToken());
        int num_cows = Integer.parseInt(st.nextToken());
        int[][] stat = new int[num_practice][num_cows];
        for (int i=0; i < num_practice; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < num_cows; j++){
                int temp = Integer.parseInt(st1.nextToken());
                stat[i][temp-1] = j;
            }
        }
        int counter = 0;
        for (int i=0; i < num_cows; i++){
            for (int j=0; j < num_cows; j++){
                if (i != j){
                    boolean match = true;
                    for (int k=0; k < num_practice; k++){
                        if (stat[k][i] > stat[k][j]){
                            match = false;
                        }
                    }
                    if (match){
                        counter++;
                    }
                }
            }
        }
        pw.println(counter);
        pw.close();
    }
}
