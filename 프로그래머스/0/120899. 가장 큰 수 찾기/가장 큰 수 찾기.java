class Solution {
    public int[] solution(int[] array) {
        int maxValue = -1;
        int maxValueIndex = -1;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                maxValueIndex = i;
            }
        }
        
        return new int[] {maxValue, maxValueIndex};
    }
}