class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[2]; // 짝수의 개수, 홀수의 개수
        
        for (int number : numbers) {
            answer[number % 2]++;
        }
        
        return answer;
    }
}