import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
    private final int startTime;
    private final int endTime;

    Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Meeting other) {
        // 회의 종료 시간에 대한 오름차순 정렬
        int result = Integer.compare(this.endTime, other.getEndTime());
        
        if (result != 0) {
            return result;
        }
        
        // 회의 시작 시간에 대한 오름차순 정렬
        return Integer.compare(this.startTime, other.getStartTime()); 
    }
}

class Main {
    private static final List<Meeting> meetings = new ArrayList<>();
    
    private static void init(int N, BufferedReader reader) throws IOException {
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

            // 회의 시작 시간, 종료 시간 입력
            int startTime = Integer.parseInt(tokenizer.nextToken());
            int endTime = Integer.parseInt(tokenizer.nextToken());

            // 회의 객체 생성 및 회의 리스트에 추가
            meetings.add(new Meeting(startTime, endTime));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = 1; // 가능한 최대 회의 개수
        int N = Integer.parseInt(reader.readLine()); // 회의 개수

        // 회의 리스트 초기화
        init(N, reader);

        // 회의 리스트 정렬
        meetings.sort(null);

        Meeting firstMeeting = meetings.get(0);
        int previousEndTime = firstMeeting.getEndTime();

        // 그리디 알고리즘
        for (int i = 1; i < N; i++) {
            Meeting currentMeeting = meetings.get(i);
            int currentStartTime = currentMeeting.getStartTime();

            if (previousEndTime <= currentStartTime) {
                previousEndTime = currentMeeting.getEndTime();
                meetingCount++;
            }
        }

        System.out.println(meetingCount);
    }
}