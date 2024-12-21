class Solution {
    public int solution(int num, int k) {
        int answer = -1;
        char targetNumber = Integer.toString(k).charAt(0);
        
        for (int i = 0; i < Integer.toString(num).length(); i++) {
            if (targetNumber == Integer.toString(num).charAt(i)) {
                answer = i + 1;
                break;
            }
        }
        
        return answer;
    }
}