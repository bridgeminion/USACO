import java.io.*;
import java.util.*;

public class Shuffle {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("shuffle.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] shuffle = new int[n];
        String[] name = new String[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            shuffle[i] = Integer.parseInt(st1.nextToken());
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            name[i] = st2.nextToken();
        }
        int[] reverse = new int[n];
        for (int i=0; i < n; i++){
            reverse[shuffle[i] - 1] = i;
        }
        for (int i=0; i < n; i++){
//            System.out.println(name[i]);
            System.out.println(reverse[i]);
        }
        String[] after1 = new String[n];
        String[] after2 = new String[n];
        String[] after3 = new String[n];
        for (int i=0; i < n; i++){
            after1[reverse[i]] = name[i];
        }
        for (int i=0; i < n; i++){
            after2[reverse[i]] = after1[i];
        }
        for (int i=0; i < n; i++){
            after3[reverse[i]] = after2[i];
        }
        for (int i=0; i < n; i++){
            pw.println(after3[i]);
        }
        pw.close();

    }
}
