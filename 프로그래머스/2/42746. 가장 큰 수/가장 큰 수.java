import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        
        // int 배열을 String 배열로 변환
        String[] numberStrings = Arrays.stream(numbers).mapToObj(Integer::toString).toArray(String[]::new);
        
        // 모든 요소가 "0"인 경우 "0"을 반환
        if (Arrays.stream(numberStrings).allMatch(n -> n.equals("0"))) {
            return "0";
        }
        
        // 두 숫자 문자열을 연결했을 때 더 큰 숫자를 만들 수 있는 순서대로 정렬
        Arrays.sort(numberStrings, (numberString1, numberString2) -> {
            int combinedResult1 = Integer.parseInt(numberString1 + numberString2);
            int combinedResult2 = Integer.parseInt(numberString2 + numberString1);
            
            return Integer.compare(combinedResult2, combinedResult1);
        });
        
        // String 배열을 하나의 문자열로 연결해 반환
        return String.join("", numberStrings);
        
    }
}