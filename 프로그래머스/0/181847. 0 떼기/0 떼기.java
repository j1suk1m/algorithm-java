class Solution {
    public String solution(String n_str) {
        int index = 0;
        char[] array = n_str.toCharArray();
        
        while (array[index] == '0') {
            index++;
        }
        
        if (index != 0) {
            return n_str.substring(index);
        }

        return n_str;
    }
}