import java.io.*;
import java.util.*;

class Meeting implements Comparable<Meeting> {
    int weight;
    int pos;
    int speed;
    int time;
    public Meeting(int weight, int pos, int speed, int time){
        this.weight = weight;
        this.pos = pos;
        this.speed = speed;
        this.time = time;
    }

    @Override
    public int compareTo(Meeting x) {
        return (this.pos - x.pos);
    }
}

class Meeting1 implements Comparable<Meeting1> {
    int weight;
    int pos;
    int speed;
    int time;
    public Meeting1(int weight, int pos, int speed, int time){
        this.weight = weight;
        this.pos = pos;
        this.speed = speed;
        this.time = time;
    }

    @Override
    public int compareTo(Meeting1 x) {
        return (this.time - x.time);
    }
}


public class Meetings {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("meetings.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int barn_pos = Integer.parseInt(st.nextToken());
        List<Meeting> sorted_pos = new ArrayList<>();
        List<Meeting1> sorted_time = new ArrayList<>();
        TreeSet<Integer> right = new TreeSet<>();
        TreeSet<Integer> left = new TreeSet<>();
        int total_weight = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st1.nextToken());
            int pos = Integer.parseInt(st1.nextToken());
            int speed = Integer.parseInt(st1.nextToken());
            int time;
            if (speed == 1){
                time = barn_pos - pos;
                right.add(pos);
            }
            else{
                time = pos;
                left.add(pos);
            }
            sorted_pos.add(new Meeting(weight, pos, speed, time));
            sorted_time.add(new Meeting1(weight, pos, speed, time));
            total_weight += weight;
        }
        Collections.sort(sorted_pos);
        Collections.sort(sorted_time);
        int cur_weight = 0;
        int time = 0;
        int index = 0;
        int front_index = 0;
        int back_index = 1;
        while (cur_weight < (total_weight+1)/2){
            if (sorted_time.get(index).speed == 1){
                cur_weight += sorted_pos.get(n-back_index).weight;
                back_index++;
                index++;
            }
            else{
                cur_weight += sorted_pos.get(front_index).weight;
                front_index++;
                index++;
            }
        }
        time = sorted_time.get(index-1).time;
        int counter = 0;
        Queue<Meeting> cur = new LinkedList<>();
        for (Meeting i : sorted_pos){
            if (i.speed==1){
                cur.add(i);
            }
            else {
                while (!cur.isEmpty() && i.pos - cur.peek().pos > 2*time){
                    cur.poll();
                }
                counter += cur.size();
            }
        }
        pw.println(counter);
        pw.close();
    }
}
