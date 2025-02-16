import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    
    // 인덱스: 학생 번호
    // 해당 인덱스의 값: 여분 체육복 소지 여부
    private boolean[] isUniformProvider;
    
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n; // 체육 수업을 들을 수 있는 학생의 수
        isUniformProvider = new boolean[n + 2];
        
        // 오름차순 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 여분 체육복을 가지고 있으면서 체육복을 도난당한 학생 번호 제외
        int[] newLost = removeCommonStudents(lost, reserve);
        int[] newReserve = removeCommonStudents(reserve, lost);
        
        // 여분 체육복을 가진 학생 처리
        for (int student : newReserve) {
            isUniformProvider[student] = true;
        }
        
        for (int student : newLost) {
            if (isUniformProvider[student - 1]) { // 왼쪽 학생에게 빌릴 수 있는 경우
                isUniformProvider[student - 1] = false; 
            } else if (isUniformProvider[student + 1]) { // 오른쪽 학생에게 빌릴 수 있는 경우
                isUniformProvider[student + 1] = false;
            } else { // 양쪽 학생 모두에게 빌릴 수 없는 경우
                answer--;
            }
        }
        
        return answer;
    }
    
    // 두 배열의 교집합(여분 체육복을 가지고 있으면서 체육복을 도난당한 학생) 제외
    private int[] removeCommonStudents(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int student : arr1) {
            set1.add(student);
        }
        
        for (int student : arr2) {
            set2.add(student);
        }
        
        set1.removeAll(set2);
        
        return set1.stream().mapToInt(i -> i).toArray();
    }
    
}