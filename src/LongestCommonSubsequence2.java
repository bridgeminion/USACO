import java.io.*;

public class LongestCommonSubsequence2 {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String a = br.readLine();
        String b = br.readLine();
//        int[][] val = new int[a.length()+1][b.length()+1];
//        System.out.println(dp (a, b, a.length()-1, b.length()-1, val, new boolean[a.length()][b.length()]));
//        for (int i=1; i <= a.length(); i++){
//            for (int j=1; j <= b.length(); j++){
//                if (a.charAt(i-1) == b.charAt(j-1)){
//                    val[i][j] = 1+val[i-1][j-1];
//                }
//                else {
//                    val[i][j] = Math.max(val[i-1][j], val[i][j-1]);
//                }
//            }
//        }
        int[] dp1 = new int[b.length()+1];
        int[] dp2 = new int[b.length()+1];
        for (int i=1; i <= a.length(); i++){
            for (int j=1; j <= b.length(); j++){
                if (a.charAt(i-1) == b.charAt(j-1)){
                    dp1[j] = 1+dp2[j-1];
                }
                else {
                    dp1[j] = Math.max(dp1[j-1], dp2[j]);
                }
            }
            for (int j=1; j <= b.length(); j++){
                int temp = dp1[j];
                dp1[j] = dp2[j];
                dp2[j] = temp;
            }
        }
        System.out.println(Math.max(dp1[b.length()], dp2[b.length()]));
    }
}
