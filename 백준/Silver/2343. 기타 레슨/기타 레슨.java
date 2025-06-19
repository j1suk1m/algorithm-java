import java.util.*;
import java.io.*;

class Main {
    private static int N; // 강의의 개수
    private static int M; // 블루레이의 최대 개수
    private static int[] lengths; // 강의 길이의 배열 (단위: 분)
    
    public static void main(String[] args) throws IOException {
        input();

        int low = getMaxLength() - 1;
        int high = getTotalLength() + 1;

        System.out.println(runParametricSearch(low, high));
    }

    // 데이터 입력
    private static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        lengths = new int[N];
        tokenizer = new StringTokenizer(reader.readLine(), " ");

        for (int i = 0; i < N; i++) {
            lengths[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    // 제일 긴 강의 길이 계산
    private static int getMaxLength() {
        int max = 0;
        
        for (int length : lengths) {
           if (length > max) {
               max = length;
           } 
        }

        return max;
    }

    // 전체 강의 길이의 합 계산
    private static int getTotalLength() {
        int total = 0;

        for (int length : lengths) {
            total += length;
        }

        return total;
    }

    // 매개 변수 탐색
    private static int runParametricSearch(int low, int high) {
        while (low + 1 < high) {
            int mid = low + (high - low) / 2; // (low + high) / 2와 동일한 표현식

            if (check(mid)) {
                high = mid; // 조건 만족 -> 블루레이 최소 크기를 줄여도 만족하는지 탐색
            } else {
                low = mid; // 조건 불만족 -> 블루레이 최소 크기를 늘림
            }
        }

        return high;
    }

    // 블루레이의 크기가 mid일 때, 총 블루레이 개수가 M 이하인지 확인
    private static boolean check(int mid) {
        int count = 1;
        int currentSize = 0;

        for (int length : lengths) {
            if (currentSize + length > mid) {
                count++;
                currentSize = 0;
            }

            if (count > M) {
                return false;
            }

            currentSize += length;
        }

        return true;
    }
}