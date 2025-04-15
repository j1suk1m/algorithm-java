import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    private final String word; // 단어
    private final int conversionCount; // 변환 횟수
    
    Node(String word, int conversionCount) {
        this.word = word;
        this.conversionCount = conversionCount;
    }
    
    public String getWord() {
        return word;
    }
    
    public int getConversionCount() {
        return conversionCount;
    }
}

class Solution {
    private boolean[] visited; // 단어 방문 여부
    private final Queue<Node> queue = new LinkedList<>();
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        // target이 words에 포함되지 않는다면 begin을 target으로 변환 불가 -> 0 반환
        if (!Arrays.asList(words).contains(target)) {
            return answer;
        }
        
        // 방문 여부 배열 초기화
        visited = new boolean[words.length];
        
        // bfs를 이용한 최소 변환 횟수 계산
        return bfs(begin, target, words);
    }
    
    // 최소 변환 횟수 계산을 위한 너비 우선 탐색
    private int bfs(String begin, String target, String[] words) {
        queue.add(new Node(begin, 0)); // 시작 노드 추가
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.getWord().equals(target)) { // target으로 변환 완료
                return current.getConversionCount(); // 변환 횟수 반환
            }
            
            for (int i = 0; i < words.length; i++) {
                String nextWord = words[i];
                
                // 방문한 적이 없고 현재 단어에서 변환 가능한 단어를 갖는 노드 방문
                if (!visited[i] && canConvert(current.getWord(), nextWord)) {
                    queue.add(new Node(nextWord, current.getConversionCount() + 1));
                    visited[i] = true; // 방문 처리
                }
            }
        }
        
        return 0; // 이 시점에는 변환 불가 -> 0 반환
    }
    
    // currentWord를 nextWord로 변환할 수 있는지 확인
    private boolean canConvert(String currentWord, String nextWord) {
        int differentAlphabetCount = 0; // 서로 다른 알파벳 개수
        
        for (int i = 0; i < currentWord.length(); i++) {
            if (differentAlphabetCount >= 2) { // 2개 이상 다르다면 변환 불가 -> false 반환
                return false;
            }
            
            if (currentWord.charAt(i) != nextWord.charAt(i)) {
                differentAlphabetCount++;
            }
        }
        
        return differentAlphabetCount == 1;
    }
}