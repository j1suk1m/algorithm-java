class Solution {
    
    // 첫번째 행은 마지막 집을 제외하고, 두번째 행은 첫번째 집을 제외한 경우를 나타낸다.
    // 각 행에 대하여, 열에 해당하는 집에 도착한 시점에 얻을 수 있는 최대 이익을 저장한다.
    private int[][] table;
    
    public int solution(int[] money) {
        int houseCount = money.length;
        
        // 테이블을 초기화한다.
        initTable(money, houseCount);
        
        // 기저 조건을 명시한다.
        table[0][0] = money[0]; // 첫번째 집에서 도둑질한다.
        table[0][1] = money[0]; // 인접한 두번째 집에서는 도둑질하지 못한다.
        
        table[1][0] = 0; // 첫번째 집을 제외한다.
        table[1][1] = money[1];
        
        // 다이나믹 프로그래밍을 실행하며 테이블을 채운다.
        for (int i = 2; i < houseCount - 1; i++) {
            table[0][i] = Math.max(table[0][i - 1], table[0][i - 2] + money[i]);
        }
        
        for (int i = 2; i < houseCount; i++) {
            table[1][i] = Math.max(table[1][i - 1], table[1][i - 2] + money[i]);
        }
        
        return Math.max(table[0][houseCount - 2], table[1][houseCount - 1]);
    }
    
    // 테이블을 초기화한다.
    private void initTable(int[] money, int houseCount) {
        table = new int[2][houseCount];
        
        for (int i = 0; i < houseCount; i++) {
            int currentMoney = money[i];
            
            table[0][i] = currentMoney;
            table[1][i] = currentMoney;
        }
    }
    
}