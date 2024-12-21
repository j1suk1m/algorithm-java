import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String symbol = "*";
        
        for (int i = 1; i <= n; i++) {
            System.out.println(symbol.repeat(i));
        }
    }
}