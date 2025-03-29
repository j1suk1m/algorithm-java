class Solution {
    private int[] nums; // 입력 배열 arr의 피연산자들을 순서대로 저장한다.
    private String[] operators; // 입력 배열 arr의 연산자들을 순서대로 저장한다.
    private int[][] minTable; // i번째 피연산자부터 j번째 피연산자까지의 연산 결과의 최솟값을 저장한다.
    private int[][] maxTable; // i번째 피연산자부터 j번째 피연산자까지의 연산 결과의 최댓값을 저장한다.
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    
    public int solution(String arr[]) {
        
        // 피연산자의 개수를 저장한다.
        int numCount = (arr.length + 1) / 2;
        
        // 배열들을 초기화한다.
        init(arr, numCount);

        // 총 n개의 피연산자가 있을 때, 1개부터 n-1개까지 하나의 괄호로 묶을 수 있다.
        for (int chunkSize = 1; chunkSize < numCount; chunkSize++) {
            
            // 하나의 괄호 묶음 안에서 시작 인덱스 start와 끝 인덱스 end를 증가시킨다.
            for (int start = 0; start < numCount - chunkSize; start++) {
                int end = start + chunkSize; 
                
                // 현재 괄호 묶음을 current를 기준으로 두 개의 작은 괄호 묶음으로 본다.
                // current를 start부터 end - 1까지 증가시킨다.
                // 현재 괄호 묶음의 연산 결과의 최댓값과 최솟값을 계산해 저장한다.
                for (int current = start; current < end; current++) {
                    String operator = operators[current];
                    
                    if (operator.equals(PLUS)) {
                        minTable[start][end] = Math.min(
                            minTable[start][end], 
                            minTable[start][current] + minTable[current + 1][end]
                        );
                        maxTable[start][end] = Math.max(
                            maxTable[start][end],
                            maxTable[start][current] + maxTable[current + 1][end]
                        );
                    } else {
                        minTable[start][end] = Math.min(
                            minTable[start][end], 
                            minTable[start][current] - maxTable[current + 1][end]
                        );
                        maxTable[start][end] = Math.max(
                            maxTable[start][end],
                            maxTable[start][current] - minTable[current + 1][end]
                        );                        
                    }
                }
            } 
        }
        
        // 전체 피연산자에 대해 연산 결과의 최댓값을 반환한다.
        return maxTable[0][numCount - 1];
    }
    
    private void init(String[] arr, int numCount) {
        nums = new int[numCount];
        operators = new String[arr.length - numCount];

        // 연산자와 피연산자를 분리해 각 배열에 저장한다.
        parseNumsAndOperators(arr);
        
        // 메모이제이션을 위한 배열을 초기화한다.
        initTables(numCount);
    }

    private void parseNumsAndOperators(String[] arr) {
        int numsIndex = 0;
        int operatorsIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[numsIndex++] = Integer.parseInt(arr[i]);
            } else {
                operators[operatorsIndex++] = arr[i];
            }
        }
    }

    private void initTables(int numCount) {
        minTable = new int[numCount][numCount];
        maxTable = new int[numCount][numCount];

        for (int i = 0; i < numCount; i++) {
            for (int j = 0; j < numCount; j++) {
                if (i == j) {
                    minTable[i][j] = nums[i];
                    maxTable[i][j] = nums[i];
                } else {
                    minTable[i][j] = Integer.MAX_VALUE;
                    maxTable[i][j] = Integer.MIN_VALUE;
                }
            }
        }
    }
}