import java.io.*;

public class Main {
    private static final String HIGH = "too high";
    private static final String LOW = "too low";
    private static final String RIGHT = "right on";
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int upperBound = 10; // 가능한 범위의 최댓값
        int lowerBound = 1; // 가능한 범위의 최솟값
        
        while (true) {
            int num = Integer.parseInt(reader.readLine());
            
            // 게임 종료 조건
            if (num == 0) {
                break;
            }
            
            String answer = reader.readLine();
            
            if (answer.equals(HIGH)) {
                upperBound = Math.min(upperBound, num - 1);
            } else if (answer.equals(LOW)) {
                lowerBound = Math.max(lowerBound, num + 1);
            } else {
                boolean isHonest = lowerBound <= num && num <= upperBound;
                System.out.println("Stan " + (isHonest ? "may be " : "is dis") + "honest");
                
                // 상한 및 하한 초기화
                upperBound = 10; 
                lowerBound = 1; 
            }
        }
    }
}