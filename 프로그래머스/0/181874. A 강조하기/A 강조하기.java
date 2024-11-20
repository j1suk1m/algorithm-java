class Solution {
    public String solution(String myString) {
        StringBuilder sb = new StringBuilder();
        
        for (char alphabet : myString.toCharArray()) {
            if (alphabet == 'a') {
                sb.append("A");
            } else if (alphabet != 'A' && Character.isUpperCase(alphabet)) {
                sb.append(Character.toLowerCase(alphabet));
            } else {
                sb.append(alphabet);
            }
        }
        
        return sb.toString();
    }
}