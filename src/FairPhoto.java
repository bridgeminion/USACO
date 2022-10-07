import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Photo implements Comparable<Photo>{
    int pos;
    char breed;

    Photo(int pos, char breed){
        this.pos = pos;
        this.breed = breed;
    }

    @Override
    public int compareTo(Photo photo) {
        return (this.pos - photo.pos);
    }
}

public class FairPhoto {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Photo> fence = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            fence.add(new Photo(Integer.parseInt(st1.nextToken()), st1.nextToken().charAt(0)));
        }
        Collections.sort(fence);
        int[] prefix = new int[n];
        int counter_g = 0;
        int counter_h = 0;
        int longest_g = 0;
        int longest_h = 0;
        int cur_start_g = 0;
        int cur_end_g = 0;
        int cur_start_h = 0;
        int cur_end_h = 0;
        for (int i=0; i < n; i++){
            if (fence.get(i).breed == 'G'){
                cur_end_g = i;
                counter_g++;
                if (i > 0 && fence.get(cur_end_h).pos - fence.get(cur_start_h).pos > longest_h){
                    longest_h = fence.get(i-1).pos - fence.get(cur_start_h).pos;
                }
                if (i > 0 && fence.get(i-1).breed == 'H'){
                    cur_start_g = i;
                }
            }
            else{
                cur_end_h = i;
                counter_h++;
                if (i > 0 && fence.get(cur_end_g).pos - fence.get(cur_start_g).pos > longest_g){
                    longest_g = fence.get(cur_end_g).pos - fence.get(cur_start_g).pos;
                }
                if (i > 0 && fence.get(i-1).breed == 'G'){
                    cur_start_h = i;
                }
            }
            prefix[i] = counter_g - counter_h;
        }
        int ans = Math.max(longest_g, longest_h);
        int[] start = new int[2*n+1];
        int[] end = new int[2*n+1];
        boolean[] found = new boolean[2*n+1];
        int[] length = new int[2*n+1];
        for (int i=0; i < 2*n + 1; i++){
            start[i] = -1;
            end[i] = -1;
        }
        for (int i=0; i < n; i++){
            if (!found[prefix[i] + n]){
                start[prefix[i] + n] = i;
                found[prefix[i] + n] = true;
            }
            else{
                end[prefix[i] + n] = i;
            }
        }
        for (int i=0; i < 2*n+1; i++){
            if (start[i] != -1 && end[i] != -1){
                length[i] = fence.get(end[i]).pos - fence.get(start[i]+1).pos;
                if (length[i] > ans){
                    ans = length[i];
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
