import java.io.*;
import java.util.*;

public class IntToEnglish {

    public static String numberToWords(int num) {
        String[] one_digit = {"Zero", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};
        String[] ten_twenty = {"Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
        String[] thirty_ninety = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        String ans = "";
        int[] digit = new int[11];
        int divide = 1;
        for (int i=1; i <= 10; i++){
            digit[11-i] = (num /divide)%10;
            divide *= 10;
        }
//        for (int i=1; i <= 10; i++){
//            System.out.println(digit[i]);
//     \\
        if (digit[1] != 0){
            ans += one_digit[digit[1]] + "Billion ";
        }
        boolean printZero = true;
        boolean printMil = false;
        boolean skipFour = false;
        if (digit[2] != 0){
            ans += one_digit[digit[2]] + "Hundred ";
            printMil = true;
        }
        if (digit[3] != 0){
            printMil = true;
            if (digit[3] == 1){
                ans += ten_twenty[digit[4]];
                skipFour = true;
            }
            else {
                ans += thirty_ninety[digit[3]];
            }
        }
        if (digit[4] != 0 && !skipFour){
            printMil = true;
            ans += one_digit[digit[4]];
        }
        if (printMil){
            ans += "Million ";
        }
        boolean printThousand = false;
        boolean skipSeven = false;
        if (digit[5] != 0){
            ans += one_digit[digit[5]] + "Hundred ";
            printThousand = true;
        }
        if (digit[6] != 0){
            printThousand = true;
            if (digit[6] == 1){
                ans += ten_twenty[digit[7]];
                skipSeven = true;
            }
            else {
                ans += thirty_ninety[digit[6]];
            }
        }
        if (digit[7] != 0 && !skipSeven){
            printThousand = true;
            ans += one_digit[digit[7]];
        }
        if (printThousand){
            ans += "Thousand ";
        }
        boolean skipTen = false;
        if (digit[8] != 0){
            ans += one_digit[digit[8]] + "Hundred ";
        }
        if (digit[9] != 0){
            if (digit[9] == 1){
                ans += ten_twenty[digit[10]];
                skipTen = true;
            }
            else {
                ans += thirty_ninety[digit[9]];
            }
        }
        if (digit[10] != 0 && !skipTen){
            ans += one_digit[digit[10]];
        }
        if (num == 0){
            return "Zero";
        }
        return ans.substring(0, ans.length()-1);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        System.out.println(numberToWords(num));
    }
}
