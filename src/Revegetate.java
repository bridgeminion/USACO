import java.io.*;
import java.util.*;

public class Revegetate {

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("revegetate.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Nodes[] same = new Nodes[n+1];
        Nodes[] dif = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            same[i] = new Nodes (new ArrayList<>());
            dif[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String s = st1.nextToken();
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            if (s.equals("S")){
                same[a].list.add(b);
                same[b].list.add(a);
            }
            else {
                dif[a].list.add(b);
                dif[b].list.add(a);
            }
        }
        int[] index = new int[n+1];
        boolean[] visited = new boolean[n+1];
        int counter = 0;
        List<Set<Integer>> group = new ArrayList<>();
        for (int i=1; i <= n; i++){
            index[i] = -1;
        }
        for (int i=1; i <= n; i++){
            if (!visited[i]){
                group.add(new HashSet<>());
                visited[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()){
                    int temp = q.remove();
                    index[temp] = counter;
                    group.get(counter).add(temp);
                    for (int j : same[temp].list){
                        if (!visited[j]){
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                counter++;
            }
        }
        int[] color = new int[counter];
        StringBuilder ans = new StringBuilder();
        ans.append(1);
        for (int a=0; a < counter; a++){
            if (color[a] == 0){
                Queue<Integer> q = new LinkedList<>();
                q.add(a);
                color[a] = 1;
                ans.append(0);
                while (!q.isEmpty()){
                    int temp = q.remove();
                    for (int i : group.get(temp)){
                        for (int j : dif[i].list){
                            if (color[index[j]] == 0){
                                color[index[j]] = 3-color[temp];
                                q.add(index[j]);
                            }
                            else if (color[index[j]] != 3-color[temp]){
                                pw.println(0);
                                pw.close();
                                return;
                            }
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
