import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int q = Integer.parseInt(br.readLine());
        while (--q >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken()); 
            long m = Long.parseLong(st.nextToken()); 

            long total = (long) ((a * m)  * 105.6d / 60000d) ;
            bw.write(String.valueOf(total));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}