import java.util.*;

class Food implements Comparable<Food> {
    private final int scoville;
    
    Food(int scoville) {
        this.scoville = scoville;
    }
    
    public int getScoville() {
        return scoville;
    }
    
    @Override
    public int compareTo(Food other) {
        return Integer.compare(this.scoville, other.getScoville());
    }
}

class Solution {
    public int solution(int[] scovilles, int K) {
        PriorityQueue<Food> foods = new PriorityQueue<>();
        int answer = 0;
        
        for (int scoville : scovilles) {
            foods.offer(new Food(scoville));
        }
        
        while (foods.size() >= 2) {
            Food food1 = foods.poll();
            Food food2 = foods.poll();
            
            if (food1.getScoville() >= K) {
                return answer;
            }
            
            foods.offer(new Food(food1.getScoville() + 2 * food2.getScoville()));
            answer++;
        }
        
        if (foods.poll().getScoville() >= K) {
            return answer;
        } else {
            return -1;
        }
    }
}