class Solution {
    public int[] solution(int[] arr) {
        // 원본 배열의 길이보다 작거나 같은 2의 거듭제곱 중 최댓값 계산
        int newLength = Integer.highestOneBit(arr.length); 
        
        // 원본 배열의 길이가 이미 2의 거듭제곱인 경우 -> 그대로 반환
        if (newLength == arr.length) return arr;
        
        // 2를 곱해서 원본 배열의 길이보다 크거나 같은 2의 거듭제곱 중 최솟값으로 변환
        newLength <<= 1;
        int[] answer = new int[newLength];
        
        // 원본 배열 복사 및 확장
        for (int i = 0; i < newLength; i++) {
            answer[i] = i < arr.length ? arr[i] : 0;
        }
        
        return answer;
    }
}