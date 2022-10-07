import java.io.*;
import java.util.*;

public class Mountains {

    static class Mount implements Comparable<Mount>{
        int dif;
        int sum;
        public Mount (int dif, int sum){
            this.dif = dif;
            this.sum = sum;
        }

        @Override
        public int compareTo(Mount mount) {
            if (this.dif == mount.dif){
                return mount.sum - this.sum;
            }
            return this.dif - mount.dif;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Mount> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            int dif = x-y;
            int sum = x+y;
            list.add(new Mount(dif, sum));
        }
        Collections.sort(list);
        int ans = 0;
        int max = -1;
        for (Mount i : list){
            if (i.sum > max){
                max = i.sum;
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
