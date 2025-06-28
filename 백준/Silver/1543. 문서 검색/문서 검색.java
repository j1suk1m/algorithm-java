import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String document = reader.readLine();
        String target = reader.readLine();

        int targetLength = target.length();
        int index = 0;
        int answer = 0;

        while (true) {
            index = document.indexOf(target, index);

            // document에서 target을 찾을 수 없거나
            // 탐색 시작 인덱스가 document 길이를 넘는 경우
            if (index == -1) {  
                break;
            }

            answer++;
            index += targetLength;
        }

        System.out.println(answer);
    }
}