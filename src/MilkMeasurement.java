import java.io.*;
import java.util.StringTokenizer;

public class MilkMeasurement {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int[] day = new int[n];
        String[] name = new String[n];
        int[] change = new int[n];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st1.nextToken());
            name[i] = st1.nextToken();
            change[i] = Integer.parseInt(st1.nextToken());
        }
        int bessie_cur = 7;
        int elsie_cur = 7;
        int mildred_cur = 7;
        boolean bessie = true;
        boolean elsie = true;
        boolean mildred = true;
        int counter = 0;
        for (int i=1; i <= 100; i++){
            for (int j=0; j < n; j++){
                if (day[j] == i){
                    if (name[j].equals("Bessie")){
                        bessie_cur += change[j];
                    }
                    if (name[j].equals("Elsie")){
                        elsie_cur += change[j];
                    }
                    if (name[j].equals("Mildred")){
                        mildred_cur += change[j];
                    }
                }
                int highest = Math.max(bessie_cur, Math.max(elsie_cur, mildred_cur));
                boolean bessie_next = true;
                boolean elsie_next = true;
                boolean mildred_next = true;
                if (bessie_cur != highest){
                    bessie_next = false;
                }
                if (elsie_cur != highest){
                    elsie_next = false;
                }
                if (mildred_cur != highest){
                    mildred_next = false;
                }
                if (bessie != bessie_next || elsie != elsie_next || mildred != mildred_next){
                    counter++;
                }
                bessie = bessie_next;
                elsie = elsie_next;
                mildred = mildred_next;
            }
        }
        pw.println(counter);
        pw.close();
    }
}
