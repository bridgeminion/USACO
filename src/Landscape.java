import java.io.*;
import java.util.StringTokenizer;

public class Landscape {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        int[] start = new int[1001];
        int[] end = new int[1001];
        int counter1 = 0;
        int counter2 = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            for (int j=0; j < a; j++){
                start[counter1] = i;
                counter1++;
            }
            int b = Integer.parseInt(st1.nextToken());
            for (int j=0; j < b; j++){
                end[counter2] = i;
                counter2++;
            }
        }
        int[][] val = new int[1001][1001];
        for (int i=0; i <= counter1; i++){
            val[i][0] = i*y;
        }
        for (int i=0; i <= counter2; i++){
            val[0][i] = i*x;
        }
        for (int i=1; i <= counter1; i++){
            for (int j=1; j <= counter2; j++){
                val[i][j] = Integer.MAX_VALUE;
                val[i][j] = Math.min(val[i][j], val[i][j-1] + x);
                val[i][j] = Math.min(val[i][j], val[i-1][j] + y);
                val[i][j] = Math.min(val[i][j], val[i-1][j-1] + z*Math.abs(start[i]-end[j]));
            }
        }
        System.out.println(val[counter1][counter2]);
    }
}
