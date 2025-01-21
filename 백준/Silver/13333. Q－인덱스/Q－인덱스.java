import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] citationCounts = new int[N];
        
        for (int i = 0; i < N; i++) {
            citationCounts[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        Arrays.sort(citationCounts);
        
        int answer = Math.min(N, citationCounts[0]);
        
        for (int i = N - 1; i >= 1; i--) {
            int Q = N - i;
            
            if (citationCounts[i - 1] <= Q && Q <= citationCounts[i]) {
                answer = Q;
                break;
            }
        }
        
        System.out.println(answer);
    }
}