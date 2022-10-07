import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCSki {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static boolean works (int startr, int startc, int[][] h, boolean[][] wp, int d, int m, int n, int num){
        boolean[][] visited = new boolean[m][n];
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        row.add(startr);
        col.add(startc);
        visited[startr][startc] = true;
        int counter = 0;
        while (!row.isEmpty()){
            int curr = row.remove();
            int curc = col.remove();
            if (wp[curr][curc]){
                counter++;
            }
            for (int j=0; j < 4; j++){
                int newr = curr+dr[j];
                int newc = curc+dc[j];
                if (newr >= 0 && newr < m && newc >= 0 && newc < n && !visited[newr][newc] && Math.abs(h[curr][curc]-h[newr][newc]) <= d){
                    visited[newr][newc] = true;
                    row.add(newr);
                    col.add(newc);
                }
            }
        }
        return counter == num;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int[][] h = new int[rows][cols];
        boolean[][] wp = new boolean[rows][cols];
        for (int i=0; i < rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < cols; j++){
                h[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        int startr = 0;
        int startc = 0;
        int num = 0;
        for (int i=0; i < rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < cols; j++){
                wp[i][j] = st1.nextToken().equals("1");
                if (wp[i][j]){
                    startr = i;
                    startc = j;
                    num++;
                }
            }
        }
        int low = 0;
        int high = 1000000000;
        while (low <= high) {
            int mid = (high+low)/2;
            if (works(startr, startc, h, wp, mid, rows, cols, num)){
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
        System.out.println(low);
    }
}