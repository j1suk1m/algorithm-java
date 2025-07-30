import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Deque<Integer> queue = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine()); // 명령의 수

        // N개의 명령 수행
        while (N-- > 0) {
            String command = br.readLine();

            switch (command) {
                case "pop": {
                    System.out.println(queue.isEmpty() ? -1 : queue.poll());
                    break;
                }
                case "size": {
                    System.out.println(queue.size());
                    break;
                }
                case "empty": {
                    System.out.println(queue.isEmpty() ? 1 : 0);
                    break;
                }
                case "front": {
                    System.out.println(queue.isEmpty() ? -1 : queue.peekFirst());
                    break;
                }
                case "back": {
                    System.out.println(queue.isEmpty() ? -1 : queue.peekLast());
                    break;
                }
                default: { // push
                    st = new StringTokenizer(command);
                    st.nextToken();
                    queue.offer(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }
}