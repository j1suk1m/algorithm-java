class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        boolean isTrue = false;
        
        switch (ineq + eq) {
            case ">=" -> isTrue = n >= m;
            case "<=" -> isTrue = n <= m;
            case ">!" -> isTrue = n > m;
            case "<!" -> isTrue = n < m;
        }
        
        return isTrue == true ? 1 : 0;
    }
}