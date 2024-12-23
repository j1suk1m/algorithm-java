import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] counts = new int[2]; // (문자열 압축 후) 0의 개수, 1의 개수
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String input = reader.readLine();
        
        // 한 번 이상 연속으로 등장하는 숫자를 하나로 압축
        String compressedInput = input.replaceAll("0+", "0").replaceAll("1+", "1");

        for (int i = 0; i < compressedInput.length(); i++) {
            counts[Character.getNumericValue(compressedInput.charAt(i))]++;
        }
        
        System.out.println(Math.min(counts[0], counts[1]));
    }
}