import java.io.*;

class Main {
    private static int[][][] table;

    // 세 개의 문자가 모두 일치하는지 확인
    private static boolean isMatched(char char1, char char2, char char3) {
        return char1 == char2 && char2 == char3;
    }

    // 세 개의 문자열에 대한 LCS
    private static void LCS(String string1, String string2, String string3) {
        int length1 = string1.length();
        int length2 = string2.length();
        int length3 = string3.length();

        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                for (int k = 1; k < length3; k++) {
                    if (isMatched(string1.charAt(i), string2.charAt(j), string3.charAt(k))) {
                        table[i][j][k] = table[i - 1][j - 1][k - 1] + 1;
                    } else {
                        table[i][j][k] = Math.max(table[i - 1][j][k], Math.max(table[i][j - 1][k], table[i][j][k - 1]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string1 = " " + br.readLine();
        String string2 = " " + br.readLine();
        String string3 = " " + br.readLine();

        table = new int[string1.length()][string2.length()][string3.length()];

        LCS(string1, string2, string3);

        System.out.println(table[string1.length() - 1][string2.length() - 1][string3.length() - 1]);
    }
}