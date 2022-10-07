import java.io.*;
import java.util.StringTokenizer;

public class DNA {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("citystate.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_bull = Integer.parseInt(st.nextToken());
        int num_cow = Integer.parseInt(st.nextToken());
        String[] bull = new String[num_bull];
        String[] cow = new String[num_cow];
        int[][] ans = new int[num_bull][num_cow];
        for (int i=0; i < num_bull; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            bull[i] = st1.nextToken();
        }
        for (int i=0; i < num_cow; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            cow[i] = st1.nextToken();
        }
        for (int i=0; i < num_bull; i++){
            for (int j=0; j < num_cow; j++){
                for (int k=0; k < num_bull; k++){
                    if (k != i){
                        boolean yes = true;
                        for (int l=0; l < 2; l++){
                            if (bull[i].charAt(l) != bull[k].charAt(l) && cow[j].charAt(l) != bull[k].charAt(l)){
                                yes = false;
                                break;
                            }
                        }
                        if (yes){
                            ans[i][j]++;
                        }
                    }
                }
                for (int k=0; k < num_cow; k++){
                    if (k != j){
                        boolean yes = true;
                        for (int l=0; l < 2; l++){
                            if (bull[i].charAt(l) != cow[k].charAt(l) && cow[j].charAt(l) != cow[k].charAt(l)){
                                yes = false;
                                break;
                            }
                        }
                        if (yes){
                            ans[i][j]++;
                        }
                    }
                }
            }
        }
        for (int i=0; i < num_bull; i++){
            for (int j=0; j < num_cow; j++){
                System.out.print(ans[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
