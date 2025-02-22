class Solution {  
    public int solution(String name) {
        int totalLength = name.length();
        int alphabetChangeCount = 0;
        int cursorMoveCount = totalLength - 1;
        
        for (int i = 0; i < totalLength; i++) {
            char currentAlphabet = name.charAt(i);
            
            // 알파벳 변경을 위한 조작 횟수 추가
            alphabetChangeCount += Math.min(currentAlphabet - 'A', 'Z' - currentAlphabet + 1);
            
            int j = i + 1;
            
            // 다음 알파벳이 A인 경우, 연속된 A의 마지막 인덱스 계산
            while (j < totalLength && name.charAt(j) == 'A') {
                j++;
            }
            
            // 세 가지 경우 중 커서 이동 횟수가 가장 작은 쪽 선택
            // 1. 오른쪽 단방향 이동
            // 2. 오른쪽 방향으로 이동하다가 왼쪽 방향으로 변경
            // 3. 왼쪽 방향으로 이동하다가 오른쪽 방향으로 변경
            cursorMoveCount = Math.min(cursorMoveCount, 2 * i + totalLength - j);
            cursorMoveCount = Math.min(cursorMoveCount, i + 2 * (totalLength - j));           
        }
        
        return alphabetChangeCount + cursorMoveCount;
    }
}