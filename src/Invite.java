import java.io.*;
import java.util.*;

class Group{
    Set<Integer> set;
    int index;
    public Group(Set<Integer> set, int index){
        this.set = set;
        this.index = index;
    }
}

public class Invite {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cows = Integer.parseInt(st.nextToken());
        int num_groups = Integer.parseInt(st.nextToken());
        Map<Integer, List<Group>> groups = new HashMap<>();
        for (int i=0; i < num_groups; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st1.nextToken());
            Set<Integer> cur = new HashSet<>(size);
            for (int j=0; j < size; j++){
                int temp = Integer.parseInt(st1.nextToken());
                cur.add(temp);
            }
            Group bb = new Group(cur, i);
            for (int j : cur){
                groups.putIfAbsent(j, new ArrayList<>());
                groups.get(j).add(bb);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] added = new boolean[num_cows+1];
        q.add(1);
        int counter = 0;
        while (!q.isEmpty()){
            int temp = q.remove();
            if (!added[temp]){
                added[temp] = true;
                counter++;
                for (int i=0; i < groups.get(temp).size(); i++) {
                    Group aa = groups.get(temp).get(i);
                    aa.set.remove(temp);
                    if (aa.set.size() == 1) {
                        int notAdded = aa.set.iterator().next();
                        aa.set.remove(notAdded);
                        q.add(notAdded);
                    }
                }
            }
        }
        System.out.println(counter);
    }

}
