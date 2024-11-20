class Solution {
    public int solution(double flo) {
        String[] numbers = Double.toString(flo).split("\\.");
        int answer = Integer.parseInt(numbers[0]);
        
        return answer;
    }
}