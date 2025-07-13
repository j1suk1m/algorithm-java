import java.util.*;
import java.io.*;

class Main {
    private static final char YUTNORI = 'Y'; // 윷놀이
    private static final char PICTURE_MATCHING = 'F'; // 같은 그림 찾기
    private static final char ONE_CARD = 'O'; // 원카드
    private static final HashMap<Character, Integer> gameTypeToPlayerCount = new HashMap<>();
    private static final HashSet<String> players = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        // 플레이 신청 횟수 N과 플레이할 게임의 종류 gameType 입력
        int N = Integer.parseInt(tokenizer.nextToken()); // String -> int
        char gameType = tokenizer.nextToken().charAt(0); // String -> char

        // 게임 타입에 따라 필요한 인원수를 gameTypeToPlayerCount 맵에 등록
        initGameTypeToPlayerCount();
        
        // 플레이를 신청한 사람들의 이름을 players 집합에 등록
        initPlayers(reader, N);

        // 최대 플레이 횟수 계산
        System.out.println(players.size() / (gameTypeToPlayerCount.get(gameType) - 1));
    }

    // 게임 타입에 따라 필요한 인원수를 gameTypeToPlayerCount 맵에 등록
    // key: 게임 타입을 나타내는 문자
    // value: 해당 게임의 인원
    private static void initGameTypeToPlayerCount() {
        gameTypeToPlayerCount.put(YUTNORI, 2); // 윷놀이 - 2인
        gameTypeToPlayerCount.put(PICTURE_MATCHING, 3); // 같은 그림 찾기 - 3인
        gameTypeToPlayerCount.put(ONE_CARD, 4); // 원카드 - 4인
    }

    // 플레이를 신청한 사람들의 이름을 players 집합에 등록
    private static void initPlayers(BufferedReader reader, int N) throws IOException {
        for (int i = 0; i < N; i++) {
            String player = reader.readLine();
            players.add(player);
        }
    }
}