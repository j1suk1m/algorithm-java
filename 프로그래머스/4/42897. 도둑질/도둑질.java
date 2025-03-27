class Solution {
    public int solution(int[] money) {
        
        // 집의 개수를 저장한다.
        int houseCount = money.length;
        
        // 인덱스 i에 대해, i번째 집에 도착했을 때 얻을 수 있는 돈의 최댓값을 저장한다.
        int[] table1 = new int[houseCount]; // 인덱스 범위가 0 ~ (houseCount - 2)인 집에 대해서 계산한다.
        int[] table2 = new int[houseCount]; // 인덱스 범위가 1 ~ (houseCount - 1)인 집에 대해서 계산한다.
        
        // 기저 조건을 명시한다.
        table1[0] = money[0]; // 첫번째 집에서 도둑질을 한다고 가정한다.
        table1[1] = money[0]; // 두번째 집에서는 도둑질을 하지 못한다.
        table2[0] = 0; // 첫번째 집에서 도둑질을 하지 않는다고 가정한다.
        table2[1] = money[1];
        
        // 다이나믹 프로그래밍을 통해 얻을 수 있는 돈의 최댓값을 계산한다.
        for (int i = 2; i < houseCount; i++) {
            table1[i] = Math.max(table1[i - 2] + money[i], table1[i - 1]);
            table2[i] = Math.max(table2[i - 2] + money[i], table2[i - 1]);    
        }
        
        return Math.max(table1[houseCount - 2], table2[houseCount - 1]);
        
    }
}