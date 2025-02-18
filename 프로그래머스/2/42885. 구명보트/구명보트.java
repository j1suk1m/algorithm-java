import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[] people, int limit) {    
        
        // 체중에 대해 오름차순 정렬
        Arrays.sort(people);
        
        int answer = 0; // 구명보트의 개수
        int minWeight = 0; // 구명보트에 타지 않은 사람 중 가장 가벼운 사람의 인덱스
        int maxWeight = people.length - 1; // 구명보트에 타지 않은 사람 중 가장 무거운 사람의 인덱스
        
        while (minWeight <= maxWeight) {
            
            // 가장 가벼운 사람 + 가장 무거운 사람을 함께 태우는 조합 시도
            if (people[minWeight] + people[maxWeight] <= limit) {
                minWeight++;
                maxWeight--;
                answer++;
            } else { // 가장 무거운 사람 혼자 태우기
                maxWeight--;
                answer++;
            }
        }
        
        return answer;
    }
}