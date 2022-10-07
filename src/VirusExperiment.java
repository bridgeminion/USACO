import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class VirusExperiment {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // 0 = N, 1 = E, 2 = S, 3 = W

    // can prune by exiting early

    static int ans = Integer.MAX_VALUE;
    static int num = 0;

    public static int test (int row, int col, int[][] u, String d, int m, int r, int c, int[] len){
        Queue<Integer> rq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        boolean[][] inf = new boolean[r][c];
        rq.add(row);
        cq.add(col);
        inf[row][col] = true;
        int res = 0;
        while (!rq.isEmpty()){
            int size = rq.size();
            for (int i=0; i < size; i++){
                int cr = rq.remove();
                int cc = cq.remove();
                res++;
                if (res > ans){
                    return res;
                }
                for (int j=0; j < 4; j++){
                    int nr = cr + dr[j];
                    int nc = cc + dc[j];
                    if (nr >= 0 && nr < r && nc >= 0 && nc < c && !inf[nr][nc] && u[nr][nc] > 0){
                        int code = 0;
                        for (int k=0; k < 4; k++) {
                            int r1 = nr + dr[k];
                            int c1 = nc + dc[k];
                            if (r1 >= 0 && r1 < r && c1 >= 0 && c1 < c && inf[r1][c1]){
                                code += (1 << k);
                            }
                        }
                        if (len[code] >= u[nr][nc]){
                            inf[nr][nc] = true;
                            rq.add(nr);
                            cq.add(nc);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        String d = br.readLine();
        int[] days = new int[m];
        for (int i=0; i < m; i++){
            if (d.charAt(i) == 'E'){
                days[i] = 1;
            }
            else if (d.charAt(i) == 'S'){
                days[i] = 2;
            }
            else if (d.charAt(i) == 'W'){
                days[i] = 3;
            }
        }
        int[][] u = new int[r][c];
        for (int i=0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                u[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] len = new int[16];
        for (int i=0; i < 15; i++){
            boolean[] valid = new boolean[4];
            for (int j=0; j < 4; j++){
                if ((i & (1 << j)) > 0){
                    valid[j] = true;
                }
            }
            int cur = 0;
            for (int k=0; k < m; k++){
                if (valid[days[k]]){
                    cur++;
                }
                else {
                    cur = 0;
                }
                len[i] = Math.max(len[i], cur);
            }
            for (int k=0; k < m; k++){
                if (valid[days[k]]){
                    cur++;
                }
                else {
                    cur = 0;
                }
                len[i] = Math.max(len[i], cur);
            }
            if (len[i] == m*2){
                len[i] = 100001;
            }
        }
        int[][] arr = new int[r][c];
        for (int i=0; i < r; i++){
            for (int j=0; j < c; j++){
                if (u[i][j] > 0){
                    int temp = test (i, j, u, d, m, r, c, len);
                    arr[i][j] = temp;
                    if (temp < ans){
                        ans = temp;
                        num = 1;
                    }
                    else if (temp == ans){
                        num++;
                    }
                }
            }
        }
        System.out.println(ans);
        System.out.println(num);
    }
}
