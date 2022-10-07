import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RopeFolding {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_knots = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        boolean[] rope = new boolean[2*length + 1];
        int[] knot_pos = new int[num_knots];
        for (int i=0; i < num_knots; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            knot_pos[i] = 2*Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(knot_pos);
        int[] mid = new int[num_knots-1];
        for (int i=0; i < num_knots - 1; i++){
            mid[i] = (knot_pos[i] + knot_pos[i+1])/2;
        }
        int counter = 0;
        for (int i=0; i < num_knots; i++){
            boolean yes = true;
            for (int j=0; j < num_knots; j++){
                if (i - j >= 0 && i + j < num_knots){
                    if ((knot_pos[i] - knot_pos[i - j]) != (knot_pos[i + j] - knot_pos[i])){
                        yes = false;
                        break;
                    }
                }
            }
            if (yes){
                if (!rope[knot_pos[i]]){
                    counter++;
                    rope[knot_pos[i]] = true;
                }
            }
        }
        for (int i=0; i < num_knots - 1; i++){
            boolean yes = true;
            for (int j=0; j < num_knots; j++){
                if (i - j >= 0 && i + j  + 1 < num_knots){
                    if ((mid[i] - knot_pos[i - j]) != (knot_pos[i + j + 1] - mid[i])){
                        yes = false;
                        break;
                    }
                }
            }
            if (yes){
                if (!rope[mid[i]]){
                    counter++;
                    rope[mid[i]] = true;
                }
            }
        }
        if (rope[knot_pos[0]]){
            counter--;
        }
        if (rope[knot_pos[num_knots-1]]){
            counter--;
        }
        System.out.println(counter);
    }
}
