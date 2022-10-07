import java.io.*;
import java.util.*;

class Store implements Comparable<Store> {
    int gallons;
    int price;
    public Store(int gallons, int price){
        this.gallons = gallons;
        this.price = price;
    }

    @Override
    public int compareTo(Store x) {
        return this.price - x.price;
    }
}

public class Rental {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("rental.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cows = Integer.parseInt(st.nextToken());
        int num_stores = Integer.parseInt(st.nextToken());
        int num_farms = Integer.parseInt(st.nextToken());
        int[] milk = new int[num_cows];
        Store[] store = new Store[num_stores];
        int[] farm = new int[num_farms];
        for (int i=0; i < num_cows; i++){
//            StringTokenizer st1 = new StringTokenizer(br.readLine());
            milk[i] = Integer.parseInt(br.readLine());
        }
        for (int i=0; i < num_stores; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int gal = Integer.parseInt(st1.nextToken());
            int price = Integer.parseInt(st1.nextToken());
            store[i] = new Store(gal, price);
        }
        for (int i=0; i < num_farms; i++){
            farm[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(milk);
        Arrays.sort(store);
        Arrays.sort(farm);
        long ans = 0;
        long[] rental = new long[num_farms+1];
        rental[0] = 0;
        for (int i=1; i <= num_farms; i++){
            rental[i] = farm[num_farms - i] + rental[i-1];
        }
        int storeindex = num_stores-1;
        long[] profit = new long[num_cows+1];
        profit[0] = 0;
        for (int i=1; i <= num_cows; i++){
            profit[i] = profit[i-1];
            int temp = milk[num_cows-i];
            while (temp > 0){
                if (storeindex < 0){
                    break;
                }
                int gal = store[storeindex].gallons;
                int price = store[storeindex].price;
                if (temp > gal){
                    profit[i]+=gal*price;
                    temp -= gal;
                    storeindex--;
                }
                else{
                    profit[i]+=price*temp;
                    store[storeindex] = new Store(gal-temp, price);
                    break;
                }
            }
        }
        for (int i=0; i <= num_cows; i++){
            if (num_cows-i > num_farms){
                profit[i] += rental[num_farms];
            }
            else{
                profit[i] += rental[num_cows-i];
            }
//            System.out.println("Num sold");
//            System.out.println(i);
//            System.out.println(profit[i]);
            if (profit[i] > ans){
                ans = profit[i];
            }
        }
        pw.println(ans);
        pw.close();
    }
}

//import java.io.*;
//import java.util.*;
//public class Rental {
//    public static void main(String[] args) throws IOException {
////        BufferedReader br = new BufferedReader(new FileReader("rental.in"));
////        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int r = Integer.parseInt(st.nextToken());
//        int[] milkProduced = new int[n];
//        for(int i = 0; i < n; i++) {
//            milkProduced[i] = Integer.parseInt(br.readLine());
//        }
//        sort(milkProduced);
//        Shop[] shops = new Shop[m];
//        for(int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            shops[i] = new Shop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//        }
//        Arrays.sort(shops);
//        long[] maxProfit = new long[n+1];
//        {
//            int index = 0;
//            for(int i = 0; i < n; i++) {
//                maxProfit[i+1] = maxProfit[i];
//                while(index < m && milkProduced[i] > 0) {
//                    int use = Math.min(milkProduced[i], shops[index].quantity);
//                    maxProfit[i+1] += use * (long)shops[index].price;
//                    milkProduced[i] -= use;
//                    shops[index].quantity -= use;
//                    if(shops[index].quantity == 0) {
//                        index++;
//                    }
//                }
//            }
//        }
//        int[] rental = new int[r];
//        for(int i = 0; i < r; i++) {
//            rental[i] = Integer.parseInt(br.readLine());
//        }
//        sort(rental);
//        {
//            int a = n-1;
//            int rI = 0;
//            long rentalSoFar = 0;
//            while(a >= 0 && rI < r) {
//                rentalSoFar += rental[rI];
//                maxProfit[a] += rentalSoFar;
//                rI++;
//                a--;
//            }
//        }
//        long ret = 0;
//        for(long out: maxProfit) {
//            ret = Math.max(ret, out);
//        }
//        pw.println(ret);
//        pw.close();
//    }
//
//    public static void sort(int[] l) {
//        Arrays.sort(l);
//        for(int i = 0; i < l.length-1-i; i++) {
//            l[i] ^= l[l.length-1-i];
//            l[l.length-1-i] ^= l[i];
//            l[i] ^= l[l.length-1-i];
//        }
//    }
//
//    static class Shop implements Comparable<Shop> {
//        public int quantity, price;
//        public Shop(int a, int b) {
//            quantity=a;
//            price=b;
//        }
//        public int compareTo(Shop s) {
//            return s.price - price;
//        }
//    }
//
//}