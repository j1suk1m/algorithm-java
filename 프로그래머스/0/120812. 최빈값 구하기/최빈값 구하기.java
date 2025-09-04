class Solution {
    public int solution(int[] array) {
        int answer = 0; // 최빈값
        int maxCount = 0; // 최빈값의 등장 횟수
        int modeCount = 0; // 최빈값의 개수
        int[] counts = new int[1001];
        
        // 각 정수의 등장 횟수 저장
        for (int number : array) {
            counts[number]++;
        }
        
        // 최빈값과 최빈값의 개수 계산
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0 || counts[i] < maxCount) continue;
            
            if (counts[i] == maxCount) { // 최빈값이 여러 개인 경우
                modeCount++;
            } else if (counts[i] > maxCount) { // 최빈값을 갱신할 수 있는 경우
                answer = i;
                maxCount = counts[i];
                modeCount = 1;
            }
        }
        
        return modeCount == 1 ? answer : -1;
    }
}