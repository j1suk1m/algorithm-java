class Solution {
    private int N;
    private int M;
    private int[][] expandedLock;
    
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        
        // 1. lock 확장
        expandLock(lock);
        
        for (int direction = 0; direction < 4; direction++) {
            // 2. key 회전
            key = rotateKey(key);
            
            for (int i = 0; i < 2 * N; i++) {
                for (int j = 0; j < 2 * N; j++) {
                    // 3. key로 lock 해제 시도
                    insertKey(key, i, j);
                    
                    // 4. 해제 가능하면 true 반환
                    if (fits()) { 
                        return true;
                    }
                    
                    // 5. lock에서 key 제거
                    removeKey(key, i, j);
                }
            }
        }

        // 6. 해제할 방법이 없으면 false 반환
        return false;
    }
    
    // key가 lock 바깥부터 겹칠 수 있도록 lock을 3배 확장
    // M <= N이므로 3배 확장 시 key는 전체 범위를 벗어나지 않음을 보장
    private void expandLock(int[][] lock) {
        expandedLock = new int[3 * N][3 * N];
        
        // 확장된 lock 중앙에 기존 lock의 값 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                expandedLock[i + N][j + N] = lock[i][j];
            }
        }
    }
    
    // originalKey를 시계 방향으로 90도 회전
    private int[][] rotateKey(int[][] originalKey) {
        int[][] newKey = new int[M][M];
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                newKey[j][M - 1 - i] = originalKey[i][j]; // (i, j) --90도 회전--> (j, M - 1 - i)
            }
        }
        
        return newKey;
    }
    
    // expandedLock과 key의 좌표 덧셈
    // key의 좌측 상단이 expandedLock 영역 위의 (di, dj)에 위치한다고 가정
    private void insertKey(int[][] key, int di, int dj) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                expandedLock[i + di][j + dj] += key[i][j];
            }
        }
    }
    
    // key로 expandedLock을 해제할 수 있는지 확인
    // 해제할 수 있다면 기존 lock에 해당하는 중앙 부분의 모든 요소가 1이어야 함
    private boolean fits() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (expandedLock[i + N][j + N] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // expandedLock과 key의 좌표 뺄셈
    // key의 좌측 상단이 expandedLock 영역 위의 (di, dj)에 위치한다고 가정
    private void removeKey(int[][] key, int di, int dj) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                expandedLock[i + di][j + dj] -= key[i][j];
            }
        }
    }
}