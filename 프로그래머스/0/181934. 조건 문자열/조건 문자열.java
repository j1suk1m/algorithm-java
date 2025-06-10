class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        boolean isTrue = switch (ineq + eq) {
            case ">=" -> n >= m;
            case "<=" -> n <= m;
            case ">!" -> n > m;
            case "<!" -> n < m;
            default -> false;
        };
        
        return isTrue == true ? 1 : 0;
    }
}