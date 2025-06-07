// 날짜를 나타내는 정수 배열 [year, month, day]를 yyyymmdd 형태의 정수로 변환하여 비교
// month와 day는 항상 두 자리로 맞춰야 [2025, 6, 7] vs [2025, 5, 30]과 같은 테스트 케이스를 통과할 수 있음
// String.format("%0Nd", str)를 사용하면 str의 길이가 N보다 작을 경우 왼쪽에 0을 패딩해 총 N 자리를 만들 수 있음

class Solution {
    public int solution(int[] date1, int[] date2) {
        return joinDatePartsAsInt(date1) < joinDatePartsAsInt(date2) ? 1 : 0; 
    }
    
    // 정수 배열 [year, month, day]를 yyyymmdd 형태의 정수로 변환
    private int joinDatePartsAsInt(int[] date) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < date.length; i++) {
            result.append(String.format("%02d", date[i])); // 두 자리보다 짧은 경우 왼쪽에 0을 채워 두 자리로 만듦  
        }
        
        return Integer.parseInt(result.toString());
    }
}