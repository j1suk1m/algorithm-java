import java.util.*;

class Solution {
    public int solution(String input) {
        String[] numbers = input.split("[A-Za-z]+");

        return Arrays.stream(numbers)
                     .mapToInt(number -> {
                         try {
                             return Integer.parseInt(number);
                         } catch (NumberFormatException e) {
                             return 0;
                         }
                     })
                     .sum();
    }
}