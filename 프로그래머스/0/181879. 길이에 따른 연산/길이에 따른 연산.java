import java.util.stream.IntStream;

class Solution {
    public int solution(int[] num_list) {
        if (num_list.length >= 11) {
            return IntStream.of(num_list).sum();
        } else {
            return IntStream.of(num_list).reduce(1, (num1, num2) -> num1 * num2);
        }
    }
}