class Solution {
    private int[] counts = new int[31]; // 인덱스: 문자열의 길이, 값: 그룹의 크기
    
    public int solution(String[] strArr) {
        count(strArr);
        return findMaxCount();
    }
    
    private void count(String[] strArr) {
        for (String str : strArr) {
            counts[str.length()]++;
        }
    }
    
    private int findMaxCount() {
        int maxCount = 0;
        
        for (int count : counts) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        return maxCount;
    }
}