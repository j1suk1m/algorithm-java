class Solution {
    public String[] solution(String[] strArr) {
        String[] answer = new String[strArr.length];
        
        for (int idx = 0; idx < strArr.length; idx++) {
            if (idx % 2 == 0) {
                answer[idx] = strArr[idx].toLowerCase();
            } else {
                answer[idx] = strArr[idx].toUpperCase();
            }
        }
        
        return answer;
    }
}