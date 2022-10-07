import java.io.*;
import java.util.*;

public class MilkingCows {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] milk = new int[1000001];
        int max_size = 0;
        int min_size = 1000000;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            milk[start]++;
            milk[end]--;
            if (end > max_size){
                max_size = end;
            }
            if (start < min_size){
                min_size = start;
            }
        }
        for (int i=1; i < 1000001; i++){
            milk[i] += milk[i-1];
        }
        int longest_milk = 0;
        int longest_none = 0;
        int cur_milk = 0;
        int cur_none = 0;
        for (int i=min_size; i <= max_size; i++){
            if (milk[i] > 0){
                cur_milk++;
                if (cur_none > longest_none){
                    longest_none = cur_none;
                }
                cur_none = 0;
            }
            else{
                cur_none++;
                if (cur_milk > longest_milk){
                    longest_milk = cur_milk;
                }
                cur_milk = 0;
            }
        }
        pw.print(longest_milk);
        pw.print(" ");
        pw.print(longest_none);
        pw.close();
    }
}
