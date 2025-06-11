class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for (int[] query : queries) {
            swap(arr, query[0], query[1]);
        }
        
        return arr;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}