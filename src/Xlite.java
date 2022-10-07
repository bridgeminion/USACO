import java.io.*;
import java.util.StringTokenizer;

public class Xlite {

    static class Ans {
        int num_lights;
        int num_uses;

        public Ans(int num_lights, int num_uses) {
            this.num_lights = num_lights;
            this.num_uses = num_uses;
        }
    }

    public static int convert (String s, int length){
        int ans = 0;
        int m = 1;
        for (int i=length-1; i >= 0; i--){
            ans += (s.charAt(i)-'0')*m;
            m *= 2;
        }
        return ans;
    }

    public static int numBits (int n){
        int ans = 0;
        for (int i=0; i < 8; i++){
            if ((n & (1 << i)) == 1){
                ans++;
            }
        }
        return ans;
    }

    public static int notToggle (int n, String lights, int pos, int max){
        int ans = n%max;
        if (lights.charAt(pos) == '1'){
            ans++;
        }
        return ans;
    }

    public static int Toggle (int n, String lights, int pos, int max, int pf){
        n = n^pf;
        int ans = n%max;
        if (lights.charAt(pos) == '1'){
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        String lights = br.readLine();
        lights += '0';
        String p = br.readLine();
        int pf = convert(p, t);
        int max = (int)Math.pow(2, t);
        Ans[][] dp = new Ans[n+1][max];
        for (int i=0; i <= n; i++){
            for (int j=0; j < max; j++){
                dp[i][j] = new Ans (Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
        int start = convert(lights.substring(0, t), t);
        System.out.println(start);
        dp[t-1][start] = new Ans (numBits(start), 0);
        dp[t-1][start^pf] = new Ans (numBits(start^pf), 1);
        for (int i=t-1; i < n; i++){
            for (int j=0; j < max; j++){
                if (dp[i][j].num_lights != Integer.MAX_VALUE){
                    System.out.println(true);
                    int nl = dp[i][j].num_lights;
                    if (lights.charAt(i-t+1) == '1'){
                        nl++;
                    }
                    if (nl < dp[i+1][notToggle(j, lights, i+1, max)].num_lights){
                        dp[i+1][notToggle(j, lights, i+1, max)] = new Ans (nl, dp[i][j].num_uses);
                    }
                    else if (nl == dp[i+1][notToggle(j, lights, i+1, max)].num_lights){
                        dp[i+1][notToggle(j, lights, i+1, max)].num_uses = Math.min(dp[i][j].num_uses, dp[i+1][notToggle(j, lights, i+1, max)].num_uses);
                    }
                    if (nl < dp[i+1][Toggle(j, lights, i+1, max, pf)].num_lights){
                        dp[i+1][Toggle(j, lights, i+1, max, pf)] = new Ans (nl, dp[i][j].num_uses+1);
                    }
                    else if (nl == dp[i+1][notToggle(j, lights, i+1, max)].num_lights){
                        dp[i+1][Toggle(j, lights, i+1, max, pf)].num_uses = Math.min(dp[i][j].num_uses+1, dp[i+1][Toggle(j, lights, i+1, max, pf)].num_uses);
                    }
                }
            }
        }
        Ans ans = new Ans (Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int i=0; i < max; i++){
            if (dp[n][i].num_lights < ans.num_lights){
                ans = dp[n][i];
            }
            else if (dp[n][i].num_lights == ans.num_lights){
                ans.num_uses = Math.min(ans.num_uses, dp[n][i].num_uses);
            }
        }
        System.out.println(ans.num_uses);
    }
}
