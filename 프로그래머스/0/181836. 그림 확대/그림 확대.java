class Solution {
    public String[] solution(String[] picture, int k) {
        if (k == 1) return picture;
        
        String[] answer = new String[picture.length * k];
        
        for (int i = 0; i < picture.length; i++) {
            String expandedResult = expand(picture[i], k);
            
            for (int j = 0; j < k; j++) {
                answer[i * k + j] = expandedResult;
            }
        }
        
        return answer;
    }
    
    // 원본 문자열의 각 문자를 k번씩 반복
    String expand(String original, int k) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < original.length(); i++) {
            char ch = original.charAt(i);
            
            for (int j = 0; j < k; j++) {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
}