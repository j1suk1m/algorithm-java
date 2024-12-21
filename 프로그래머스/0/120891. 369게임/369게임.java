class Solution {
    public int solution(int order) {
        return Integer.toString(order).replaceAll("[^369]", "").length();
    }
}