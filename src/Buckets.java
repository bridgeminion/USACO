import java.io.*;
import java.util.*;

public class Buckets {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buckets.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("buckets.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        char[][] input = new char[10][10];
        int barn_x, barn_y, lake_x, lake_y, rock_x, rock_y;
        barn_x = 0;
        barn_y = 0;
        lake_x = 0;
        lake_y = 0;
        rock_x = 0;
        rock_y = 0;
        for (int i=0; i < 10; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < 10; j++){
                input[i][j] = temp.charAt(j);
                if (input[i][j] == 'B'){
                    barn_x = i;
                    barn_y = j;
                }
                if (input[i][j] == 'L'){
                    lake_x = i;
                    lake_y = j;
                }
                if (input[i][j] == 'R'){
                    rock_x = i;
                    rock_y = j;
                }
            }
        }
        int ans = Math.abs(barn_x-lake_x) + Math.abs(barn_y - lake_y) - 1;
        if (lake_x == barn_x && barn_x == rock_x){
            if (rock_y >= barn_y && rock_y <= lake_y){
                ans += 2;
                pw.println(ans);
                pw.close();
                return;
            }
            if (rock_y >= lake_y && rock_y <= barn_y){
                ans += 2;
                pw.println(ans);
                pw.close();
                return;
            }
        }
        if (lake_y == barn_y && barn_y == rock_y){
            if (rock_x >= barn_x && rock_x <= lake_x){
                ans += 2;
                pw.println(ans);
                pw.close();
                return;
            }
            if (rock_x >= lake_x && rock_x <= barn_x){
                ans += 2;
                pw.println(ans);
                pw.close();
                return;
            }
        }
        pw.println(ans);
        pw.close();
    }

}
