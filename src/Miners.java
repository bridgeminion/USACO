import java.io.*;

public class Miners {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] type = new int[n];
        for (int i=0; i < n; i++){
            if (s.charAt(i) == 'M'){
                type[i] = 1;
            }
            else if (s.charAt(i) == 'F'){
                type[i] = 2;
            }
            else {
                type[i] = 3;
            }
        }
//        int[][] dp = new int[n+1][256];
        int[] cur = new int[256];
        int[] next = new int[256];
//        for (int i=0; i <= n; i++){
//            for (int j=0; j < 256; j++){
//                dp[i][j] = -1;
//            }
//        }
        for (int j=0; j < 256; j++){
            cur[j] = -1;
            next[j] = -1;
        }
        cur[0] = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < 256; j++){
                if (cur[j] >= 0){
                    int[] temp = new int[4];
                    int code = j;
                    for (int k=0; k < 4; k++){
                        temp[k] = code%4;
                        code /= 4;
                    }
                    int m1 = temp[1] + type[i]*4 + temp[2]*16 + temp[3]*64;
                    boolean[] found = new boolean[4];
                    found[temp[0]] = true;
                    found[temp[1]] = true;
                    found[type[i]] = true;
                    int add = 0;
                    for (int k=1; k < 4; k++){
                        if (found[k]){
                            add++;
                        }
                        found[k] = false;
                    }
                    next[m1] = Math.max(next[m1], cur[j] + add);
                    int m2 = temp[0] + temp[1]*4 + temp[3]*16 + type[i]*64;
                    found[temp[2]] = true;
                    found[temp[3]] = true;
                    found[type[i]] = true;
                    add = 0;
                    for (int k=1; k < 4; k++){
                        if (found[k]){
                            add++;
                        }
                        found[k] = false;
                    }
                    next[m2] = Math.max(next[m2], cur[j] + add);
                }
            }
            for (int j=0; j < 256; j++){
                cur[j] = next[j];
                next[j] = -1;
            }
        }
//        for (int i=0; i < n; i++){
//            for (int j=0; j < 256; j++){
//                if (dp[i][j] >= 0){
//                    int[] temp = new int[4];
//                    int code = j;
//                    for (int k=0; k < 4; k++){
//                        temp[k] = code%4;
//                        code /= 4;
//                    }
//                    int m1 = temp[1] + type[i]*4 + temp[2]*16 + temp[3]*64;
//                    boolean[] found = new boolean[4];
//                    found[temp[0]] = true;
//                    found[temp[1]] = true;
//                    found[type[i]] = true;
//                    int add = 0;
//                    for (int k=1; k < 4; k++){
//                        if (found[k]){
//                            add++;
//                        }
//                        found[k] = false;
//                    }
//                    dp[i+1][m1] = Math.max(dp[i+1][m1], dp[i][j] + add);
//                    int m2 = temp[0] + temp[1]*4 + temp[3]*16 + type[i]*64;
//                    found[temp[2]] = true;
//                    found[temp[3]] = true;
//                    found[type[i]] = true;
//                    add = 0;
//                    for (int k=1; k < 4; k++){
//                        if (found[k]){
//                            add++;
//                        }
//                        found[k] = false;
//                    }
//                    dp[i+1][m2] = Math.max(dp[i+1][m2], dp[i][j] + add);
//                }
//            }
//        }
        int ans = 0;
        for (int j=0; j < 256; j++){
            ans = Math.max(ans, cur[j]);
        }
        System.out.println(ans);
    }
}
