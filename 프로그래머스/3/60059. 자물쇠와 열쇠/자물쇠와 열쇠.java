class Solution {
    private int[][] expandedLock; // 확장된 자물쇠 배열
    private int[][] rotatedKey; // 회전된 열쇠 배열
    private int N; // 자물쇠 배열 초기 크기
    private int M; // 열쇠 배열 초기 크기
    
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        
        // 자물쇠 확장
        expandLock(lock);
        
        rotatedKey = key;
        
        for (int direction = 0; direction < 4; direction++) {
            // 열쇠 회전
            rotateKey(rotatedKey);
            
            for (int dx = 0; dx < N + M - 1; dx++) {
                for (int dy = 0; dy < N + M - 1; dy++) {
                    insertKey(dx, dy); // 열쇠 넣기
                    
                    if (fits()) {
                        return true;
                    } else { 
                        removeKey(dx, dy); // 열쇠 제거 -> 원상 복구
                    }
                }
            }
        }
        
        return false;
    }
    
    // N * N 크기의 lock을 (N + 2 * (M - 1)) * (N + 2 * (M - 1)) 크기로 확장
    private void expandLock(int[][] lock) {
        int expandedSize = N + 2 * (M - 1);
        expandedLock = new int[expandedSize][expandedSize];
        
        // 확장된 자물쇠 배열의 중앙에 기존 자물쇠 배열의 좌표 저장
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                expandedLock[x + M - 1][y + M - 1] = lock[x][y];
            }
        }
    }
    
    // M * M 크기의 key를 시계 방향으로 90도 회전
    private void rotateKey(int[][] key) {
        rotatedKey = new int[M][M];
        
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < M; y++) {
                rotatedKey[y][M - 1 - x] = key[x][y]; // 기존의 (x, y) 좌표가 (y, M - 1 - x)으로 이동
            }
        }
    }
    
    private void insertKey(int dx, int dy) {
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < M; y++) {
                expandedLock[x + dx][y + dy] += rotatedKey[x][y]; // 좌표 덧셈
            }
        }
    }
    
    private boolean fits() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (expandedLock[x + M - 1][y + M - 1] != 1) { // 기존 자물쇠의 모든 좌표가 1이어야 함
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private void removeKey(int dx, int dy) {
        for (int x = 0; x < M; x++) {
            for (int y = 0; y < M; y++) {
                expandedLock[x + dx][y + dy] -= rotatedKey[x][y]; // 좌표 뺄셈
            }
        }        
    }
}