class Solution {
    public int[] solution(int[] arr, int[] query) {
        int[] answer;
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < query.length; i++) {
            if (i % 2 == 0) { // 짝수 인덱스인 경우
                end = start + query[i];
            } else { // 홀수 인덱스인 경우
                start += query[i];
            }
        }
        
        answer = new int[end - start + 1];
        
        for (int i = start; i <= end; i++) {
            answer[i - start] = arr[i];
        }
        
        return answer;
    }
}