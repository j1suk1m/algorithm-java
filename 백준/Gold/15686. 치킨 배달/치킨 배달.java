import java.util.*;
import java.io.*;

class Main {
    static int N; // 도시의 크기, [2, 50]
    static int M; // 선택할 치킨집의 개수, [1, 13]
    static int[][] city; // 도시
    static final int EMPTY = 0; // 빈칸
    static final int HOUSE = 1; // 집
    static final int CHICKEN_STORE = 2; // 치킨집
    static final List<Point> houses = new ArrayList<>();
    static final List<Point> chickenStores = new ArrayList<>();
    static int[][] distances; // distances[houseIdx][chickenStoreIdx] = 거리
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];

        // 도시 정보 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());

                if (city[i][j] == HOUSE) houses.add(new Point(i, j));
                if (city[i][j] == CHICKEN_STORE) chickenStores.add(new Point(i, j));
            }
        }

        distances = new int[houses.size()][chickenStores.size()];

        // 각 집과 각 치킨집 사이의 거리 계산
        calculateDistances();
        
        // M개의 치킨집을 선택한 뒤 도시의 치킨 거리 갱신
        calculateCombinations(0, new ArrayList<>());

        System.out.println(answer);
        
        br.close();
    }

    // 각 집과 각 치킨집 사이의 거리 계산
    static void calculateDistances() {
        for (int i = 0; i < houses.size(); i++) {
            Point house = houses.get(i);
            
            for (int j = 0; j < chickenStores.size(); j++) {
                Point chickenStore = chickenStores.get(j);
                distances[i][j] = Math.abs(house.x - chickenStore.x) + Math.abs(house.y - chickenStore.y);
            }
        }
    }

    // M개의 치킨집을 선택한 뒤 도시의 치킨 거리 갱신
    static void calculateCombinations(int startIdx, List<Integer> selectedIndices) {
        if (selectedIndices.size() == M) {
            int cityChickenDistance = calculateCityChickenDistances(selectedIndices);
            answer = Math.min(answer, cityChickenDistance);
            return;
        }

        for (int i = startIdx; i < chickenStores.size(); i++) {
            selectedIndices.add(i);
            calculateCombinations(i + 1, selectedIndices);
            selectedIndices.remove(selectedIndices.size() - 1);
        }
    }

    // 도시의 치킨 거리 계산
    static int calculateCityChickenDistances(List<Integer> selectedIndices) {
        int cityChickenDistance = 0;

        for (int i = 0; i < houses.size(); i++) {
            int chickenDistance = Integer.MAX_VALUE;

            for (int chickenStoreIdx : selectedIndices) {
                chickenDistance = Math.min(chickenDistance, distances[i][chickenStoreIdx]);
            }

            cityChickenDistance += chickenDistance;

            if (cityChickenDistance >= answer) return answer;
        }

        return cityChickenDistance;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}