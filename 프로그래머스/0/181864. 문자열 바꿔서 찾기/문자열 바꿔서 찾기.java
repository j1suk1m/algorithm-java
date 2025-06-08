class Solution {
    private static final String TEMP = "*";
    
    public int solution(String myString, String pat) {
        // pat의 길이가 myString의 길이보다 항상 작거나 같다는 조건이 없음
        // myString이 pat보다 짧아서 pat이 절대 포함될 수 없는 경우에는 바로 0을 반환함
        if (myString.length() < pat.length()) {
            return 0;
        }        
        
        // pat의 길이가 myString의 길이보다 작을 확률이 높으므로 pat을 변환함
        String convertedPat = convert(pat);
        
        return myString.contains(convertedPat) ? 1 : 0;
    }
    
    // A는 B로, B는 A로 변환하는 메서드
    // 임시 문자 TEMP로 치환하는 방식을 사용해 값이 덮여 쓰이는 문제를 방지함
    private String convert(String original) {
        return original.replace("A", TEMP) // A -> *
                       .replace("B", "A") // B -> A
                       .replace(TEMP, "B"); // * -> B
    }
}