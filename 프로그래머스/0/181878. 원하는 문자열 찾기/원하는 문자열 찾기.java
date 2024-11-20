class Solution {
    public int solution(String myString, String pat) {
        if (myString.length() < pat.length()) {
            return 0;
        } else if (myString.toLowerCase().indexOf(pat.toLowerCase()) > -1) {
            return 1;
        } else {
            return 0;
        }
    }
}