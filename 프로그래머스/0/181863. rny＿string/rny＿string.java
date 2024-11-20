class Solution {
    public String solution(String rny_string) {
        StringBuilder sb = new StringBuilder();
        char[] array = rny_string.toCharArray();
        
        for (char alphabet : array) {
            if (alphabet == 'm') {
                sb.append("rn");
            } else {
                sb.append(alphabet);
            }
        }
        
        return sb.toString();
    }
}