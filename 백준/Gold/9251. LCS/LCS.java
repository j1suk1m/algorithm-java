import java.io.*;

class Main {
    private static int[][] table;

    private static void LCS(String string1, String string2) {
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
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string1 = " " + br.readLine();
        String string2 = " " + br.readLine();

        table = new int[string1.length()][string2.length()];

        LCS(string1, string2);

        System.out.println(table[string1.length() - 1][string2.length() - 1]);
    }
}