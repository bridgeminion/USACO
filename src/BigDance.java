import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BigDance {

    static int ans = 0;

    public static void recurse(int start, int end){
        if (end == start){
            return;
        }
        if (end == start + 1){
            ans += start*end;
            return;
        }
        int mid = (start + end)/2;
        recurse(start, mid);
        recurse(mid + 1, end);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        recurse(1, n);
        System.out.println(ans);
    }
}
