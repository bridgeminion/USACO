import java.io.*;

public class LongestCommonSubstring {

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
        val[x][y] = 0;
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
        int ans = 0;
//        for (int i=1; i <= a.length(); i++){
//            for (int j=1; j <= b.length(); j++){
//                if (a.charAt(i-1) == b.charAt(j-1)){
//                    val[i][j] = 1+val[i-1][j-1];
//                    ans = Math.max(ans, val[i][j]);
//                }
//            }
//        }
//        System.out.println(ans);
        boolean[][] visited = new boolean[a.length()][b.length()];
        for (int i=0; i < a.length(); i++){
            for (int j=0; j < b.length(); j++){
                ans = Math.max(ans, dp(a, b, i, j, val, visited));
            }
        }
        System.out.println(ans);
    }
}
