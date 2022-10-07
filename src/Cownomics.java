import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Cownomics {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cownomics.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] input = new char[n*2][m];
        for (int i=0; i < n*2; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < m; j++){
                input[i][j] = temp.charAt(j);
            }
        }
        boolean[][][] used = new boolean[4][2][m];
        for (int i=0; i < n; i++){
            for (int j=0; j < m; j++){
                if (input[i][j] == 'A'){
                    used[0][0][j] = true;
                }
                if (input[i][j] == 'C'){
                    used[1][0][j] = true;
                }
                if (input[i][j] == 'G'){
                    used[2][0][j] = true;
                }
                if (input[i][j] == 'T'){
                    used[3][0][j] = true;
                }
            }
        }
        for (int i=n; i < n*2; i++){
            for (int j=0; j < m; j++){
                if (input[i][j] == 'A'){
                    used[0][1][j] = true;
                }
                if (input[i][j] == 'C'){
                    used[1][1][j] = true;
                }
                if (input[i][j] == 'G'){
                    used[2][1][j] = true;
                }
                if (input[i][j] == 'T'){
                    used[3][1][j] = true;
                }
            }
        }
        int ans = m;
        for (int i=0; i < m; i++){
            for (int j=0; j < 4; j++){
                if (used[j][0][i] && used[j][1][i]){
                    ans--;
                    break;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}


