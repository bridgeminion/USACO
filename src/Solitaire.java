import java.io.*;
import java.util.StringTokenizer;

public class Solitaire {

    public static int convert (char temp){
        if (temp == 'A'){
            return 1;
        }
        if (temp == '2'){
            return 2;
        }
        if (temp == '3'){
            return 3;
        }
        if (temp == '4'){
            return 4;
        }
        if (temp == '5'){
            return 5;
        }
        if (temp == '6'){
            return 6;
        }
        if (temp == '7'){
            return 7;
        }
        if (temp == '8'){
            return 8;
        }
        if (temp == '9'){
            return 9;
        }
        if (temp == 'T'){
            return 10;
        }
        if (temp == 'J'){
            return 11;
        }
        if (temp == 'Q') {
            return 12;
        }
        return 13;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] num = new int[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                String temp = st1.nextToken();
                num[i][j] = convert(temp.charAt(0));
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                pw.print(num[i][j]);
                pw.print(" ");
            }
            pw.println();
        }
        for (int i=n-1; i >= 0; i--){
            for (int j=0; j < n; j++){
                int row = i+1;
                int col = j-1;
                int temp1 = 0;
                int temp2 = 0;
                if (row >= 0 && row < n){
                    temp1 = num[row][j];
                }
                if (col >= 0 && col < n){
                    temp2 = num[i][col];
                }
                num[i][j] += Math.max(temp1, temp2);
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                pw.print(num[i][j]);
                pw.print(" ");
            }
            pw.println();
        }
        pw.println(num[0][n-1]);
        pw.close();
    }
}
