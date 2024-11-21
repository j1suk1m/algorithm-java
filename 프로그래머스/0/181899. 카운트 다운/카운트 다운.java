class Solution {
    public int[] solution(int start_num, int end_num) {
        int[] answer = new int[start_num - end_num + 1];
        
        for (int num = start_num; num >= end_num; num--) {
            answer[start_num - num] = num;
        }
        
        return answer;
    }
}