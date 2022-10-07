import java.io.*;
import java.util.*;

public class BadCereal {

    public static class Fav{
        int first;
        int sec;
        public Fav (int first, int sec){
            this.first = first;
            this.sec = sec;
        }
    }
    public static class Cow{
        int cereal;
        int index;
        public Cow (int cereal, int index){
            this.cereal = cereal;
            this.index = index;
        }
    }
//    int counter = 0;
//        for (int i=0; i < n; i++){
//            if (!used.contains(first[i])){
//                used.add(first[i]);
//                counter++;
//            }
//            else {
//                if (!used.contains(sec[i])){
//                    used.add(sec[i]);
//                    counter++;
//                }
//            }
//        }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cereal.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> first = new HashMap<>();
        Map<Integer, List<Integer>> sec = new HashMap<>();
        Fav[] cereal = new Fav[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st1.nextToken());
            int temp2 = Integer.parseInt(st1.nextToken());
            first.putIfAbsent(temp1, new ArrayList<>());
            sec.putIfAbsent(temp2, new ArrayList<>());
            first.get(temp1).add(i);
            sec.get(temp2).add(i);
            cereal[i] = new Fav(temp1, temp2);
        }
//        int[] index = new int[m];
//        boolean[] used = new boolean[m];
//        Set<Integer> cow = new HashSet<>();
//        for (int i=0; i < n; i++){
//            if (!used[cereal[i].first]){
//                used[cereal[i].first] = true;
//                index[cereal[i].first] = i;
//                cow.add(i);
//            }
//            else {
//                if (!used[cereal[i].sec]){
//                    used[cereal[i].sec] = true;
//                    index[cereal[i].sec] = i;
//                    cow.add(i);
//                }
//            }
//        }
        int[] cur = new int[n];
        Set<Integer> used = new HashSet<>();
        Set<Integer> used2 = new HashSet<>();
        for (int i : first.keySet()){
            int index = first.get(i).get(0);
            used.add(index);
            used2.add(i);
            cur[index] = i;
            sec.get(cereal[index].sec).remove((Integer)index);
        }
        for (int i : sec.keySet()){
            for (int j : sec.get(i)){
                if (!used.contains(j) && !used2.contains(cereal[j].sec)){
                    used.add(j);
                    used2.add(cereal[j].sec);
                    cur[j] = i;
                    break;
                }
            }
        }
        pw.println(used.size());
        for (int i=0; i < n-1; i++){
            used.remove(i);
            if (first.containsKey(cur[i])){
                first.get(cur[i]).remove(0);
                if (!first.get(cur[i]).isEmpty()){
                    int temp = first.get(cur[i]).get(0);
                    if (!used.contains(temp)){
                        used.add(temp);
                        cur[temp] = cur[i];
                    }
                    else {
                        sec.get(cur[temp]).remove(0);
                        if (!sec.get(cur[temp]).isEmpty()){
                            int temp2 = sec.get(cur[temp]).get(0);
                            if (!used.contains(temp2)){
                                used.add(temp2);
                                cur[temp2] = cur[temp];
                            }
                        }
                        cur[temp] = cur[i];
                    }
                }
                else {
                    if (sec.containsKey(cur[i]) && !sec.get(cur[i]).isEmpty()){
                        int temp = sec.get(cur[i]).get(0);
                        if (!used.contains(temp)){
                            used.add(temp);
                            cur[temp] = cur[i];
                        }
                    }
                }
            }
            pw.println(used.size());
        }
        used.size();
        pw.close();
    }
}
