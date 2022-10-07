import java.io.*;
import java.util.StringTokenizer;

public class BaleShare2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] size = new int[n+1];
        int total = 0;
        for (int i=0; i < n; i++){
            size[i+1] = Integer.parseInt(br.readLine());
            total += size[i+1];
        }
        int max = 3000;
//        boolean[][][] valid = new boolean[n+1][max][max];
        boolean[][] cur = new boolean[max][max];
        boolean[][] last = new boolean[max][max];
//        valid[0][0][0] = true;
        last[0][0] = true;
        for (int i=0; i < n; i++){
            for (int j=0; j < max; j++){
                for (int k=0; k < max; k++){
//                    if (valid[i][j][k]){
//                        valid[i+1][j][k] = true;
//                        valid[i+1][j+size[i+1]][k] = true;
//                        valid[i+1][j][k+size[i+1]] = true;
//                    }
                    if (last[j][k]){
                        cur[j][k] = true;
                        cur[j+size[i+1]][k] = true;
                        cur[j][k+size[i+1]] = true;
                    }
                }
            }
            for (int j=0; j < max; j++){
                System.arraycopy(cur[j], 0, last[j], 0, max);
            }
        }
        int ans = max;
        for (int j=0; j < max; j++){
            for (int k=0; k < max; k++){
                if (last[j][k]){
                    ans = Math.min(ans, Math.max(j, Math.max(k, total-j-k)));
                }
            }
        }
        System.out.println(ans);
    }
}
