class Solution {
    public boolean isPalindrome(int x) {
        if (x <= 0) {
            return x == 0;
        }
        
        String number = String.valueOf(x);
        int left = 0;
        int right = number.length() - 1;
            
        while (left <= right) {
            if (number.charAt(left) == number.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
            
            return true;
    }
}