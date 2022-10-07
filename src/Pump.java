import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class Pump {

    static class Pipe {
        int end;
        int cost;
        int flow;

        public Pipe(int end, int cost, int flow) {
            this.end = end;
            this.cost = cost;
            this.flow = flow;
        }
    }

    static class Nodes{
        List<Pipe> list;
        public Nodes (List<Pipe> list){
            this.list = list;
        }
    }

    static class State {

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("balancing.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Nodes[] adj = new Nodes[n+1];
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            int f = Integer.parseInt(st1.nextToken());
            adj[a].list.add(new Pipe (b, c, f));
            adj[b].list.add(new Pipe (a, c, f));
        }

    }
}
