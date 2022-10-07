import java.io.*;
import java.util.StringTokenizer;

public class MilkOrder {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("taming.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n, num_order, num_pos;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        num_order = Integer.parseInt(st.nextToken());
        num_pos = Integer.parseInt(st.nextToken());
        int[] input = new int[n];
        int[] order = new int[num_order];
        int[] cow = new int[num_pos];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < num_order; i++){
            order[i] = Integer.parseInt(st1.nextToken());
        }
        for (int i=0; i < num_pos; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            cow[i] = Integer.parseInt(st2.nextToken());
            int pos = Integer.parseInt(st2.nextToken());
            input[pos - 1] = cow[i];
        }
        for (int i=0; i < num_pos; i++){
            for (int j=num_order - 1; j >= 0; j++){
                if (order[j] == cow[i]){
                    for (int k=0; k < n; k++){
                        if (input[k] == cow[i]){

                        }
                    }
                }
            }
        }
        pw.close();
    }
}
