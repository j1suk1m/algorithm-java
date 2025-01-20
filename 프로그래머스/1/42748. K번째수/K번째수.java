import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int answerIndex = 0;
        
        for (int[] command: commands) {
            int from = command[0];
            int to = command[1];
            int K = command[2];
            
            int[] copiedArray = Arrays.copyOfRange(array, from - 1, to);
            Arrays.sort(copiedArray);
            
            answer[answerIndex++] = copiedArray[K - 1];
        }

        return answer;
    }
}