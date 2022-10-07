import java.io.*;
import java.util.*;

public class FairPhotoGold {

    static class Cow {
        int pos;
        boolean spotted;

        public Cow(int pos, boolean spotted) {
            this.pos = pos;
            this.spotted = spotted;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Cow> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            boolean spotted = st.nextToken().equals("S");
            list.add(new Cow (pos, spotted));
        }
        Collections.sort(list, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.pos - o2.pos;
            }
        });
        int[] prefix = new int[n+1];
        for (int i=1; i <= n; i++){
            prefix[i] = prefix[i-1];
            if (list.get(i-1).spotted){
                prefix[i]++;
            }
            else {
                prefix[i]--;
            }
        }
        Map<Integer, Integer> first = new HashMap<>();
        for (int i=0; i <= n; i++){
            if (!first.containsKey(prefix[i])){
                first.put(prefix[i], i);
            }
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            if (prefix[i] >= 0){
                if (first.get(prefix[i]) < n){
                    ans = Math.max(ans, list.get(i-1).pos - list.get(first.get(prefix[i])).pos);
                }
            }
            else if (prefix[i] < 0 && prefix[i] % 2 == 0){
                ans = Math.max(ans, list.get(i-1).pos - list.get(0).pos);
            }
            else {
                if (first.containsKey(-1)){
                    if (first.get(-1) < n){
                        ans = Math.max(ans, list.get(i-1).pos - list.get(first.get(-1)).pos);
                    }
                }
                if (first.containsKey(1)){
                    if (first.get(1) < n){
                        ans = Math.max(ans, list.get(i-1).pos - list.get(first.get(1)).pos);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
