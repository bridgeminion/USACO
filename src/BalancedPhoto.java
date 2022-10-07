import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BalancedPhoto {

    static class BinaryIndexedTree {

        static int[] bit;

        public BinaryIndexedTree (int n) {
            bit = new int[n+1];
        }

        public void updateBIT (int val, int index){
            index++;
            while (index < bit.length){
                bit[index] += val;
                index = getNext(index);
            }
        }

        public int getSum (int index){
            index++;
            int sum = 0;
            while (index > 0){
                sum += bit[index];
                index = getParent(index);
            }
            return sum;
        }

        public void createTree (int input[]){
            for (int i=1; i <= input.length; i++){
                updateBIT(input[i-1], i);
            }
        }

        private int getParent (int index){
            return index - (index & -index);
        }

        private int getNext (int index){
            return index + (index & -index);
        }

    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("tractor.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("tractor.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        int[] sorted = new int[n];
        Map<Integer, Integer> id = new HashMap<>();
        for (int i=0; i < n; i++){
            input[i] = Integer.parseInt(br.readLine());
            sorted[i] = input[i];
        }
        Arrays.sort(sorted);
        for (int i=0; i < n; i++){
            id.put(sorted[i], i+1);
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        bit.createTree(new int[n]);
        int[] l = new int[n];
        for (int i=0; i < n; i++){
            l[i] = bit.getSum(n-1) - bit.getSum(i);
            System.out.println(i + "th: ");
            System.out.println("total: " + bit.getSum(n-1));
            System.out.println("minus: " + bit.getSum(i));
            System.out.println("l[i]: " + l[i]);
            bit.updateBIT(1, id.get(input[i])-1);
        }
        int ans = 0;
        for (int i=0; i < n; i++){
            int r = n-i-l[i]-1;
            if (Math.max(r, l[i]) > 2*Math.min(r, l[i])){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
