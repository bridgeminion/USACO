import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Line {

    public static void P (int n1, long input, List<Integer> list, long fact){
        int n = n1;
        fact = fact/n;
        while (n > 0){
            n--;
            int temp = 0;
            if (input!=0){
                temp = (int)((input-1)/fact);
            }
            System.out.print(list.get(temp) + " ");
            list.remove(temp);
            if (input!=0){
                input = (input-1)%fact+1;
            }
            if (fact!=1){
                fact/=n;
            }
        }
        for (int i=1; i <= n1; i++){
            list.add(i);
        }
        System.out.println();
    }

    public static void Q (int n, List<Integer> input, List<Integer> list, long fact){
        long ans = 0;
        fact/=n;
        for (int i=0; i < n; i++){
            int temp = input.get(0);
            ans += (temp-list.get(0))*fact;
            list.remove((Integer)temp);
            input.remove(0);
            for (int j=0; j < input.size(); j++){
                if (input.get(j) > temp){
                    input.set(j, input.get(j)-1);
                }
                if (list.get(j) > temp){
                    list.set(j, list.get(j)-1);
                }
            }
            if (fact!=1){
                fact/=(n-i-1);
            }
        }
        for (int i=1; i <= n; i++){
            list.add(i);
        }
        System.out.println(ans+1);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        long fact = 1;
        for (int i=1; i <= n; i++){
            fact*=i;
            list.add(i);
        }
        for (int i=0; i < k; i++){
            String temp = br.readLine();
            if (temp.equals("P")){
                long input = Long.parseLong(br.readLine());
                P (n, input, list, fact);
            }
            else {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                List<Integer> input = new ArrayList<>();
                for (int j=0; j < n; j++){
                    input.add(Integer.parseInt(st1.nextToken()));
                }
                Q (n, input, list, fact);
            }
        }
    }
}
