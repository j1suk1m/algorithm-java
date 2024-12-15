import java.io.*;

class Main {
    private static int[][] table;
    private static final StringBuilder reversedLCS = new StringBuilder();

    private static int getLengthOfLCS(String string1, String string2) {
        int row = string1.length();
        int col = string2.length();

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (string1.charAt(i) == string2.charAt(j)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        return table[row - 1][col - 1];
    }

    private static String getLCS(String string1, String string2) {
        int i = string1.length() - 1;
        int j = string2.length() - 1;

        while (i > 0 && j > 0) {
            if (table[i][j] == table[i - 1][j]) {
                i--;
            } else if (table[i][j] == table[i][j - 1]) {
                j--;
            } else {
                reversedLCS.append(string1.charAt(i));
                i--;
                j--;
            }
        }

        return reversedLCS.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string1 = " " + br.readLine();
        String string2 = " " + br.readLine();

        table = new int[string1.length()][string2.length()];

        int lengthOfLCS = getLengthOfLCS(string1, string2);
        System.out.println(lengthOfLCS);

        if (lengthOfLCS > 0) {
            System.out.println(getLCS(string1, string2));
        }
    }
}