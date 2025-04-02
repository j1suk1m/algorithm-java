import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine();
        String t = reader.readLine();

        String repeatedS = s.repeat(t.length());
        String repeatedT = t.repeat(s.length());

        System.out.println(repeatedS.equals(repeatedT) ? 1 : 0);
    }
}