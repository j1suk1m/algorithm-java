import java.util.*;

class Solution {
    private static int[] numbers;
    
    // 오른쪽으로 순회하면서 발견한 첫번째 2의 인덱스 반환
    private static int getLeftIndex() {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 2) {
                return i;
            }
        }
        
        return -1;
    }
    
    // 왼쪽으로 순회하면서 발견한 첫번째 2의 인덱스 반환
    private static int getRightIndex() {        
        for (int i =  numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == 2) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int[] solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        numbers = arr;
        int left = getLeftIndex();
        int right = getRightIndex();
        
        if (left == -1 || right == -1) {
            return new int[] {-1};
        }
        
        for (int i = left; i <= right; i++) {
            answer.add(numbers[i]);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}