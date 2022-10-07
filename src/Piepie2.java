import java.io.*;
import java.util.*;

public class Piepie2 {

    static class Pie{
        int first;
        int sec;
        int index;
        boolean bessie;
        int order;
        public Pie (int first, int sec, int index, boolean bessie, int order){
            this.first = first;
            this.sec = sec;
            this.index = index;
            this.bessie = bessie;
            this.order = order;
        }

        @Override
        public String toString() {
            return "first=" + first + ",sec=" + sec + ",index=" + index + ",bessie=" + bessie + ",order=" + order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("piepie.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        TreeSet<Pie> bessie = new TreeSet<>(new Comparator<Pie>() {
            @Override
            public int compare(Pie pie, Pie t1) {
                if (pie.sec == t1.sec) {
                    if (pie.order == t1.order){
                        return pie.index - t1.index;
                    }
                    return pie.order - t1.order;
                }
                return pie.sec - t1.sec;
            }
        });
        TreeSet<Pie> elsie = new TreeSet<>(new Comparator<Pie>() {
            @Override
            public int compare(Pie pie, Pie t1) {
                if (pie.first == t1.first) {
                    if (pie.order == t1.order){
                        return pie.index - t1.index;
                    }
                    return pie.order - t1.order;
                }
                return pie.first - t1.first;
            }
        });
        Queue<Pie> start = new LinkedList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int sec = Integer.parseInt(st1.nextToken());
            if (sec==0){
                start.add(new Pie (first, sec, i, true, 0));
            }
            else {
                bessie.add(new Pie (first, sec, i, true, 0));
            }
        }
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int sec = Integer.parseInt(st1.nextToken());
            if (sec==0){
                start.add(new Pie (first, sec,  n+i, false, 0));
            }
            else {
                elsie.add(new Pie (first, sec, n+i, false, 0));
            }
        }
        int[] ans = new int[2*n];
        for (int i=0; i < 2*n; i++){
            ans[i] = -1;
        }
        int counter = 1;
        while (!start.isEmpty()){
            int temp = start.size();
            for (int i=0; i < temp; i++){
                Pie cur = start.remove();
                ans[cur.index] = counter;
                if (cur.bessie){
                    Pie end_pie = elsie.floor(new Pie (cur.first, cur.sec, cur.index, cur.bessie, 1));
                    Pie start_pie = elsie.ceiling(new Pie (cur.first-d, cur.sec, cur.index, cur.bessie, -1));
                    Set<Pie> set;
                    if (start_pie != null && end_pie != null && start_pie.first <= end_pie.first){
                        try {
                            set = elsie.subSet(start_pie, true, end_pie, true);
                            Iterator it = set.iterator();
                            while (it.hasNext()){
                                Pie x = (Pie)it.next();
                                start.add(x);
                                it.remove();
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(start_pie + " and " + cur);
                            System.out.println(end_pie + " and " + cur);
                        }
//                        for (Pie j : set){
//                            start.add(j);
//                            elsie.remove(j);
//                        }
                    }
                }
                else {
                    Pie end_pie = bessie.floor(new Pie (cur.first, cur.sec, cur.index, cur.bessie, 1));
                    Pie start_pie = bessie.ceiling(new Pie (cur.first, cur.sec-d, cur.index, cur.bessie, -1));
                    Set<Pie> set;
                    if (start_pie != null && end_pie != null && start_pie.sec <= end_pie.sec){
                        try {
                            set = bessie.subSet(start_pie, true, end_pie, true);
                            Iterator it = set.iterator();
                            while (it.hasNext()){
                                Pie x = (Pie)it.next();
                                start.add(x);
                                it.remove();
                            }
                        } catch (Exception e) {
                            System.out.println("111" + start_pie + " and " + cur);
                            System.out.println("111" + end_pie + " and " + cur);
                        }
//                        for (Pie j : set){
//                            start.add(j);
//                            bessie.remove(j);
//                        }
                    }
                }
            }
            counter++;
        }
        for (int i=0; i < n; i++){
            pw.println(ans[i]);
        }
        pw.close();
    }
}
