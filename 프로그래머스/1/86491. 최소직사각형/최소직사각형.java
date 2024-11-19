import java.util.Arrays;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int globalLongest = 0;
        int globalShortest = 0;

        for (int i = 0; i < sizes.length; i++) {
            int localLongest = Arrays.stream(sizes[i]).max().getAsInt();
            int localShortest = Arrays.stream(sizes[i]).min().getAsInt();
            
            if (globalLongest < localLongest) {
                globalLongest = localLongest;
            }
            
            if (globalShortest < localShortest) {
                globalShortest = localShortest;
            }
        }
        
        answer = globalShortest * globalLongest;
        
        return answer;
    }
}