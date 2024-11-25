import java.util.*;

class Solution {
    private static HashMap<String, Integer> nameToIndex = new HashMap<>();
    private static int[][] graph;
    private static HashMap<String, Integer> nameToNextMonthGiftNumber = new HashMap<>();
    private static int[] giftIndices;

    private static void setUp(String[] friends) {
        for (int index = 0; index < friends.length; index++) {
            nameToIndex.put(friends[index], index); 
        }

        graph = new int[friends.length][friends.length];
        giftIndices = new int[friends.length];
    }

    // 주고받은 선물 개수와 선물 지수 계산
    private static void calculateGiftInfo(String[] gifts) {
        for (String gift : gifts) {
            String from = gift.split(" ")[0];
            String to = gift.split(" ")[1];
            int fromIndex = nameToIndex.get(from);
            int toIndex = nameToIndex.get(to);
            graph[fromIndex][toIndex] += 1; // graph[행][열] = 행(준 사람) -> 열(받은 사람) 선물 개수
            giftIndices[fromIndex] += 1;
            giftIndices[toIndex] -= 1;
        }
    }

    // 주고받은 선물 개수와 선물 지수 비교
    private static void compareGiftInfo(String[] friends) {
        for (int i = 0; i < friends.length - 1; i++) {
            String friend1 = friends[i];
            int friend1Index = nameToIndex.get(friend1);

            for (int j = i + 1; j < friends.length; j++) {
                String friend2 = friends[j];
                int friend2Index = nameToIndex.get(friend2);

                if (graph[friend1Index][friend2Index] > graph[friend2Index][friend1Index]) {
                    nameToNextMonthGiftNumber.put(friend1, nameToNextMonthGiftNumber.getOrDefault(friend1, 0) + 1);
                } else if (graph[friend1Index][friend2Index] < graph[friend2Index][friend1Index]) {
                    nameToNextMonthGiftNumber.put(friend2, nameToNextMonthGiftNumber.getOrDefault(friend2, 0) + 1);
                } else if (giftIndices[friend1Index] > giftIndices[friend2Index]) {
                    nameToNextMonthGiftNumber.put(friend1, nameToNextMonthGiftNumber.getOrDefault(friend1, 0) + 1);
                } else if (giftIndices[friend1Index] < giftIndices[friend2Index]) {
                    nameToNextMonthGiftNumber.put(friend2, nameToNextMonthGiftNumber.getOrDefault(friend2, 0) + 1);
                }
            }
        }
    }

    // 내달에 가장 많은 선물을 받는 사람이 받을 선물의 개수 계산
    private static int calculateMaxGiftNumber() {
        int max = 0;

        for (String name : nameToNextMonthGiftNumber.keySet()) {
            if (max < nameToNextMonthGiftNumber.get(name)) {
                max = nameToNextMonthGiftNumber.get(name);
            }
        }

        return max;
    }

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        setUp(friends);
        calculateGiftInfo(gifts);
        compareGiftInfo(friends);
        answer = calculateMaxGiftNumber();

        return answer;
    }
}
