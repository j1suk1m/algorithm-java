import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    
    private final String[] vowels = {"A", "E", "I", "O", "U"};
    private final List<String> dictionary = new ArrayList<>();
    
    public int solution(String word) {
        
        // 알파벳 모음만으로 구성된 길이 5 이하의 모든 단어 생성하기
        generateWordsWithVowels();
        
        // 사전 순서에 대해 오름차순 정렬하기
        Collections.sort(dictionary);
        
        // 사전에서 등장하는 순서 반환하기
        return dictionary.indexOf(word) + 1;
        
    }
    
    // 백트래킹을 이용해 가능한 모든 단어의 조합 게산하기
    private void backtrack(StringBuilder temp, int maxLength) {
        if (temp.length() == maxLength) {
            dictionary.add(temp.toString());
            return;
        }
        
        for (int i = 0; i < vowels.length; i++) {
            temp.append(vowels[i]);
            backtrack(temp, maxLength);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
    
    private void generateWordsWithVowels() {
        for (int length = 1; length <= 5; length++) {
            backtrack(new StringBuilder(), length);
        }
    }
    
}