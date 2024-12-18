class Solution {
    private static boolean isOdd(int[] arr) {
        return arr.length % 2 == 1;
    }
    
    public int[] solution(int[] arr, int n) {
        int startIndex = isOdd(arr) ? 0 : 1;
        
        for (int i = startIndex; i < arr.length; i += 2) {
            arr[i] += n;
        }
        
        return arr;
    }
}