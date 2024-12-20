class Solution {
    public String solution(String my_string, int n) {
        String[] string = my_string.split("");
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < string.length; i++) {
            answer.append(string[i].repeat(n));
        }
        
        return answer.toString();
    }
}