import java.io.*;
import java.util.StringTokenizer;

public class LongestCommonSubsequence {

    public static int dp (String a, String b, int x, int y, int[][] val, boolean[][] visited){
        if (x==-1 || y==-1){
            return 0;
        }
        if (visited[x][y]){
            return val[x][y];
        }
        visited[x][y] = true;
        if (a.charAt(x) == b.charAt(y)){
            val[x][y] = 1 + dp(a, b, x-1, y-1, val, visited);
            return val[x][y];
        }
        val[x][y] = Math.max(dp(a, b, x-1, y, val, visited), dp(a, b, x, y-1, val, visited));
        return val[x][y];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String a = br.readLine();
        String b = br.readLine();
        int[][] val = new int[a.length()+1][b.length()+1];
//        System.out.println(dp (a, b, a.length()-1, b.length()-1, val, new boolean[a.length()][b.length()]));
        for (int i=1; i <= a.length(); i++){
            for (int j=1; j <= b.length(); j++){
                if (a.charAt(i-1) == b.charAt(j-1)){
                    val[i][j] = 1+val[i-1][j-1];
                }
                else {
                    val[i][j] = Math.max(val[i-1][j], val[i][j-1]);
                }
            }
        }
        System.out.println(val[a.length()][b.length()]);
    }
}
