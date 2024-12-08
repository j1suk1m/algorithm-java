import java.util.*;
import java.io.*;

class Chapter {
    private final int days;
    private final int pages;

    Chapter(int days, int pages) {
        this.days = days;
        this.pages = pages;
    }

    public int getDays() {
        return days;
    }

    public int getPages() {
        return pages;
    }
}

class Main {
    private static int N; // remaining days
    private static int M; // the number of chapters
    private static ArrayList<Chapter> chapters = new ArrayList<>();
    private static int[][] array;

    private static int knapsack() {
        for (int order = 1; order < M + 1; order++) {
            Chapter chapter = chapters.get(order);
            int daysOfChapter = chapter.getDays();
            int pagesOfChapter = chapter.getPages();

            for (int days = 1; days < N + 1; days++) {
                if (daysOfChapter <= days) {
                    array[order][days] = Math.max(array[order - 1][days], array[order - 1][days - daysOfChapter] + pagesOfChapter);
                } else {
                    array[order][days] = array[order - 1][days];
                }
            }
        }

        return array[M][N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[M + 1][N + 1];

        // padding
        chapters.add(new Chapter(0, 0));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int days = Integer.parseInt(st.nextToken());
            int pages = Integer.parseInt(st.nextToken());

            chapters.add(new Chapter(days, pages));
        }

        System.out.println(knapsack());
    }
}