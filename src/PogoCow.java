import java.io.*;
import java.util.*;

public class PogoCow {

    static class Target {
        int pos;
        int val;

        public Target(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pogocow.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("pogocow.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Target> list = new ArrayList<>(n);
        List<Integer> positions = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            positions.add(pos);
            list.add(new Target (pos, val));
        }
        Collections.sort(list, new Comparator<Target>() {
            @Override
            public int compare(Target o1, Target o2) {
                return o1.pos - o2.pos;
            }
        });
        positions.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int[][] dp1 = new int[n][n];
        for (int i=0; i < n; i++){
            dp1[i][i] = list.get(i).val;
        }
        for (int i=0; i < n; i++){
            for (int j=0; j <= i; j++){
                if (dp1[i][j] != 0){
                    int temp = list.get(i).pos - list.get(j).pos;
                    int index = Collections.binarySearch(positions, list.get(i).pos + temp);
                    if (index < 0){
                        index = (index+1)*-1;
                    }
                    for (int k=Math.max(i+1, index); k < n; k++){
                        if (list.get(k).pos - list.get(i).pos >= temp){
                            dp1[k][i] = Math.max(dp1[k][i], dp1[i][j] + list.get(k).val);
                        }
                    }
                }
            }
        }
        int[][] dp2 = new int[n][n];
        for (int i=0; i < n; i++){
            dp2[i][i] = list.get(i).val;
        }
        for (int i=n-1; i >= 0; i--){
            for (int j=n-1; j >= i; j--){
                if (dp2[i][j] != 0){
                    int temp = list.get(j).pos - list.get(i).pos;
                    int index = Collections.binarySearch(positions, list.get(i).pos - temp);
                    if (index < 0){
                        index = (index+1)*-1;
                        index--;
                    }
                    for (int k=Math.min(i-1, index); k >= 0; k--){
                        if (list.get(i).pos - list.get(k).pos >= temp){
                            dp2[k][i] = Math.max(dp2[k][i], dp2[i][j] + list.get(k).val);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                ans = Math.max(ans, dp1[i][j]);
                ans = Math.max(ans, dp2[i][j]);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
