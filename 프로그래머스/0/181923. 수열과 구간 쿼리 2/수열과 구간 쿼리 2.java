class Solution {
    static final int MAX = (int) 1e6 + 1;
    
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int target = query[2];
            int nextLarger = MAX; // [start, end]에서 target보다 큰 수의 최솟값
            
            for (int j = start; j <= end; j++) {
                int curr = arr[j];
                
                if (curr > target && curr < nextLarger) {
                    nextLarger = curr;
                }
            }
            
            answer[i] = nextLarger < MAX ? nextLarger : -1;
        }
        
        return answer;
    }
}