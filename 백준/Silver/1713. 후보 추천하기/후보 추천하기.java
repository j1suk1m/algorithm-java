import java.util.*;
import java.io.*;

public class Main {
	static int[] recommendedStudents; // 추천받은 학생들의 번호
	static int[] recommendataionCounts = new int[101]; // i번째 학생이 추천받은 횟수
	static int[] postedTimes = new int[101]; // i번째 학생의 사진이 게시된 시점
	static final Set<Integer> postedStudents = new HashSet<>(); // 사진이 게시된 학생들의 번호

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 사진틀의 개수
		int totalRecommendations = Integer.parseInt(br.readLine()); // 총 추천 횟수
		
		recommendedStudents = new int[totalRecommendations];
		st = new StringTokenizer(br.readLine());
		
		// 추천받은 학생들의 번호 저장
		for (int i = 0; i < totalRecommendations; i++) {
			recommendedStudents[i] = Integer.parseInt(st.nextToken());
		}
		
		// 사진이 게시된 시점을 저장하는 배열을 최댓값으로 초기화
		Arrays.fill(postedTimes, totalRecommendations);
		
		for (int i = 0; i < totalRecommendations; i++) {
			int student = recommendedStudents[i];
			
			if (postedStudents.contains(student)) { // 이미 사진이 게시된 학생의 경우
				recommendataionCounts[student]++; // 추천받은 횟수 증가
				continue;
			} 
			
			if (postedStudents.size() == N) { // 남아 있는 사진틀이 없는 경우
				evict(); // 조건에 따라 한 명 방출
			}
	
			recommendataionCounts[student]++; // 추천받은 횟수 증가
			postedTimes[student] = i; // 게시된 시점 저장
			postedStudents.add(student);
		}
		
		List<Integer> answer = new ArrayList<>(postedStudents);
		answer.sort(Comparator.naturalOrder());
		
		for (int student : answer) {
			System.out.print(student + " ");
		}
	}
	
    // 사진이 게시된 학생들 중 조건에 따라 한 명 방출
    // 조건 1: 추천받은 횟수가 가장 적은 학생
    // 조건 2: 조건 1을 만족하는 학생이 둘 이상일 경우, 가장 오래 전에 사진이 게시된 학생
	static void evict() {
		TreeSet<Integer> set = new TreeSet<>(Comparator.comparingInt(s -> recommendataionCounts[(int) s]) // 추천받은 횟수가 적은 순
				   							           .thenComparingInt(s -> postedTimes[(int) s])); // 오래 전에 게시된 순
		set.addAll(postedStudents);
		
		int evictedStudent = set.pollFirst();
		
		recommendataionCounts[evictedStudent] = 0; // 추천받은 횟수 초기화
		postedStudents.remove(evictedStudent);
	}
}