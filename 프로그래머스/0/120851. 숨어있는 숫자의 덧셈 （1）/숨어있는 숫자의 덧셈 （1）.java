class Solution {
    public int solution(String my_string) {
        char[] input = my_string.toCharArray();
        int answer = 0;
        
        for (int i = 0; i < input.length; i++) {
            char current = input[i];
            
            if (Character.isDigit(current)) {
                answer += Character.getNumericValue(current);
            }
        } 
        
        return answer;
    }
}