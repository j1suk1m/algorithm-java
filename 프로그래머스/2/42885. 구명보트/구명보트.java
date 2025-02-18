import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] people, int limit) {    
        
        // 체중에 대해 정렬
        Arrays.sort(people);
        
        int answer = 0;
        int minWeight = 0;
        int maxWeight = people.length - 1;
        
        while (minWeight <= maxWeight) {
            if (people[minWeight] + people[maxWeight] <= limit) {
                minWeight++;
            } 
            
            maxWeight--;
            answer++;
        }
        
        return answer;
    }
}