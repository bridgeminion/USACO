import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Lineup {

    public static int convert(String name){
        if (name.equals("Beatrice")){
            return 0;
        }
        if (name.equals("Belinda")){
            return 1;
        }
        if (name.equals("Bella")){
            return 2;
        }
        if (name.equals("Bessie")){
            return 3;
        }
        if (name.equals("Betsy")){
            return 4;
        }
        if (name.equals("Blue")){
            return 5;
        }
        if (name.equals("Buttercup")){
            return 6;
        }
        if (name.equals("Sue")){
            return 7;
        }
        return 0;
    }

    public static String convertBack(int n){
        if (n == 0){
            return "Beatrice";
        }
        if (n == 1){
            return "Belinda";
        }
        if (n == 2){
            return "Bella";
        }
        if (n == 3){
            return "Bessie";
        }
        if (n == 4){
            return "Betsy";
        }
        if (n == 5){
            return "Blue";
        }
        if (n == 6) {
            return "Buttercup";
        }
        if (n == 7){
            return "Sue";
        }
        return "Sue";
    }
    public static boolean check(int[] order, int[][] constraint, int n){
        for (int i=0; i < n; i++){
            int temp1 = constraint[i][0];
            int temp2 = constraint[i][1];
            for (int j=0; j < 8; j++){
                if (order[j] == temp1){
                    if (j == 0){
                        if (order[1] != temp2){
                            return false;
                        }
                    }
                    else if (j == 7){
                        if (order[6] != temp2){
                            return false;
                        }
                    }
                    else{
                        if (order[j-1] != temp2 && order[j+1] != temp2){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("lineup.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] constraint = new int[n][2];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String name1 = st1.nextToken();
            int temp1 = convert(name1);
            st1.nextToken();
            st1.nextToken();
            st1.nextToken();
            st1.nextToken();
            String name2 = st1.nextToken();
            int temp2 = convert(name2);
            constraint[i][0] = temp1;
            constraint[i][1] = temp2;
        }
        int[] order = new int[8];
        int[] ans = new int[8];
        for (int i=0; i < 8; i++){
            ans[i] = 7;
        }
        int counter = 0;
        int counter1 = 0;
        for (int a=0; a < 8; a++){
            for (int b=0; b < 8; b++){
                if (b != a) {
                    for (int c = 0; c < 8; c++) {
                        if (c != b && c != a){
                            for (int d = 0; d < 8; d++) {
                                if (d != c && d != b && d != a){
                                    for (int e = 0; e < 8; e++) {
                                        if (e != d && e != c && e != b && e != a){
                                            for (int f = 0; f < 8; f++) {
                                                if (f != e && f != d && f != c && f != b && f != a){
                                                    for (int g = 0; g < 8; g++) {
                                                        if (g != f && g != e && g != d && g != c && g != b && g != a){
                                                            for (int h = 0; h < 8; h++) {
                                                                if (h != g && h != f && h != e && h != d && h != c && h != b && h != a){
                                                                    counter++;
                                                                    order[0] = a;
                                                                    order[1] = b;
                                                                    order[2] = c;
                                                                    order[3] = d;
                                                                    order[4] = e;
                                                                    order[5] = f;
                                                                    order[6] = g;
                                                                    order[7] = h;
                                                                    if (check(order, constraint, n)) {
                                                                        counter1++;
                                                                        int i=0;
                                                                        while (order[i] == ans[i] && i < 7){
                                                                            i++;
                                                                        }
                                                                        if (order[i] < ans[i]){
                                                                            for (int j=0; j < 8; j++){
                                                                                ans[j] = order[j];
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i=0; i < 8; i++){
            pw.println(convertBack(ans[i]));
        }
        pw.close();
    }
}
