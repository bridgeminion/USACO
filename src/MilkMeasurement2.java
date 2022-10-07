import java.io.*;
import java.util.*;

class Measurement implements Comparable<Measurement> {
    int day;
    int id;
    int change;
    public Measurement(int day, int id, int change){
        this.day = day;
        this.id = id;
        this.change = change;
    }

    @Override
    public int compareTo(Measurement x) {
        return (this.day - x.day);
    }
}

class CowMilk{
    int amt;
    boolean display;
    public CowMilk(int amt, boolean display){
        this.amt = amt;
        this.display = display;
    }
}

public class MilkMeasurement2 {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        List<Measurement> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st1.nextToken());
            int id = Integer.parseInt(st1.nextToken());
            int change = Integer.parseInt(st1.nextToken());
            list.add(new Measurement(day, id, change));
        }
        Collections.sort(list);
        Map<Integer, CowMilk> map = new HashMap<>();
        int max = 0;
        int counter = 0;
        List<Integer> display = new ArrayList<>();
        for (Measurement i : list){
            map.putIfAbsent(i.id, new CowMilk(0, false));
            map.get(i.id).amt += i.change;
            if (map.get(i.id).amt >= max && !map.get(i.id).display){
                map.get(i.id).display = true;
                max = map.get(i.id).amt;
                for (int j : display){
                    map.get(j).display = false;
                }
                display.clear();
                display.add(i.id);
                counter++;
            }

        }
        pw.println(counter);
        pw.close();
    }
}
