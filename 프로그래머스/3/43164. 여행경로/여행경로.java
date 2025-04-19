import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] used; // 티켓 사용 여부
    private final List<String> routes = new ArrayList<>(); // 가능한 모든 경로
    private static final String ROUTE_DELIMITER = " "; // 하나의 경로 내에서 공항 사이를 연결할 구분자
     
    public String[] solution(String[][] tickets) {
        used = new boolean[tickets.length];
        
        // 백트래킹을 이용해 가능한 모든 경로 계산
        backtrack(tickets, 0, "ICN", new StringBuilder("ICN"));
        
        // 사전 순서로 정렬
        routes.sort(String::compareTo);

        // 첫번째 경로를 구분자로 구분해 반환
        return routes.get(0).split(ROUTE_DELIMITER);
    }
    
    private void backtrack(String[][] tickets, int usedTicketCount, String start, StringBuilder route) {
        
        // 모든 티켓을 사용한 경우
        if (usedTicketCount == tickets.length) {
            routes.add(route.toString()); // 지금까지의 경로 저장
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            
            // 사용한 적이 없고 출발지가 start와 동일한 티켓 사용
            if (!used[i] && tickets[i][0].equals(start)) {
                used[i] = true; // 티켓 사용 처리
                
                int beforeLength = route.length(); 
                route = route.append(ROUTE_DELIMITER).append(tickets[i][1]); // 경로에 도착지 추가
                
                backtrack(tickets, usedTicketCount + 1, tickets[i][1], route);
                
                route.setLength(beforeLength); // 경로 복원
                
                used[i] = false; // 티켓 사용 여부 복원
            }
            
        }
        
    }
}