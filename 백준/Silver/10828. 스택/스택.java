import java.util.*;
import java.io.*;

class Main {
    static final int ERROR = -1;
    static final int TRUE = 1;
    static final int FALSE = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Deque<Integer> stack = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine()); // 명령의 수

        // N개의 명령 수행
        while (N-- > 0) {
            String command = br.readLine();

            switch (command) {
                case "pop": {
                    System.out.println(stack.isEmpty() ? ERROR : stack.pop());
                    break;
                }
                case "size": {
                    System.out.println(stack.size());
                    break;
                }
                case "empty": {
                    System.out.println(stack.isEmpty() ? TRUE : FALSE);
                    break;
                }
                case "top": {
                    System.out.println(stack.isEmpty() ? ERROR : stack.peek());
                    break;
                }
                default: { // push
                    st = new StringTokenizer(command);
                    st.nextToken();
                    stack.push(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }
}