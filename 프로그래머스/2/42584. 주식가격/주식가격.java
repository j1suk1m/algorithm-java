import java.util.Stack;

class Stock {
    private final int price;
    private final int second;
    
    public Stock(int price, int second) {
        this.price = price;
        this.second = second;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getSecond() {
        return second;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        Stack<Stock> stack = new Stack<>();
        int[] answer = new int[prices.length];
        
        for (int i = prices.length - 2; i >= 0; i--) {
            int currentPrice = prices[i];
            int currentSecond = 1;
            
            while (!stack.isEmpty() && stack.peek().getPrice() >= currentPrice) {
                currentSecond += stack.pop().getSecond();
            }
            
            stack.push(new Stock(currentPrice, currentSecond));
            answer[i] = currentSecond;
            
        }
        
        return answer;
    }
}