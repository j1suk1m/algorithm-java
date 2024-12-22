class Solution {
    public String solution(int age) {
        StringBuilder answer = new StringBuilder();
        String ageString = Integer.toString(age);
        
        for (int i = 0; i < ageString.length(); i++) {
            answer.append((char)('a' + Integer.parseInt(ageString.charAt(i) + "")));
        }
                          
        return answer.toString();
    }
}