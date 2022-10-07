import java.io.*;
import java.util.*;

public class Cereal {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cereal.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] first = new int[n];
        int[] sec = new int[n];
        int[] occ = new int[m+1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st1.nextToken());
            int temp2 = Integer.parseInt(st1.nextToken());
            first[i] = temp1;
            sec[i] = temp2;
        }
        int[] ans = new int[n];
        int counter = 0;
        for (int i=n-1; i >= 0; i--){
            int j=i;
            int pos = first[i];
            while (true){
                if (occ[pos] == 0){
                    occ[pos] = j;
                    counter++;
                    break;
                }
                else if (occ[pos] < j){
                    break;
                }
                else {
                    int k = occ[pos];
                    occ[pos] = j;
                    if (pos == sec[k]){
                        break;
                    }
                    j = k;
                    pos = sec[k];
                }
            }
            ans[i] = counter;
        }
        for (int i=0; i < n; i++){
            pw.println(ans[i]);
        }
        pw.close();
    }
}
