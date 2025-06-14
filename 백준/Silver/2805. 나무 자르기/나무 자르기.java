import java.util.*;
import java.io.*;

class Main {
    private static int[] heights;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        int treeCount = Integer.parseInt(tokenizer.nextToken()); 
        int targetHeight = Integer.parseInt(tokenizer.nextToken());  
        
        heights = new int[treeCount];
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        
        for (int i = 0; i < treeCount; i++) {
            heights[i] = Integer.parseInt(tokenizer.nextToken()); 
        }

        Arrays.sort(heights);

        // check(low) != check(high)가 되도록 low, high의 초깃값 설정
        int low = 0; // check(low) == true
        int high = heights[treeCount - 1]; // check(high) == false

        while (low + 1 < high) {
            int mid = (low + high) / 2; // 절단기 높이

            if (check(treeCount, targetHeight, mid)) { // 적어도 ${targetHeight}미터를 가져갈 수 있는 경우
                low = mid; // 절단기 높이를 높여도 ${targetHeight}미터 이상을 가져갈 수 있는지 확인
            } else {
                high = mid; // 절단기 높이를 낮춰 나무를 더 많이 자름
            }
        }

        System.out.println(low);
    }

    private static boolean check(int treeCount, int targetHeight, int mid) {
        long totalHeight = 0L; // 덧셈 과정에서 오버플로우가 발생할 수 있으므로 int가 아닌 long 사용

        for (int i = treeCount - 1; i >= 0; i--) {
            int height = heights[i];

            if (mid >= height) { // 조기 종료 조건
                break;
            }

            totalHeight += (long) height - mid;
        }

        return totalHeight >= (long) targetHeight;
    }
}