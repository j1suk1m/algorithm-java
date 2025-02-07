class Solution {
    private boolean[] visited;
    private int answer = -1;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        // 백트래킹을 이용해 탐험하기
        explore(k, dungeons, 0);
        
        return answer;
    }
    
    private void explore(int k, int[][] dungeons, int depth) {
        answer = Math.max(answer, depth); // 탐험 가능한 최대 던전 수 갱신
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) { // 탐험 가능한 경우
                visited[i] = true;
                explore(k - dungeons[i][1], dungeons, depth + 1);
                visited[i] = false;
            }
        }
    }
}