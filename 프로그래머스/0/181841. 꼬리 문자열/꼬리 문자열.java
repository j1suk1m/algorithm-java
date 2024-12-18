class Solution {
    public String solution(String[] str_list, String ex) {
        StringBuilder answer = new StringBuilder();
        
        for (String string : str_list) {
            if (!string.contains(ex)) {
                answer.append(string);
            }
        }
        
        return answer.toString();
    }
}