import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Milk3 {

    static List<Integer> ans = new ArrayList<>();

    public static void solve(int a, int b, int c, boolean[][][] found, int a_max, int b_max, int c_max){
        if (found[a][b][c]){
            return;
        }
        found[a][b][c] = true;
        if (a == 0){
            ans.add(c);
        }
        int temp = Math.min(a, b_max - b);
        a -= temp;
        b += temp;
        solve(a, b, c, found, a_max, b_max, c_max);
        a += temp;
        b -= temp;
        int temp1 = Math.min(a, c_max - c);
        a -= temp1;
        c += temp1;
        solve(a, b, c, found, a_max, b_max, c_max);
        a += temp1;
        c -= temp1;
        int temp2 = Math.min(b, c_max - c);
        b -= temp2;
        c += temp2;
        solve(a, b, c, found, a_max, b_max, c_max);
        b += temp2;
        c -= temp2;
        int temp3 = Math.min(b, a_max - a);
        b -= temp3;
        a += temp3;
        solve(a, b, c, found, a_max, b_max, c_max);
        b += temp3;
        a -= temp3;
        int temp4 = Math.min(c, a_max - a);
        c -= temp4;
        a += temp4;
        solve(a, b, c, found, a_max, b_max, c_max);
        c += temp4;
        a -= temp4;
        int temp5 = Math.min(c, b_max - b);
        c -= temp5;
        b += temp5;
        solve(a, b, c, found, a_max, b_max, c_max);
        c += temp5;
        b -= temp5;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        boolean[][][] found = new boolean[21][21][21];
        solve(0, 0, c, found, a, b, c);
        Collections.sort(ans);
        for (int i : ans){
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
