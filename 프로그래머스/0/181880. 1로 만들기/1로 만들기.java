class Solution {
    public int solution(int[] num_list) {
        int[] powerOf2 = new int[] {1, 2, 4, 8, 16, 32}; // 2의 거듭제곱 배열
        int answer = 0;

        // 각 숫자에 대해, 해당 숫자보다 작거나 같은 2의 거듭제곱 중 최댓값의 지수를 누적
        // 이는 해당 숫자를 1이 될 때까지 2로 나누는 연산의 횟수와 동일
        for (int number : num_list) {
            for (int i = 0; i < powerOf2.length - 1; i++) {
                if (powerOf2[i] <= number && number < powerOf2[i + 1]) {
                    answer += i;
                }
            }
        }

        return answer;
    }
}