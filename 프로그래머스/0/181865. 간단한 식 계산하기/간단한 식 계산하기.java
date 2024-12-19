class Solution {
    public int solution(String binomial) {
        String[] input = binomial.split(" ");
        
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[2]);
        char op = input[1].charAt(0);
        
        if (op == '+') {
           return a + b;
        } else if (op == '-') {
            return a - b;
        }
        
        return a * b;
    }
}