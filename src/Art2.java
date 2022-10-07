import java.io.*;
import java.util.*;

public class Art2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        for (int i=0; i < n; i++){
            input[i] = Integer.parseInt(br.readLine());
        }
        Set<Integer> seen = new HashSet<>();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i=0; i < n; i++){
            start[i] = -1;
            end[i] = -1;
        }
        for (int i=0; i < n; i++){
            if (!seen.contains(input[i])){
                seen.add(input[i]);
                start[i] = input[i];
            }
        }
        seen.clear();
        for (int i=n-1; i >= 0; i--){
            if (!seen.contains(input[i])){
                seen.add(input[i]);
                end[i] = input[i];
            }
        }
        int ans = 0;
        List<Integer> cur = new ArrayList<>();
        int temp = 0;
        for (int i=0; i < n; i++){
            if (start[i] != -1){
                if (input[i] != 0){
                    temp++;
                }
                cur.add(start[i]);
            }
            ans = Math.max(ans, temp);
            if (end[i] != -1){
                if (input[i] != 0){
                    temp--;
                }
                if (cur.get(cur.size()-1)!=input[i]){
                    System.out.println(-1);
                    return;
                }
                cur.remove(cur.get(cur.size()-1));
            }
        }
        System.out.println(ans);
    }
}
