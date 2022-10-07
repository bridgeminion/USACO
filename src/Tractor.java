import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tractor {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int startx = Integer.parseInt(st.nextToken());
        int starty = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[1002][1002];
        boolean[][] visited = new boolean[1002][1002];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            grid[Integer.parseInt(st1.nextToken())][Integer.parseInt(st1.nextToken())] = true;
        }
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();
        x.add(startx);
        y.add(starty);
        visited[startx][starty] = true;
        int counter = 0;
        int ans = n;
        while (!x.isEmpty()){
            int size = x.size();
            for (int a=0; a < size; a++){
                Queue<Integer> tempx = new LinkedList<>();
                Queue<Integer> tempy = new LinkedList<>();
                tempx.add(x.poll());
                tempy.add(y.poll());
                while (!tempx.isEmpty()){
                    int temp = tempx.size();
                    for (int i=0; i < temp; i++){
                        int curx = tempx.remove();
                        int cury = tempy.remove();
                        for (int j=0; j < 4; j++){
                            int newx = curx + dx[j];
                            int newy = cury + dy[j];
                            if (newx >= 0 && newx <= 1001 && newy >= 0 && newy <= 1001 && !grid[newx][newy] && !visited[newx][newy]){
                                if (newx == 0 && newy == 0){
                                    ans = Math.min(ans, counter);
                                }
                                visited[newx][newy] = true;
                                tempx.add(newx);
                                tempy.add(newy);
                            }
                            if (newx >= 0 && newx <= 1001 && newy >= 0 && newy <= 1001 && grid[newx][newy] && !visited[newx][newy]){
                                visited[newx][newy] = true;
                                x.add(newx);
                                y.add(newy);
                            }
                        }
                    }
                }
            }
            counter++;
        }
        pw.println(ans);
        pw.close();
    }
}
