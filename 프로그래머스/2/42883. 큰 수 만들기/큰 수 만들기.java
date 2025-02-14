class Solution {   
    public String solution(String number, int k) {
        int digitCount = number.length(); // 숫자의 자릿수
        int iterationCount = digitCount - k; // 제거되지 않는 숫자의 수 // 정답 숫자의 자릿수
        int leftIndex = 0; // 탐색 시작 인덱스
        int rightIndex = k; // 탐색 끝 인덱스
        StringBuilder answer = new StringBuilder();

        while (iterationCount-- > 0) { 
            int max = -1; // 현재 자릿수에 올 수 있는 최댓값
            int indexOfMax = 0; // 최댓값의 인덱스
 
            for (int i = leftIndex; i <= rightIndex; i++) {
                int current = number.charAt(i) - '0'; // char -> int 변환
                
                // 최댓값 갱신
                if (max < current) {
                    max = current;
                    indexOfMax = i;
                }
            }
            
            answer.append(max);
            
            // 탐색 범위 갱신
            leftIndex = indexOfMax + 1;
            rightIndex = Math.min(rightIndex + 1, digitCount - 1); // 인덱스 초과 방지
        }

        return answer.toString();
    }
}