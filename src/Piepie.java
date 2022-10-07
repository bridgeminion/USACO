import java.io.*;
import java.util.*;

public class Piepie {

    static class Pie{
        int first;
        int sec;
        int index;
        boolean bessie;
        public Pie (int first, int sec, int index, boolean bessie){
            this.first = first;
            this.sec = sec;
            this.index = index;
            this.bessie = bessie;
        }

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("piepie.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        List<Pie> bessie = new ArrayList<>(n);
        List<Pie> elsie = new ArrayList<>(n);
        Queue<Pie> start = new LinkedList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int sec = Integer.parseInt(st1.nextToken());
            if (sec==0){
                start.add(new Pie (first, sec, i, true));
            }
            else {
                bessie.add(new Pie (first, sec, i, true));
            }
        }
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int sec = Integer.parseInt(st1.nextToken());
            if (sec==0){
                start.add(new Pie (first, sec, n+i, false));
            }
            else {
                elsie.add(new Pie (first, sec, n+i, false));
            }
        }
        bessie.sort(new Comparator<Pie>() {
            @Override
            public int compare(Pie pie, Pie t1) {
                if (pie.sec == t1.sec) {
                    return pie.index - t1.index;
                }
                return pie.sec - t1.sec;
            }
        });
        elsie.sort(new Comparator<Pie>() {
            @Override
            public int compare(Pie pie, Pie t1) {
                if (pie.first == t1.first) {
                    return pie.index - t1.index;
                }
                return pie.first - t1.first;
            }
        });
        int[] ans = new int[2*n];
        for (int i=0; i < 2*n; i++){
            ans[i] = -1;
        }
        boolean[] visited = new boolean[2*n];
        int counter = 1;
        while (!start.isEmpty()){
            int temp = start.size();
            for (int i=0; i < temp; i++){
                Pie cur = start.remove();
                ans[cur.index] = counter;
                if (cur.bessie){
                    int end_index = Collections.binarySearch(elsie, new Pie (cur.first, Integer.MAX_VALUE, cur.index, cur.bessie), new Comparator<Pie>() {
                        @Override
                        public int compare(Pie pie, Pie t1) {
                            if (pie.first==t1.first){
                                return pie.sec - t1.sec;
                            }
                            return pie.first - t1.first;
                        }
                    });
                    int start_index = Collections.binarySearch(elsie, new Pie (cur.first-d, -1, cur.index, cur.bessie), new Comparator<Pie>() {
                        @Override
                        public int compare(Pie pie, Pie t1) {
                            if (pie.first==t1.first){
                                return pie.sec - t1.sec;
                            }
                            return pie.first - t1.first;
                        }
                    });
                    if (start_index < 0){
                        start_index = (start_index+1)*-1;
                    }
                    if (end_index < 0){
                        end_index = (end_index+1)*-1;
                    }
                    start.addAll(bessie.subList(start_index, end_index));
//                    for (int j=start_index; j < end_index; j++){
//                        if (!visited[elsie.get(j).index]){
//                            start.add(elsie.get(j));
//                            visited[elsie.get(j).index] = true;
//                        }
//                    }
                }
                else {
                    int end_index = Collections.binarySearch(bessie, new Pie (Integer.MAX_VALUE, cur.sec, cur.index, cur.bessie), new Comparator<Pie>() {
                        @Override
                        public int compare(Pie pie, Pie t1) {
                            if (pie.sec==t1.sec){
                                return pie.first - t1.first;
                            }
                            return pie.sec - t1.sec;
                        }
                    });
                    int start_index = Collections.binarySearch(bessie, new Pie (-1, cur.sec-d, cur.index, cur.bessie), new Comparator<Pie>() {
                        @Override
                        public int compare(Pie pie, Pie t1) {
                            if (pie.sec==t1.sec){
                                return pie.first - t1.first;
                            }
                            return pie.sec - t1.sec;
                        }
                    });
                    if (start_index < 0){
                        start_index = (start_index+1)*-1;
                    }
                    if (end_index < 0){
                        end_index = (end_index+1)*-1;
                    }
                    start.addAll(bessie.subList(start_index, end_index));
//                    for (int j=start_index; j < end_index; j++){
//                        if (!visited[bessie.get(j).index]){
//                            start.add(bessie.get(j));
//                            visited[bessie.get(j).index] = true;
//                        }
//                    }
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