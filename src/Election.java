import java.io.*;
import java.util.*;

class Candidate implements Comparable<Candidate>{
    int index;
    int first;
    int second;
    public Candidate(int index, int first, int second){
        this.index = index;
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Candidate candidate) {
        if (this.first == candidate.first){
            return (candidate.second - this.second);
        }
        return (candidate.first - this.first);
    }
}

class Candidate2 implements Comparable<Candidate2>{
    int index;
    int first;
    int second;
    public Candidate2(int index, int first, int second){
        this.index = index;
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Candidate2 candidate) {
        return (candidate.second - this.second);
    }
}


public class Election {

    public static int sort (Candidate a, Candidate b){
        return (a.second - b.second);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Candidate> cow = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            Candidate temp = new Candidate(i + 1, Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
            cow.add(temp);
        }
        Collections.sort(cow);
        List<Candidate2> cur = new ArrayList<>();
        for (int i=0; i < k; i++){
            Candidate2 temp = new Candidate2(cow.get(i).index, cow.get(i).first, cow.get(i).second);
            cur.add(temp);
        }
        Collections.sort(cur);
        pw.println(cur.get(0).index);
        pw.close();
    }
}
