class Solution {
    
    // 두 정수를 붙여서 쓴 값을 반환
    private static int sumString(String a, String b) {
        return Integer.parseInt(a + b);    
    }
    
    public int solution(int a, int b) {
        int answer = sumString(Integer.toString(a), Integer.toString(b));
        if (answer < 2 * a * b) {
            answer = 2 * a * b;
        }
        
        return answer;
    }
}