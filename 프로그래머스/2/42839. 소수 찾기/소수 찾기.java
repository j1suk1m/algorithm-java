import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<Integer> uniqueNumbers = new HashSet<>();
    private boolean[] visited;
    
    public int solution(String numbers) {
        int answer = 0;
        int totalLength = numbers.length();
        
        visited = new boolean[totalLength];
        
        // 조합할 수 있는 모든 숫자 생성하기
        generateUniqueNumbers(numbers);
        
        // 소수의 개수 세기
        for (int uniqueNumber : uniqueNumbers) {
            if (isPrimeNumber(uniqueNumber)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 백트래킹을 이용해 최대 길이가 length인 숫자 생성하기
    private void backtrack(String numbers, StringBuilder generatedNumber, int length) {
        if (generatedNumber.length() == length) {
            uniqueNumbers.add(Integer.parseInt(generatedNumber.toString()));
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                generatedNumber.append(numbers.charAt(i));
                backtrack(numbers, generatedNumber, length);
                generatedNumber.deleteCharAt(generatedNumber.length() - 1);
                visited[i] = false;
            }    
        }
    }
    
    // 전체 length에 대해 백트래킹 수행하기
    private void generateUniqueNumbers(String numbers) {
        for (int length = 1; length <= numbers.length(); length++) {
            backtrack(numbers, new StringBuilder(), length);
        }
    }
    
    // 소수 판별하기
    private boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        
        for (int divisor = 2; divisor <= (int) Math.sqrt(number); divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }
        
        return true;
    }
}