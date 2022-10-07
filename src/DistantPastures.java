import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DistantPastures {

    static class Node {
        int row;
        int col;
        int key;
        public Node (int row, int col, int key){
            this.row = row;
            this.col = col;
            this.key = key;
        }
    }

    static int ans = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void dijkstra (int r, int c, boolean[][] grid, int n, int a, int b){
        int[][] key = new int[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                key[i][j] = Integer.MAX_VALUE;
            }
        }
        key[r][c] = 0;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.key == o2.key){
                    if (o1.row == o2.row){
                        return o1.col - o2.col;
                    }
                    return o1.row - o2.row;
                }
                return o1.key - o2.key;
            }
        });
        pq.add(new Node (r, c, 0));
        for (int i=0; i < n*n; i++){
            Node cur = pq.poll();
            while (visited[cur.row][cur.col]){
                cur = pq.poll();
            }
            ans = Math.max(ans, cur.key);
            visited[cur.row][cur.col] = true;
            for (int j=0; j < 4; j++){
                int newr = cur.row + dr[j];
                int newc = cur.col + dc[j];
                if (newr >= 0 && newr < n && newc >= 0 && newc < n){
                    int cost;
                    if (grid[cur.row][cur.col] == grid[newr][newc]){
                        cost = cur.key + a;
                    }
                    else {
                        cost = cur.key + b;
                    }
                    if (cost < key[newr][newc] && !visited[newr][newc]){
                        pq.add(new Node(newr, newc, cost));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[n][n];
        for (int i=0; i < n; i++){
            String temp = br.readLine();
            for (int j=0; j < n; j++){
                grid[i][j] = temp.charAt(j) == ')';
            }
        }
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                dijkstra(i, j, grid, n, a, b);
            }
        }
        System.out.println(ans);
    }
}
