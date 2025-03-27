class Solution {
    public int solution(int[] money) {
        
        // 집의 개수를 저장한다.
        int houseCount = money.length;
        
        // 인덱스 i에 대해, i번째 집에 도착했을 때 얻을 수 있는 돈의 최댓값을 저장한다.
        // 첫번째 행에는 인덱스 범위가 0 ~ (houseCount - 2)인 집에 대한 결과를 저장한다.
        // 두번째 행에는 인덱스 범위가 1 ~ (houseCount - 1)인 집에 대한 결과를 저장한다.
        int[][] table = new int[2][houseCount];
        
        // 기저 조건을 명시한다.
        // 첫번째 행에는 첫번째 집에서 도둑질을 하는 경우의 결과를 저장한다.
        // 두번째 행에는 첫번째 집에서 도둑질을 하지 않는 경우의 결과를 저장한다.
        table[0][0] = money[0];
        table[0][1] = money[0];
        table[1][0] = 0;
        table[1][1] = money[1];
        
        // 다이나믹 프로그래밍을 통해 얻을 수 있는 돈의 최댓값을 계산한다.
        for (int i = 2; i < houseCount; i++) {
            for (int j = 0; j < 2; j++) {
                table[j][i] = Math.max(table[j][i - 2] + money[i], table[j][i - 1]);
            } 
        }
        
        // 최댓값을 구해 반환한다.
        return Math.max(table[0][houseCount - 2], table[1][houseCount - 1]);
        
    }
}