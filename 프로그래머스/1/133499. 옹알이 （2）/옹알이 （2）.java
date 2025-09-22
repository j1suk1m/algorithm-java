class Solution {
    final String POSSIBLE = "O";
    final String IMPOSSIBLE = "X";
    final String[] possibleWords = new String[] {"aya", "ye", "woo", "ma"};
    final String[] impossibleWords = new String[] {"ayaaya", "yeye", "woowoo", "mama"};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String curr : babbling) {
            // 불가능한 발음 X로 표시
            for (String word : impossibleWords) {
                curr = curr.replace(word, IMPOSSIBLE);
            }
            
            // 가능한 발음 O로 표시
            for (String word : possibleWords) {
                curr = curr.replace(word, POSSIBLE);
            }
            
            if (canPronounce(curr)) answer++;
        }
        
        return answer;
    }
    
    boolean canPronounce(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != POSSIBLE.charAt(0)) {
                return false;
            }
        }
        
        return true;
    }
}