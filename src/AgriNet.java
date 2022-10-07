import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int index;
    int key;
    public Node (int index, int key){
        this.index = index;
        this.key = key;
    }
    @Override
    public int compareTo (Node x) {
        return this.key-x.key;
    }
}

public class AgriNet {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][n];
        boolean notdone = false;
        int i1=0;
        int j1=0;
        while (i1 < n){
            String temp = br.readLine();
            StringTokenizer st = new StringTokenizer(temp);
            if (!notdone){
                j1 = 0;
            }
            while (st.hasMoreTokens()){
                cost[i1][j1] = Integer.parseInt(st.nextToken());
                j1++;
            }
            if (!(j1 < n)){
                i1++;
                notdone = false;
            }
            else {
                notdone = true;
            }
        }
//        for (int i=0; i < n; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int j=0; j < n; j++){
//                cost[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
        Set<Integer> visited = new HashSet<>();
//        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
//            @Override
//            public int compare(Node node, Node t1) {
//                return node.key - t1.key;
//            }
//        });
        List<Node> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            list.add(new Node(i, Integer.MAX_VALUE));
        }
        list.get(0).key = 0;
        int ans = 0;
        while (visited.size() < n){
            Collections.sort(list);
            int j=0;
            while (visited.contains(list.get(j).index)){
                j++;
            }
            int cur = list.get(j).index;
            ans += list.get(j).key;
            visited.add(cur);
            for (int i=0; i < n; i++){
                 list.get(i).key = Math.min(list.get(i).key, cost[cur][list.get(i).index]);
            }
        }
        System.out.println(ans);


//        visited[0] = true;
//        int ans = 0;
//        List<Integer> cur = new ArrayList<>();
//        cur.add(0);
//        for (int counter = 0; counter < n-1; counter++){
//            int min = 100001;
//            int index = 0;
//            for (int i : cur){
//                for (int j=0; j < n; j++){
//                    if (!visited[j]){
//                        if (min > cost[i][j]){
//                            min = cost[i][j];
//                            index = j;
//                        }
//                    }
//                }
//            }
//            visited[index] = true;
//            cur.add(index);
//            ans += min;
//        }
//        System.out.println(ans);
    }
}
