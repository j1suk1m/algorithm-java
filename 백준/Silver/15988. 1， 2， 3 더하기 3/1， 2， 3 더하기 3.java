import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
    
public class Main {
    private static final int MAX_NUM = (int) 1e6;
    private static final int MOD = (int) 1e9 + 9;
    
    // 인덱스 i에 대하여, i를 1, 2, 3의 합으로 나타내는 방법의 수를 저장한다.
    private static final long[] table = new long[MAX_NUM + 1];
    
    public static void main(String[] args) throws IOException {
        
        // 테이블을 초기화한다.
        initTable();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 개수를 저장한다.
        int T = Integer.parseInt(reader.readLine());
        
        // 테스트 케이스를 실행한다.
        while (T-- > 0) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(table[n]);
        }
          
    }
    
    private static void initTable() {
        
        // 기저 조건을 명시한다.
        table[1] = 1;
        table[2] = table[1] + 1;
        table[3] = table[1] + table[2] + 1;
        
        // 다이나믹 프로그래밍을 수행한다.
        for (int i = 4; i <= MAX_NUM; i++) {
            table[i] = (table[i - 1] + table[i - 2] + table[i - 3]) % MOD;
        }
        
    }
}