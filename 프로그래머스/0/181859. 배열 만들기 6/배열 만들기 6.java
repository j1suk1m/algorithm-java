import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> stk = new ArrayList<>();
        int i = 0;
        
        while (i < arr.length) {
            if (stk.isEmpty() || stk.get(stk.size() - 1) != arr[i]) {
                stk.add(arr[i]);
            } else {
                stk.remove(stk.size() - 1);
            } 
            
            i++;
        }
        
        if (stk.isEmpty()) return new int[] {-1};
        
        return stk.stream().mapToInt(n -> n).toArray();
    }
}