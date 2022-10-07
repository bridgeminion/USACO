import java.io.*;
import java.util.*;

class Grass implements Comparable<Grass> {
    long start;
    long end;
    public Grass (long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Grass x) {
        return (int)(this.start - x.start);
    }
}

public class SocialDistancing {

    static List<Grass> grass = new ArrayList<>();
    static int index = 0;
    static long result = 0;

    public static long getNext (long guess) {
        for (int i=index; i < grass.size(); i++) {
            if (grass.get(i).start <= guess && grass.get(i).end >= guess) {
                index = i;
                return guess;
            } else if (grass.get(i).start > guess) {
                index = i;
                return grass.get(i).start;
            }
        }
        return -1;
    }

    public static boolean put (long big, int num_cows, int cur, long pos, long max) {
        while (cur <= num_cows - 1 && pos <= max) {
            if (cur == num_cows - 1) {
                if (big > result){
                    result = big;
                }
                return true;
            }
            long temp = getNext(pos+big);
            if (temp == -1){
                return false;
            }
            pos = temp;
            cur++;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
//
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("socdist.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cows = Integer.parseInt(st.nextToken());
        int num_grass = Integer.parseInt(st.nextToken());
        for (int i=0; i < num_grass; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            long start1 = Long.parseLong(st1.nextToken());
            long end1 = Long.parseLong(st1.nextToken());
            grass.add(new Grass(start1, end1));
        }
        Collections.sort(grass);
        long min = grass.get(0).start;
        long max = grass.get(grass.size()-1).end;
        long big = (max-min)/(num_cows-1);
        long low = 0, high = big;
        while (low <= high) {
            index = 0;
            long mid = (high+low)/2;
            long test = mid;
            if (put (test, num_cows, 0, min, max)){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        pw.println(result);
        pw.close();
//        for (long i = big; i >= 0; i--){w
//            index = 0;
//            put (i, num_cows, 0, min, max);
//            if (put (guess, num_cows, 0, min, max);){
//                pw.println(i);
//                pw.close();
//                return;
//            }
//        }
//        pw.close();
    }
}
