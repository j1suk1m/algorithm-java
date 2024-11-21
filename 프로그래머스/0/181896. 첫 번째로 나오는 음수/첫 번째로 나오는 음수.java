class Solution {
    public int solution(int[] num_list) {
        int answer = -1;
        
        for (int idx = 0; idx < num_list.length; idx++) {
            if (num_list[idx] < 0) {
                answer = idx;
                break;
            }
        }
        
        return answer;
    }
}