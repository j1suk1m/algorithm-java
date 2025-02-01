import java.util.ArrayList;
import java.util.List;

class Student {
    private final int id; // 번호
    private final int[] responses; // 풀이한 답변
    private int score = 0; // 점수
    
    public Student(int id, int[] responses) {
        this.id = id;
        this.responses = responses;
    }
    
    // 한 문제 채점
    public void calculateScore(int problemNumber, int answer) {
        if (responses[problemNumber % responses.length] == answer) {
            score++;
        }
    } 
    
    public int getId() {
        return id;
    }
    
    public int getScore() {
        return score;
    }
}

class Solution {
    List<Integer> answer = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    public int[] solution(int[] answers) {
        initStudents(); // 학생 리스트 초기화
        
        for (int problemNumber = 0; problemNumber < answers.length; problemNumber++) {
            for (Student student : students) {
                student.calculateScore(problemNumber, answers[problemNumber]); // 채점
            }
        }
        
        findStudentsWithTopScore(); // 가장 많은 문제를 맞힌 학생 조회
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    // 학생 리스트 초기화
    private void initStudents() {
        students.add(new Student(1, new int[] {1, 2, 3, 4, 5}));
        students.add(new Student(2, new int[] {2, 1, 2, 3, 2, 4, 2, 5}));
        students.add(new Student(3, new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}));
    }
    
    // 가장 높은 점수 조회
    private int findTopScore() {
        int topScore = students.stream()
                               .mapToInt(Student::getScore)
                               .max()
                               .orElse(0); // 최댓값이 없을 경우 사용할 기본값
        
        return topScore;
    }
    
    // 가장 많은 문제를 맞힌 학생 조회
    private void findStudentsWithTopScore() {
        int topScore = findTopScore();
        
        students.stream()
                .filter(student -> student.getScore() == topScore)
                .map(Student::getId)
                .forEach(answer::add); // answer 리스트에 추가
    }
}