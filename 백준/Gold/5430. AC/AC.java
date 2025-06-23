import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    private static final char REVERSE = 'R'; // 뒤집기 연산
    private static final char DELETE = 'D'; // 첫번째 숫자 삭제 연산
    private static Deque<Integer> deque;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine()); // 테스트 케이스 개수

        for (int i = 0; i < T; i++) {
            char[] operations = reader.readLine().toCharArray();
            int N = Integer.parseInt(reader.readLine()); // 배열의 길이
            
            deque = parseArrayString(N, reader.readLine());
            boolean isReversed = false;
            boolean isError = false;
            
            for (char operation : operations) {
                if (operation == REVERSE) isReversed = (!isReversed); // 실제로 뒤집지 않고 토글 처리
                else if (operation == DELETE) {
                    if (deque.isEmpty()) {
                        isError = true;
                        System.out.println("error");
                        break;
                    } else if (isReversed) { // isReversed가 true면 뒤집힌 상태이므로 뒤에서 삭제
                        deque.pollLast();
                    } else { // isReversed가 false면 정방향 상태이므로 앞에서 삭제
                        deque.pollFirst();  
                    }
                }
            }

            if (isError) continue;
            
            System.out.println(formatDeque(isReversed));            
        }
    }

    // [1,2,3] 형태의 문자열을 파싱해 Deque<Integer> 타입으로 반환
    private static Deque<Integer> parseArrayString(int arrayLength, String arrayString) {
        if (arrayLength == 0) return new ArrayDeque<>();
        
        return Arrays.stream(arrayString.substring(1, arrayString.length() - 1).split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty()) 
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    // Deque<Integer> 타입 객체를 [1,2,3] 형태의 문자열로 변환
    private static String formatDeque(boolean isReversed) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[");

        while (!deque.isEmpty()) {
            sb.append(isReversed ? deque.pollLast() : deque.pollFirst());
            
            if (!deque.isEmpty()) sb.append(",");
        }

        sb.append("]");
        
        return sb.toString();
    }
}