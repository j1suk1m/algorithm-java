import java.util.*;
import java.io.*;

public class Main {
	static final String QUIT = "QUIT";
	static final String END = "END";
	static final String ERROR = "ERROR";
	static final long MAX = (long) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			List<String> commands = new ArrayList<>();
			boolean goesToNextMachine = false;
			
			// 명령어 입력
			while (true) {
				String command = reader.readLine();
				
				if (command.equals(QUIT)) {
					return;
				} else if (command.equals(END)) {
					break;
				} else if (command.isEmpty()) {
					goesToNextMachine = true;
					break;
				}
				
				commands.add(command);
			}
			
			if (goesToNextMachine) {
				continue;
			}
			
			int N = Integer.parseInt(reader.readLine());

			// 숫자 입력
			while (N-- > 0) {
				int number = Integer.parseInt(reader.readLine());
				String result = run(commands, number); // 각 숫자에 대해 프로그램 수행
				System.out.println(result);
			}
			
			System.out.println();
		}
	}
	
	static String run(List<String> commands, int input) {
		Deque<Integer> stack = new ArrayDeque<>(Arrays.asList(input));
		
	    for (String command : commands) {
	        try {
	            if (command.startsWith("NUM")) {
	                int num = Integer.parseInt(command.split(" ")[1]);
	                stack.push(num);
	            } else if (command.equals("POP")) {
	                stack.pop();
	            } else if (command.equals("INV")) {
	                stack.push(-stack.pop());
	            } else if (command.equals("DUP")) {
	                stack.push(stack.peek());
	            } else if (command.equals("SWP")) {
	                int a = stack.pop();
	                int b = stack.pop();
	                stack.push(a);
	                stack.push(b);
	            } else if (command.equals("ADD")) {
	                long a = stack.pop();
	                long b = stack.pop();
	                long sum = a + b;
	                if (Math.abs(sum) > MAX) return ERROR;
	                stack.push((int) sum);
	            } else if (command.equals("SUB")) {
	                int a = stack.pop();
	                int b = stack.pop();
	                long result = (long) b - a;
	                if (Math.abs(result) > MAX) return ERROR;
	                stack.push((int) result);
	            } else if (command.equals("MUL")) {
	                long a = stack.pop();
	                long b = stack.pop();
	                long result = a * b;
	                if (Math.abs(result) > MAX) return ERROR;
	                stack.push((int) result);
	            } else if (command.equals("DIV")) {
	                int a = stack.pop();
	                int b = stack.pop();
	                if (a == 0) return ERROR;
	                int sign = (a < 0 ^ b < 0) ? -1 : 1;
	                int result = Math.abs(b) / Math.abs(a);
	                stack.push(sign * result);
	            } else if (command.equals("MOD")) {
	                int a = stack.pop();
	                int b = stack.pop();
	                if (a == 0) return ERROR;
	                int result = Math.abs(b) % Math.abs(a);
	                if (b < 0) result = -result;
	                stack.push(result);
	            } else { // 그 외 명령
	                return ERROR; 
	            }
	        } catch (NoSuchElementException e) { // 스택 부족
	            return ERROR; 
	        }
	    }
		
		return stack.size() == 1 ? stack.pop().toString() : ERROR;
	}
}