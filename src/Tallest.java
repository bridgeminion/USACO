import java.io.*;
import java.util.*;

class Dub{
    int a;
    int b;
    public Dub (int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dub dub = (Dub) o;
        return a == dub.a &&
                b == dub.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

public class Tallest {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int index = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int num_input = Integer.parseInt(st.nextToken());
        int[] start = new int[n];
        int[] end = new int[n];
        Set<Dub> set = new HashSet<>();
        for (int i=0; i < num_input; i++){
            StringTokenizer st1  = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st1.nextToken());
            int last = Integer.parseInt(st1.nextToken());
            if (first > last){
                int temp = first;
                first = last;
                last = temp;
            }
            if (!set.contains(new Dub(first, last))){
                set.add(new Dub(first, last));
                start[first-1]++;
                end[last-1]++;
            }
        }
        int counter = 0;
        for (int i=0; i < n; i++){
            counter -= end[i];
            System.out.println(height-counter);
            counter += start[i];
        }
    }
}
