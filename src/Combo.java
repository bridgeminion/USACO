import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Combo {

    public static boolean check (int[] fj, int[] master, int cur1, int cur2, int cur3, int n){
        if (close(fj[0], cur1, n) && close(fj[1], cur2, n) && close(fj[2], cur3, n)){
            return true;
        }
        return close(master[0], cur1, n) && close(master[1], cur2, n) && close(master[2], cur3, n);
    }

    public static boolean close (int a, int b, int n){
        if (Math.abs((a - b) % n) < 3){
            return true;
        }
        return Math.abs((a - b) % n) > n - 3;
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] fj = new int[3];
        int[] master = new int[3];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < 3; i++){
            fj[i] = Integer.parseInt(st1.nextToken());
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i=0; i < 3; i++){
            master[i] = Integer.parseInt(st2.nextToken());
        }
        int counter = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                for (int k=0; k < n; k++){
                    if (check(fj, master, i, j, k, n)){
                        counter++;
                    }
                }
            }
        }
        pw.println(counter);
        pw.close();
    }
}
