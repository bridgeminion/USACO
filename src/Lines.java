import java.io.*;
import java.util.*;

class Fraction {
    int num;
    int den;
    public Fraction (int num, int den){
        this.num = num;
        this.den = den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return num == fraction.num &&
                den == fraction.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}

public class Lines {

    static Set<Fraction> ans = new HashSet<>();

    public static void simplify (int num, int den){
        boolean pos = true;
        if (num < 0 && den >= 0){
            pos = false;
        }
        if (num >= 0 && den < 0){
            pos = false;
        }
        num = Math.abs(num);
        den = Math.abs(den);
        if (num == 0){
            ans.add(new Fraction(0, 0));
            return;
        }
        if (den == 0){
            ans.add(new Fraction(1, 0));
            return;
        }
        for (int i=Math.min(num, den); i >= 2; i--){
            if (num%i == 0 && den%i == 0){
                if (!pos){
                    ans.add(new Fraction(-num/i, den/i));
                    return;
                }
                ans.add(new Fraction(num/i, den/i));
                return;
            }
        }
        if (!pos){
            ans.add(new Fraction(-num, den));
            return;
        }
        ans.add(new Fraction(num, den));
    }

    public static void slope (Coordinate a, Coordinate b){
        simplify(b.y - a.y, b.x - a.x);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moobuzz.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Coordinate> list = new ArrayList<>(n);
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (Coordinate a : list){
            for (Coordinate b : list){
                if (a != b){
                    slope(a, b);
                }
            }
        }
//        for (Fraction i : ans){
//            System.out.println(i.num + "/" + i.den);
//        }
        System.out.println(ans.size());
    }
}
