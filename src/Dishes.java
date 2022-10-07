import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Dishes {

//    static int ans = 0;
//    static boolean done = false;
//    static boolean works = false;
//
//    public static void binarySearch (int low, int high, int[] plates){
//        int mid = (low + high)/2;
//        while (low <= high){
//            if (check(mid, plates)){
//                ans = Math.max(ans, mid);
//                low = mid+1;
//            }
//            else {
//                ans = Math.max(ans, mid);
//                high = mid-1;
//            }
//        }
//    }
//
//    public static boolean check (int n, int[] plates){
//        int[] p = new int[n];
//        int[] sorted = new int[n];
//        for (int i=0; i < n; i++){
//            p[i] = plates[i];
//            sorted[i] = plates[i];
//        }
//        Arrays.sort(sorted);
//        done = false;
//        works = false;
//        dfs (p, sorted, 0, 0, new ArrayList<>());
//        return works;
//    }
//
//    public static void dfs (int[] p, int[] sorted, int dirty_i, int done_i, List<Stack<Integer>> soap){
//        if (done){
//            return;
//        }
//        if (done_i == p.length - 1){
//            works = true;
//            done = true;
//            return;
//        }
//        if (dirty_i < p.length){
//            for (Stack<Integer> i : soap){
//                i.add(p[dirty_i]);
//                dfs (p, sorted, dirty_i+1, done_i, soap);
//                i.pop();
//            }
//            Stack<Integer> temp = new Stack<>();
//            temp.add(p[dirty_i]);
//            soap.add(temp);
//            dfs (p, sorted, dirty_i+1, done_i, soap);
//        }
//        if (soap.size() == 0){
//            return;
//        }
//        if (soap.get(0).peek() == sorted[done_i]){
//            soap.get(0).pop();
//            dfs (p, sorted, dirty_i, done_i+1, soap);
//        }
//    }

    public static int sim (int[] plates){
//        Deque<Integer> soap = new LinkedList<>();
//        int last = 0;
//        int ans = 0;
//        for (int i=0; i < plates.length; i++){
//            if (plates[i] < last){
//                return ans;
//            }
//            ans++;
//            if (soap.size() == 0){
//                soap.add(plates[i]);
//            }
//            else {
//                boolean put = false;
//                while (!soap.isEmpty()){
//                    if (plates[i] > soap.peekFirst()){
//                        last = soap.pop();
//                    }
//                    else {
//                        soap.push(plates[i]);
//                        put = true;
//                    }
//                }
//                if (!put){
//                    soap.add(plates[i]);
//                }
//            }
//        }
        List<Deque<Integer>> soap = new ArrayList<>();

        int last = 0;
        int ans = 0;
        for (int i : plates){
            if (i < last){
                 return ans;
            }
            ans++;
            if (soap.size() == 0){
                Deque<Integer> temp = new LinkedList<>();
                temp.add(i);
                soap.add(temp);
            }
            else if (i > soap.get(soap.size()-1).peekLast()) {
                Deque<Integer> temp = new LinkedList<>();
                temp.add(i);
                soap.add(temp);
            }
            else if (i < soap.get(0).peekFirst()){
                soap.get(0).push(i);
            }
            else {
                boolean put = false;
                for (int j=1; j < soap.size(); j++){
                    if (i > soap.get(j-1).peekLast() && i < soap.get(j).peekFirst()){
                        soap.get(j).push(i);
                        put = true;
                        break;
                    }
                }
                if (!put){
                    for (int j=0; j < soap.size(); j++){
                        if (i > soap.get(j).peekLast()){
                           soap.remove(j);
                           j--;
                           continue;
                        }
                        boolean done = false;
                        while (!soap.get(j).isEmpty() && i > soap.get(j).peekFirst()){
                            last = soap.get(j).pop();
                            if (soap.get(j).isEmpty()){
                                soap.remove(j);
                                j--;
                                done = true;
                                break;
                            }
                        }
                        if (!done && !soap.get(j).isEmpty() && i < soap.get(j).peekFirst()){
                            soap.get(j).push(i);
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] plates = new int[n];
        for (int i=0; i < n; i++){
            plates[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(sim (plates));
    }
}
