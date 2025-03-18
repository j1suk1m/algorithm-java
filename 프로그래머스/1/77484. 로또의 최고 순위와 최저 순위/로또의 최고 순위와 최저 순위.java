import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> lottosList = new ArrayList<>();
    private List<Integer> winNumsList = new ArrayList<>();

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        init(lottos, win_nums);

        int zeroCountOfLottos = getZeroCountOfLottos();
        int intersectionSize = getIntersectionSize();

        answer[0] = getRank(zeroCountOfLottos + intersectionSize);
        answer[1] = getRank(intersectionSize);

        return answer;
    }

    // int[] -> List<Integer> 변환
    private void init(int[] lottos,int[] win_nums) {
        int numCount = 6;
        
        for (int i = 0; i < numCount; i++) {
            lottosList.add(lottos[i]);
            winNumsList.add(win_nums[i]);
        }
    }

    // 일치 번호 개수 계산
    private int getIntersectionSize() {
        lottosList.retainAll(winNumsList);
        return lottosList.size();
    }

    // 알 수 없는 번호 개수 계산
    private int getZeroCountOfLottos() {
        int result = 0;

        for (int i = 0; i < lottosList.size(); i++) {
            if (lottosList.get(i) == 0) {
                result += 1;
            }
        }

        return result;
    }

    // 일치 번호 개수 -> 순위 변환
    private int getRank(int matchedNumCount) {
        if (matchedNumCount >= 2) {
            return 7 - matchedNumCount;
        } else {
            return 6;
        }
    }
}