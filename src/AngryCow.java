import java.io.*;
import java.util.*;


public class AngryCow {

    public static boolean works (int[] pos, int k, int r){
        int counter = 0;
        int cur = -1;
        for (int i : pos){
            if (i > cur){
                counter++;
                cur = i+2*r;
            }
        }
        return counter <= k;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("angry.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] pos = new int[n];
        for (int i=0; i < n; i++){
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pos);
        int low = 0;
        int high = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (high+low)/2;
            if (works (pos, k, mid)){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        pw.println(low);
        pw.close();
    }



}
