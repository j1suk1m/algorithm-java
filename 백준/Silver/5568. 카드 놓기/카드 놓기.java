import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
	static final Set<String> cardPermutations = new HashSet<>(); // k개의 카드를 나열해 만들 수 있는 정수의 개수
	static final List<Integer> selectedCards = new ArrayList<>(); // 선택된 카드
	static final List<Integer> cards = new ArrayList<>(); // 전체 카드
	static boolean[] selected; // 카드 선택 여부 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 전체 카드의 개수
		int k = Integer.parseInt(br.readLine()); // 선택할 카드의 개수
		
		selected = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			cards.add(Integer.parseInt(br.readLine()));
		}
		
		backtrack(n, k);
		
		System.out.println(cardPermutations.size());
	}
	
    // 백트래킹을 이용한 카드 선택
	static void backtrack(int n, int k) {
		if (selectedCards.size() == k) { // k개를 다 선택한 경우
			String result = selectedCards.stream()
										 .map(String::valueOf)
										 .collect(Collectors.joining());
			cardPermutations.add(result);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!selected[i]) {
				selected[i] = true;
				selectedCards.add(cards.get(i));
				backtrack(n, k);
				selectedCards.remove(selectedCards.size() - 1);
				selected[i] = false;
			}
		}
	}
}