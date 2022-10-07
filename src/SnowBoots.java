import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

class Boot{
    int snow;
    int dist;
    public Boot(int snow, int dist){
        this.snow = snow;
        this.dist = dist;
    }
}

public class SnowBoots {

    static int ans = 250;
    static boolean[][] visited = new boolean[250][250];

    public static void dfs (int tile_index, int boot_index, int num_tiles, int num_boots, int[] snow, List<Boot> boot, int counter){
        if (tile_index >= num_tiles-1){
            ans = Math.min(ans, counter);
            return;
        }
        if (boot_index >= num_boots){
            return;
        }
        if (visited[tile_index][boot_index]){
            return;
        }
        visited[tile_index][boot_index] = true;
//        int index = tile_index;
//        int curdist = 0;
        for (int i=tile_index+1; i < num_tiles; i++){
            if (i - tile_index > boot.get(boot_index).dist){
                break;
            }
            if (boot.get(boot_index).snow >= snow[i]){
                dfs (i, boot_index, num_tiles, num_boots, snow, boot, counter);
            }
        }
        for (int i=boot_index+1; i < num_boots; i++){
            if (boot.get(i).snow >= snow[tile_index]){
                dfs (tile_index, i, num_tiles, num_boots, snow, boot, counter + i - boot_index);
            }
        }
//        while (index < num_tiles-1 && curdist < boot.get(boot_index).dist){
//            index++;
//            curdist++;
//            if (snow[index] <= boot.get(boot_index).snow){
//                dfs (index, boot_index, num_tiles, num_boots, snow, boot, counter);
//            }
//            for (int i=boot_index+1; i < num_boots; i++){
//                if (snow[index] <= boot.get(i).snow){
//                    dfs (index, i, num_tiles, num_boots, snow, boot, counter+i-boot_index);
//                }
//            }
//        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("snowboots.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_tiles = Integer.parseInt(st.nextToken());
        int num_boots = Integer.parseInt(st.nextToken());
        int[] snow = new int[num_tiles];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < num_tiles; i++){
            snow[i] = Integer.parseInt(st1.nextToken());
        }
        List<Boot> boot = new ArrayList<>();
        for (int i=0; i < num_boots; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            boot.add(new Boot(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken())));
        }
        dfs(0, 0, num_tiles, num_boots, snow, boot, 0);
        pw.println(ans);
        pw.close();
    }
}
