class Solution {
    public int solution(String number) {
        int answer = 0;
        String[] digits = number.split("");
        
        for (String digit : digits) {
            answer += Integer.parseInt(digit);
        }
        
        return answer % 9;
    }
}