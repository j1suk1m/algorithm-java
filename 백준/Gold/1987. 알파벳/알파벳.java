import java.util.*;
import java.io.*;

class Main {
    private static int R;
    private static int C;
    private static char[][] board;
    private static final boolean[] included = new boolean[26]; // 이미 포함된 알파벳인지 확인
    private static boolean[][] visited; // 이미 방문한 칸인지 확인
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int answer; // 방문할 수 있는 최대 칸의 개수

    // 대문자 알파벳을 인덱스로 변환
    private static int getIndexFromAlphabet(char alphabet) {
        return (int)alphabet - (int)'A';
    }

    private static void dfs(int x, int y, int depth) {
        answer = Math.max(answer, depth);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
                continue;
            } else if (visited[nx][ny]) {
                continue;
            } else if (included[getIndexFromAlphabet(board[nx][ny])]) {
                continue;
            } else {
                included[getIndexFromAlphabet(board[nx][ny])] = true;
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                included[getIndexFromAlphabet(board[nx][ny])] = false;
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        // 보드 저장
        for (int i = 0; i < R; i++) {
            String[] row = br.readLine().split("");

            for (int j = 0; j < C; j++) {
                board[i][j] = row[j].charAt(0);
            }
        }

        // 초기 방문 처리
        included[getIndexFromAlphabet(board[0][0])] = true;
        visited[0][0] = true;

        dfs(0, 0, 1);

        System.out.println(answer);
    }
}