import java.io.*;
import java.util.StringTokenizer;

public class Blink {

    public static int[] toggle(int[] input){
        int[] bulb = new int[input.length];
        for (int i=0; i < input.length; i++){
            bulb[i] = input[i]^input[(i-1+input.length)%input.length];
        }
        return (bulb);
    }

    public static int convert (int[] input){
        int counter = 0;
        int value = 1;
        for (int i=input.length - 1; i >= 0; i--){
            counter += value*input[i];
            value = value * 2;
        }
        return (counter);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Long time = Long.parseLong(st.nextToken());
        int[] light = new int[n];
        Long[] first = new Long[(int) Math.pow(2, n+1)];
        boolean[] found = new boolean[(int) Math.pow(2, n+1)];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            light[i] = Integer.parseInt(st1.nextToken());
        }
        found[convert(light)] = true;
        first[convert(light)] = 0L;
        Long counter = 0L;
        while (counter < time){
            light = toggle(light);
            counter++;
//            for (int i=0; i < light.length; i++){
//                System.out.print(light[i]);
//                System.out.print(" ");
//            }
//            System.out.println();
            if (found[convert(light)]){
                break;
            }
            else{
                found[convert(light)] = true;
                first[convert(light)] = counter;
            }
        }
        int num = -1;
//        System.out.println("counter = " + counter);
//        System.out.println(first[convert(light)]);
        if (counter >= time){
            num = 0;
        }
        else if (counter-first[convert(light)] != 0){
            num = (int)((time-first[convert(light)])%(counter-first[convert(light)]));
        }
//        System.out.print("time = ");
//        System.out.println(time-first[convert(light)]);
//        System.out.print("divided by ");
//        System.out.println(counter-first[convert(light)]);
        for (int i = 0; i < num; i++){
            light = toggle(light);
        }
//        System.out.print("num = ");
//        System.out.println(num);
        for (int i : light){
            System.out.println(i);
        }
    }
}
