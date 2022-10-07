import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cownomics2 {

    static int ans = 0;

    public static int convert (char a){
        if (a=='A'){
            return 0;
        }
        if (a=='T'){
            return 1;
        }
        if (a=='C'){
            return 2;
        }
        if (a=='G'){
            return 3;
        }
        return 0;
    }

    public static boolean check (List<String> spot, List<String> plain, int[] chosen){
        boolean[] used = new boolean[64];
        for (String i : spot){
            int temp = 0;
            for (int j=0; j < 3; j++){
                temp += convert(i.charAt(chosen[j]))*Math.pow(4, j);
            }
            used[temp] = true;
        }
        for (String i : plain){
            int temp = 0;
            for (int j=0; j < 3; j++){
                temp += convert(i.charAt(chosen[j]))*Math.pow(4, j);
            }
            if (used[temp]){
                return false;
            }
        }
        return true;
    }

    public static void generate (int num, int cur, int[] chosen, List<String> spot, List<String> plain, int m){
        if (num==3){
            if (check (spot, plain, chosen)){
                ans++;
            }
            return;
        }
        for (int i=cur+1; i < m; i++){
            chosen[num] = i;
            generate(num+1, i, chosen, spot, plain, m);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cownomics.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<String> spot = new ArrayList<>(n);
        List<String> plain = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            spot.add(br.readLine());
        }
        for (int i=0; i < n; i++){
            plain.add(br.readLine());
        }
        generate(0, -1, new int[3], spot, plain, m);
        System.out.println(ans);
    }
}
