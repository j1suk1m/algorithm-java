class Solution {
    private static final char TOGGLE = '1';
    private int mode = 0;
    private final StringBuilder ret = new StringBuilder();
    
    public String solution(String code) {        
        for (int idx = 0; idx < code.length(); idx++) {
            char current = code.charAt(idx);
            
            if (current == TOGGLE) { 
                toggle(); // mode 반전 // 0 -> 1 // 1 -> 0
            } else if (canAddLast(idx)) { 
                addLast(current); // ret 끝에 현재 문자 추가
            }
        }
        
        String answer = ret.toString();
        
        return answer.isEmpty() ? "EMPTY" : answer;
    }
    
    private void toggle() {
        mode = 1 - mode;
    }
    
    private boolean isEven(int num) {
        return num % 2 == 0;
    } 
    
    private boolean isOdd(int num) {
        return num % 2 == 1;
    }
       
    private boolean canAddLast(int idx) {
        return (mode == 0 && isEven(idx)) || (mode == 1 && isOdd(idx));
    }
    
    private void addLast(char ch) {
        ret.append(ch);
    }
}