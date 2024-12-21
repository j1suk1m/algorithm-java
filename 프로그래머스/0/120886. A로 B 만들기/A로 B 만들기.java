import java.util.*;

class Solution {
    public int solution(String before, String after) {
        char[] beforeArray = before.toCharArray();
        char[] afterArray = after.toCharArray();
        
        // 정렬 결과가 동일하다면 before의 순서를 바꿔서 after를 만들 수 있음
        Arrays.sort(beforeArray);
        Arrays.sort(afterArray);
        
        String sortedBefore = new String(beforeArray);
        String sortedAfter = new String(afterArray);
        
        return sortedBefore.equals(sortedAfter) ? 1 : 0;
    }
}