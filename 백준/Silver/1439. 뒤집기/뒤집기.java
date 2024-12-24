import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] counts = new int[2]; // 연속된 0 묶음의 개수, 연속된 1 묶음의 개수
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        // 연속된 묶음의 개수 저장
        counts[0] = (int) Arrays.stream(input.split("1+")).filter(s -> !s.isEmpty()).count();
        counts[1] = (int) Arrays.stream(input.split("0+")).filter(s -> !s.isEmpty()).count();

        System.out.println(Math.min(counts[0], counts[1]));
    }
}