import java.util.*;
import java.io.*;

class Main {
    static int N, Q;
    static int[][] currVessel;
    static int[][] nextVessel;
    static boolean[][] visited;
    static boolean[][] connected;
    static final int[] dx = new int[] {-1, 1, 0, 0};
    static final int[] dy = new int[] {0, 0, -1, 1};
    static final List<Microbe> microbes = new ArrayList<>(Arrays.asList(new Microbe()));
    static final StringBuilder sb = new StringBuilder();
    static final int EMPTY = 0;

    static class Microbe implements Comparable<Microbe> {
        int number;
        int area;
        boolean isAlive;

        Microbe(int number, int x1, int y1, int x2, int y2) {
            int dx = x2 - x1;
            int dy = y2 - y1;

            this.number = number;
            this.area = dx * dy;
            this.isAlive = true;
        }

        // 1-based 인덱싱을 위한 dummy 생성자
        Microbe() {
            this.number = 0;
            this.area = 0;
            this.isAlive = false;
        }

        @Override
        public int compareTo(Microbe other) {
            // 살아 있는 미생물 우선
            if (this.isAlive != other.isAlive) {
                return this.isAlive ? -1 : 1;
            }

            // 영역의 넓이가 큰 미생물 우선
            if (this.area != other.area) {
                return Integer.compare(other.area, this.area);
            }

            // 미생물 번호(투입된 순서)가 작은 미생물 우선
            return Integer.compare(this.number, other.number);
        }

        void updateArea(int diff) {
            this.area += diff;
        }

        void setArea(int area) {
            this.area = area;
        }
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        currVessel = new int[N][N];

        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 이번 실험에서 새로 투입할 미생물 객체 생성
            Microbe microbe = new Microbe(i, x1, y1, x2, y2);
            microbes.add(microbe);

            // 미생물 투입
            introduce(microbe, x1, y1, x2, y2);

            // 새로운 배양 용기로 모든 미생물 이동
            move();

            // 실험 결과 기록
            record();

            // 다음 실험 진행을 위해 배양 용기 갱신
            currVessel = nextVessel;
        }

        System.out.print(sb);
    }

    static void introduce(Microbe microbe, int x1, int y1, int x2, int y2) {
        Set<Integer> overlapped = new HashSet<>();

        // 배양 용기에 새로운 미생물 투입
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                // 영역 내에 이미 다른 미생물이 존재하는 경우
                if (currVessel[x][y] != EMPTY) {
                    Microbe other = microbes.get(currVessel[x][y]);
                    other.updateArea(-1);
                    overlapped.add(other.number);
                }

                currVessel[x][y] = microbe.number;
            }
        }

        // 기존에 있던 미생물 처리
        for (int number : overlapped) {
            Microbe other = microbes.get(number);

            // 새로 투입한 미생물에 의해 넓이가 0이 되었거나 둘 이상의 영역으로 나뉠 경우 제거
            if (other.area == 0 || hasMoreThanTwoAreas(other)) {
                remove(other);
            }
        }
    }

    static void move() {
        List<Microbe> sorted = new ArrayList<>();
        nextVessel = new int[N][N];

        for (Microbe microbe : microbes) {
            if (microbe.isAlive) {
                sorted.add(microbe);
            }
        }
        
        sorted.sort(Comparator.naturalOrder());

        // 각 미생물을 새로운 배양 용기로 이동
        for (Microbe microbe : sorted) {
            // 이동시킬 수 없으면 기존 배양 용기에서 제거
            if (!canMove(microbe)) {
                remove(microbe);
            }
        }
    }

    static void record() {
        connected = new boolean[Q + 1][Q + 1];
        int result = 0;
        String delimiter = "\n";

        // 인접 관계 저장
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (nextVessel[x][y] == EMPTY) continue;

                for (int i = 0; i < dx.length; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (isOutOfRange(nx, ny)) continue;
                    if (nextVessel[nx][ny] == EMPTY) continue;
                    if (nextVessel[nx][ny] == nextVessel[x][y]) continue;

                    connected[nextVessel[x][y]][nextVessel[nx][ny]] = true;
                    connected[nextVessel[nx][ny]][nextVessel[x][y]] = true;
                }
            }
        }

        // 성과 누적
        for (int i = 1; i < Q; i++) {
            for (int j = i + 1; j <= Q; j++) {
                if (connected[i][j]) {
                    result += microbes.get(i).area * microbes.get(j).area;
                }
            }
        }

        sb.append(result).append(delimiter);
    }

    static boolean canMove(Microbe microbe) {
        boolean[][] mask = getMask(microbe);
        int row = mask.length;
        int col = mask[0].length;

        for (int x = 0; x + row <= N; x++) {
            for (int y = 0; y + col <= N; y++) {
                boolean overlaps = false;

                // 현재 좌표에 미생물을 이동시킬 경우 다른 미생물과 겹치는지 확인
                for (int i = 0; i < row && !overlaps; i++) {
                    for (int j = 0; j < col; j++) {
                        if (mask[i][j] 
                            && nextVessel[i + x][j + y] != EMPTY
                            && nextVessel[i + x][j + y] != microbe.number) {
                            overlaps = true;
                            break;
                        }
                    }
                }

                // 다른 미생물과 겹치는 경우 다음 좌표 조합으로 이동 시도
                if (overlaps) continue;

                // 새로운 배양 용기로 미생물 이동
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (mask[i][j]) {
                            nextVessel[i + x][j + y] = microbe.number;
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }

    static boolean[][] getMask(Microbe microbe) {
        int minX = N, minY = N;
        int maxX = -1, maxY = -1;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (currVessel[x][y] == microbe.number) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        int row = maxX - minX + 1;
        int col = maxY - minY + 1;

        boolean[][] mask = new boolean[row][col];

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                mask[x - minX][y - minY] = currVessel[x][y] == microbe.number;
            }
        }

        return mask;
    }

    static boolean hasMoreThanTwoAreas(Microbe microbe) {
        visited = new boolean[N][N];
        int areaCount = 0;
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y] && currVessel[x][y] == microbe.number) {
                    visit(x, y);

                    if (++areaCount >= 2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static void visit(int x, int y) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            
            for (int i = 0; i < dx.length; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (isOutOfRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (currVessel[nx][ny] != currVessel[curr.x][curr.y]) continue;

                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    static void remove(Microbe microbe) {
        microbe.isAlive = false;
        microbe.setArea(EMPTY);

        // 배양 용기에서 제거
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (currVessel[x][y] == microbe.number) {
                    currVessel[x][y] = EMPTY;
                }
            }
        }            
    }
}
