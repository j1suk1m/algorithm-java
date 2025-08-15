class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[] {sequence.length, sequence.length * 2};
        
        int left = 0;
        int right = 0;
        int sum = sequence[0]; // [left, right] 범위의 합
        
        while (left < sequence.length) {
            if (sum == k) {
                int currLength = right - left + 1;
                int minLength = getMinLength(answer);
                
                if (currLength < minLength) {
                    answer[0] = left;
                    answer[1] = right;
                } else if (currLength == minLength && left < answer[0]) {
                    answer[0] = left;
                    answer[1] = right;       
                }
                
                sum -= sequence[left];
                left++;                
            } else if (sum > k) {
                sum -= sequence[left];
                left++;
            } else {
                right++;
                
                if (right >= sequence.length) break;
                
                sum += sequence[right];
            }
        }
        
        return answer;
    }
    
    int getMinLength(int[] answer) {
        return answer[1] - answer[0] + 1;
    }
}