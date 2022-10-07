import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Reduce {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("reduce.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        int[] placex = new int[n];
        int [] placey = new int[n];
        int max_x = 0;
        int max_y = 0;
        int min_x = 2147483647;
        int min_y = 2147483647;
        int big_x = 0;
        int big_y = 0;
        int small_x = 0;
        int small_y = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st1.nextToken());
            y[i] = Integer.parseInt(st1.nextToken());
            placex[i] = x[i];
            placey[i] = y[i];
        }
        Arrays.sort(placex);
        Arrays.sort(placey);
        max_x = placex[n-1];
        max_y = placey[n-1];
        min_x = placex[0];
        min_y = placey[0];
        big_x = placex[n-2];
        big_y = placey[n-2];
        small_x = placex[1];
        small_y = placey[1];
//        pw.println(max_x);
//        pw.println(max_y);
//        pw.println(min_x);
//        pw.println(min_y);
//        pw.println(big_x);
//        pw.println(big_y);
//        pw.println(small_x);
//        pw.println(small_y);
        int ans = (max_x - min_x)*(max_y - min_y);
        for (int i=0; i < n; i++){
            int temp = ans;
            if (x[i] == max_x){
                if (y[i] == max_y){
                    int temp1 = (big_x - min_x)*(big_y - min_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else if (y[i] == min_y){
                    int temp1 = (big_x - min_x)*(max_y - small_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else{
                    int temp1 = (big_x - min_x)*(max_y - min_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
            }
            if (x[i] == min_x){
                if (y[i] == max_y){
                    int temp1 = (max_x - small_x)*(big_y - min_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else if (y[i] == min_y){
                    int temp1 = (max_x - small_x)*(max_y - small_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else{
                    int temp1 = (max_x - small_x)*(max_y - min_y);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
            }
            if (y[i] == max_y){
                if (x[i] == max_x){
                    int temp1 = (big_y - min_y)*(big_x - min_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else if (x[i] == min_x){
                    int temp1 = (big_y - min_y)*(max_x - small_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else{
                    int temp1 = (big_y - min_y)*(max_x - min_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
            }
            if (y[i] == min_y){
                if (x[i] == max_x){
                    int temp1 = (max_y - small_y)*(big_x - min_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else if (x[i] == min_x){
                    int temp1 = (max_y - small_y)*(max_x - small_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
                else{
                    int temp1 = (max_y - small_y)*(max_x - min_x);
                    if (temp1 < temp){
                        temp = temp1;
                    }
                }
            }
            if (temp < ans){
                ans = temp;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
