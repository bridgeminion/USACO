import java.io.*;

public class StringEditDistance {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String a = br.readLine();
        String b = br.readLine();
        int[][] val = new int[a.length()+1][b.length()+1];
//        System.out.println(dp (a, b, a.length()-1, b.length()-1, val, new boolean[a.length()][b.length()]));
        for (int i=0; i <= a.length(); i++){
            val[i][0] = i;
        }
        for (int i=0; i <= b.length(); i++){
            val[0][i] = i;
        }
        for (int i=1; i <= a.length(); i++){
            for (int j=1; j <= b.length(); j++){
                int case1 = 1;
                if (a.charAt(i-1)==b.charAt(j-1)){
                    case1 = 0;
                }
                int case2 = 1+val[i][j-1];
                int case3 = 1+val[i-1][j];
                int temp = Math.min(case2, case3);
                val[i][j] = Math.min(val[i-1][j-1] + case1, temp);
            }
        }
        System.out.println(val[a.length()][b.length()]);
    }
}
