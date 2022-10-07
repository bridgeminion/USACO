import java.io.*;
import java.util.StringTokenizer;

public class Mowing {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mowing.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mowing.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] lastcut = new int[2001][2001];
        int[][] time_elapsed = new int[2001][2001];
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int cur_x = 1000;
        int cur_y = 1000;
        int time = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            int steps = Integer.parseInt(st1.nextToken());
            if (temp.equals("N")){
                for (int j=0; j < steps; j++){
                    time++;
                    cur_y++;
                    if (lastcut[cur_x][cur_y] != 0){
                        if (time - lastcut[cur_x][cur_y] > time_elapsed[cur_x][cur_y]){
                            time_elapsed[cur_x][cur_y] = time - lastcut[cur_x][cur_y];
                        }
                    }
                    lastcut[cur_x][cur_y] = time;
                }
            }
            if (temp.equals("S")){
                for (int j=0; j < steps; j++){
                    time++;
                    cur_y--;
                    if (lastcut[cur_x][cur_y] != 0){
                        if (time - lastcut[cur_x][cur_y] > time_elapsed[cur_x][cur_y]){
                            time_elapsed[cur_x][cur_y] = time - lastcut[cur_x][cur_y];
                        }
                    }
                    lastcut[cur_x][cur_y] = time;
                }
            }
            if (temp.equals("W")){
                for (int j=0; j < steps; j++){
                    time++;
                    cur_x--;
                    if (lastcut[cur_x][cur_y] != 0){
                        if (time - lastcut[cur_x][cur_y] > time_elapsed[cur_x][cur_y]){
                            time_elapsed[cur_x][cur_y] = time - lastcut[cur_x][cur_y];
                        }
                    }
                    lastcut[cur_x][cur_y] = time;
                }
            }
            if (temp.equals("E")){
                for (int j=0; j < steps; j++){
                    time++;
                    cur_x++;
                    if (lastcut[cur_x][cur_y] != 0){
                        if (time - lastcut[cur_x][cur_y] > time_elapsed[cur_x][cur_y]){
                            time_elapsed[cur_x][cur_y] = time - lastcut[cur_x][cur_y];
                        }
                    }
                    lastcut[cur_x][cur_y] = time;
                }
            }
        }
        int ans = 2147483647;
        for (int i=0; i < 2001; i++){
            for (int j=0; j < 2001; j++){
                if (time_elapsed[i][j] != 0 && time_elapsed[i][j] < ans){
                    ans = time_elapsed[i][j];
                }
            }
        }
        if (ans == 2147483647){
            ans = -1;
        }
        pw.println(ans);
        pw.close();
    }
}
