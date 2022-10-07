import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Bcs {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcs.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("bcs.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n, k;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        List<char[][]> input = new ArrayList<>();
        char[][] big = new char[3*n][3*n];
        char[][] target = new char[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String s = st1.nextToken();
            for (int j=0; j < n; j++){
                target[i][j] = s.charAt(j);
            }
        }
        for (int i=0; i < k; i++){
            char[][] temp = new char[n][n];
            for (int j=0; j < n; j++){
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                String s = st1.nextToken();
                for (int a=0; a < n; a++){
                    temp[j][a] = s.charAt(a);
                }
            }
            input.add(temp);
        }
        for (int i=0; i < input.size(); i++){
            for (int j=0; j < input.size(); j++){
                char[][] one = input.get(i);
                char[][] two = input.get(j);

                if (i != j){

//                    System.out.println("i=" + i + ", j=" + j);
//                    System.out.println("one");
//                    for (int index_x = 0; index_x < n; index_x++) {
//                        for (int index_y = 0; index_y < n; index_y++) {
//                            System.out.print(one[index_x][index_y]);
//                        }
//                        System.out.println();
//                    }
//
//                    System.out.println("two");
//                    for (int index_x = 0; index_x < n; index_x++) {
//                        for (int index_y = 0; index_y < n; index_y++) {
//                            System.out.print(two[index_x][index_y]);
//                        }
//                        System.out.println();
//                    }

                    for (int a = n; a < 2*n; a++){
                        for (int b = n; b < 2*n; b++){
                            big[a][b] = one[a-n][b-n];
                        }
                    }

//                    System.out.println("big");
//                    for (int index_x = 0; index_x < 3*n; index_x++) {
//                        for (int index_y = 0; index_y < 3*n; index_y++) {
//                            System.out.print(big[index_x][index_y]);
//                        }
//                        System.out.println();
//                    }
                    for (int x_start = 1; x_start < 2*n - 1; x_start++){
                        for (int y_start = 1; y_start < 2*n - 1; y_start++){
                            boolean flag1 = false;
                            for (int c = x_start; c < x_start + n && !flag1; c++){
                                for (int d = y_start; d < y_start + n && !flag1; d++){
                                    if (!(big[c][d] == '#' && two[c-x_start][d-y_start] == '#')){
                                        if (two[c-x_start][d-y_start] == '#'){
                                            big[c][d] = two[c-x_start][d-y_start];
                                        }
                                        else {
                                            if (big[c][d] != '#'){
                                                big[c][d] = '.';
                                            }
                                        }
                                    }
                                    else{
                                        flag1 = true;
                                    }
                                }
                            }


//                            System.out.println("x_start=" + x_start + ", y_start=" + y_start);
//                            System.out.println("big");
//                            for (int index_x = 0; index_x < 3*n; index_x++) {
//                                for (int index_y = 0; index_y < 3*n; index_y++) {
//                                    System.out.print(big[index_x][index_y]);
//                                }
//                                System.out.println();
//                            }

                            for (int check_x = 0; check_x < 2*n; check_x++){
                                for (int check_y = 0; check_y < 2*n; check_y++){
                                    boolean flag = false;
                                    for (int a = check_x; a < n+check_x && !flag; a++){
                                        for (int b = check_y; b < n+check_y && !flag; b++){
                                            if (big[a][b] != target[a-check_x][b-check_y]){
                                                flag = true;
                                            }
                                        }
                                    }
                                    if (!flag){
//                                        pw.println("success!");
//                                        System.out.println("success!");
                                        pw.print(Math.min(i, j) + 1);
                                        pw.print(" ");
                                        pw.print(Math.max(i, j) + 1);
                                        pw.println();
                                        pw.close();
                                        return;
                                    }
                                }
                            }
                            for (int e = 0; e < 3*n; e++){
                                for (int f = 0; f < 3*n; f++){
                                    big[e][f] = '.';
                                }
                            }
                            for (int a = n; a < 2*n; a++){
                                for (int b = n; b < 2*n; b++){
                                    big[a][b] = one[a-n][b-n];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
