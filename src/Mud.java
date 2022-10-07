import java.io.*;
import java.util.*;

public class Mud {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int endx = Integer.parseInt(st.nextToken()) + 500;
        int endy = Integer.parseInt(st.nextToken()) + 500;
        int n = Integer.parseInt(st.nextToken());
        boolean grid[][] = new boolean[1002][1002];
        boolean visited[][] = new boolean[1002][1002];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            grid[x+500][y+500] = true;
        }
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();
        x.add(500);
        y.add(500);
        visited[500][500] = true;
        int counter = 0;
        while (!x.isEmpty()){
            int temp = x.size();
            for (int i=0; i < temp; i++){
                int curx = x.remove();
                int cury = y.remove();
                if (curx == endx && cury == endy){
                    System.out.println(counter);
                    return;
                }
                for (int j=0; j < 4; j++){
                    int nx = curx + dx[j];
                    int ny = cury + dy[j];
                    if (nx >= 0 && nx < 1002 && ny >= 0 && ny < 1002 && !grid[nx][ny] && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        x.add(nx);
                        y.add(ny);
                    }
                }
            }
            counter++;
        }
    }
}
