class Solution {
    public String solution(String my_string) {
        String regex = "[aeiou]";
        
        return my_string.replaceAll(regex, "");
    }
}