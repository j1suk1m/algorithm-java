import java.util.*;

class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        List<Integer> answer = new ArrayList<>();
        int start = 0, end = 0, interval = 0;
        
        if (n == 1) {
            start = 0;
            end = slicer[1];
            interval = 1;
        } else if (n == 2) {
            start = slicer[0];
            end = num_list.length - 1;
            interval = 1;
        } else if (n == 3) {
            start = slicer[0];
            end = slicer[1];
            interval = 1;
        } else if (n == 4) {
            start = slicer[0];
            end = slicer[1];
            interval = slicer[2];            
        }
        
        for (int i = start; i <= end; i += interval) {
            answer.add(num_list[i]);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}