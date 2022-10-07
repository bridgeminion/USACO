import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PartyLamps {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int lamps = Integer.parseInt(st1.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        List<Integer> on = new ArrayList<>();
        List<Integer> off = new ArrayList<>();
        while (st2.hasMoreTokens()){
            int temp = Integer.parseInt(st2.nextToken());
            if (temp != -1) {
                on.add(temp);
            }
        }
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        while (st3.hasMoreTokens()){
            int temp = Integer.parseInt(st3.nextToken());
            if (temp != -1){
                off.add(temp);
            }
        }
        boolean[] light = new boolean[n];
        List<String> ans = new ArrayList<>();
        for (int a=0; a < 2; a++){
            for (int b=0; b < 2; b++){
                for (int c=0; c < 2; c++){
                    for (int d=0; d < 2; d++){
                        if ((a+b+c+d)%2 == lamps%2 && a+b+c+d <= lamps){
                            for (int i=0; i < n; i++){
                                light[i] = true;
                            }
                            if (a==1){
                                for (int i=0; i < n; i++){
                                    light[i] = !light[i];
                                }
                            }
                            if (b==1){
                                for (int i=0; i < n; i+=2){
                                    light[i] = !light[i];
                                }
                            }
                            if (c==1){
                                for (int i=1; i < n; i+=2){
                                    light[i] = !light[i];
                                }
                            }
                            if (d==1){
                                for (int i=0; i < n; i+=3){
                                    light[i] = !light[i];
                                }
                            }
                            boolean match = true;
                            for(int element : on){
                                if (!light[element - 1]) {
                                    match = false;
                                    break;
                                }
                            }
                            for(int element : off){
                                if(light[element-1]){
                                    match = false;
                                    break;
                                }
                            }
                            if (match){
                                StringBuilder temp = new StringBuilder();
                                for (int i=0; i < n; i++){
                                    int p = 0;
                                    if (light[i]){
                                        p = 1;
                                    }
                                    temp.append(p);
                                }
                                ans.add(temp.toString());
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(ans);
        for (String i : ans){
            System.out.println(i);
        }
        if (ans.size() == 0){
            System.out.println("IMPOSSIBLE");
        }
    }
}
