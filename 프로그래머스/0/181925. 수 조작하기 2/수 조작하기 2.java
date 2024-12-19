class Solution {
    public String solution(int[] numLog) {
        StringBuilder answer = new StringBuilder();
        
        for (int i = 1; i < numLog.length; i++) {
            int current = numLog[i];
            int previous = numLog[i - 1];
            
            if (current - previous == 1) {
                answer.append("w");
            } else if (current - previous == -1) {
                answer.append("s");
            } else if (current - previous == 10) {
                answer.append("d");
            } else {
                answer.append("a");
            }
        }
        
        return answer.toString();
    }
}