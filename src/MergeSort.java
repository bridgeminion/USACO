import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MergeSort {

    public static void mergePrintCopy (int start, int end, int[] a, int[] b, int[] sorted, int[] array){
        int i = 0;
        int j = 0;
        for (int index = 0; index < end-start; index++){
            if (i >= a.length){
                sorted[index] = b[j];
                j++;
            }
            else if (j >= b.length){
                sorted[index] = a[i];
                i++;
            }
            else if (a[i] < b[j]){
                sorted[index] = a[i];
                i++;
            }
            else {
                sorted[index] = b[j];
                j++;
            }
        }
        for (int k=0; k < end-start; k++){
            array[start+k] = sorted[k];
        }
        for (int k : array){
            System.out.print(k + " ");
        }
        System.out.println();
    }

    public static void sort (int start, int end, int[] cur, int[] array){
        int len = end-start;
        if (len == 0 || len == 1){
            return;
        }
        int mid = (start+end+1)/2;
        int[] a = new int[mid-start];
        int[] b = new int[end-mid];
        for (int i=0; i < mid-start; i++){
            a[i] = array[start+i];
        }
        for (int i=0; i < end-mid; i++){
            b[i] = array[mid+i];
        }
        sort(start, mid, a, array);
        sort(mid, end, b, array);
        mergePrintCopy(start, end, a, b, cur, array);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("cowqueue.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++){
            array[i] = Integer.parseInt(st1.nextToken());
        }
        sort(0, array.length, array, array);
//        int[] a = {1, 8};
//        int[] b = {2, 7};
//        mergePrintCopy(0, 4, a, b, array);
        pw.close();
    }
}
