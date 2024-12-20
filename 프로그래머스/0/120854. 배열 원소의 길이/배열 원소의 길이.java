import java.util.*;

class Solution {
    public int[] solution(String[] strList) {
        return Arrays.stream(strList).mapToInt(str -> str.length()).toArray();
    }
}