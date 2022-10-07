//import java.io.*;
//import java.util.*;
//
//class Slide{
//    int row;
//    int col;
//    public Slide(int row, int col){
//        this.row = row;
//        this.col = col;
//    }
//}
//
//public class CornMaze {
//
//    static int[] dr = {-1, 1, 0, 0};
//    static int[] dc = {0, 0, 1, -1};
//
//    public static void main(String[] args) throws IOException {
//
////        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
////        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int rows = Integer.parseInt(st.nextToken());
//        int cols = Integer.parseInt(st.nextToken());
//        char[][] grid = new char[rows][cols];
//        boolean[][] visited = new boolean[rows][cols];
//        boolean[][] safe = new boolean[rows][cols];
//        int startrow = 0;
//        int startcol = 0;
//        int endrow = 0;
//        int endcol = 0;
//        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
////        Map<Slide, Slide> map = new HashMap<>();
//        List<List<slideDP>> list = new ArrayList<>(52);
//        for (int i=0; i < 52; i++){
//            List<slideDP> emptyList = new ArrayList<>();
//            list.add(i, emptyList);
//        }
//        for (int i=0; i < rows; i++){
//            StringTokenizer st1 = new StringTokenizer(br.readLine());
//            String temp = st1.nextToken();
//            for (int j=0; j < cols; j++){
//                grid[i][j] = temp.charAt(j);
//                if (grid[i][j] != '#'){
//                    safe[i][j] = true;
//                }
//                if (grid[i][j] == '='){
//                    endrow = i;
//                    endcol = j;
//                }
//                if (grid[i][j] == '@'){
//                    startrow = i;
//                    startcol = j;
//                }
//                if (letters.indexOf(grid[i][j]) > -1){
//                    int index = grid[i][j] - 'A';
//                    list.get(index).add(new Slide(i, j));
//                }
//            }
//        }
//        Queue<Integer> row = new LinkedList<>();
//        Queue<Integer> col = new LinkedList<>();
//        Queue<Integer> time = new LinkedList<>();
//        row.add(startrow);
//        col.add(startcol);
//        time.add(0);
//        visited[startrow][startcol] = true;
//        while (!row.isEmpty()){
////            System.out.println(row);
////            System.out.println(col);
////            System.out.println(time);
//            int temp = row.size();
//            for (int i=0; i < temp; i++){
//                int crow = row.remove();
//                int ccol = col.remove();
//                int t = time.remove();
//                if (crow == endrow && ccol == endcol){
//                    System.out.println(t);
//                    return;
//                }
//                for (int j=0; j < 4; j++){
//                    int r = crow + dr[j];
//                    int c = ccol + dc[j];
//                    if (r >= 0 && r < rows && c >= 0 && c < cols && !visited[r][c] && safe[r][c]){
//                        visited[r][c] = true;
//                        int index = grid[r][c] - 'A';
//                        if (index >= 0 && index < 26){
//                            if ((list.get(index).get(0).row == r && list.get(index).get(0).col == c) && list.get(index).size() == 2){
//                                row.add(list.get(index).get(1).row);
//                                col.add(list.get(index).get(1).col);
//                                time.add(t+1);
//                            }
//                            else{
//                                row.add(list.get(index).get(0).row);
//                                col.add(list.get(index).get(0).col);
//                                time.add(t+1);
//                            }
//                        }
//                        else{
//                            row.add(r);
//                            col.add(c);
//                            time.add(t+1);
//                        }
//                    }
//                }
//            }
//        }
//        pw.close();
//    }
//}
