import java.io.*;
import java.util.*;

class Item {
    private final int weight;
    private final int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}

class Main {
    private static int N;
    private static int K;
    private static ArrayList<Item> items = new ArrayList<>();
    private static int[][] array;

    private static int knapsack() {
        for (int order = 1; order < N + 1; order++) { // order-th item
            Item item = items.get(order);
            int weightOfItem = item.getWeight();
            int valueOfItem = item.getValue();

            for (int weight = 1; weight < K + 1; weight++) {
                if (weightOfItem <= weight) {
                    array[order][weight] = Math.max(array[order - 1][weight], array[order - 1][weight - weightOfItem] + valueOfItem);
                } else {
                    array[order][weight] = array[order - 1][weight];
                }
            }
        }

        return array[N][K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        array = new int[N + 1][K + 1];

        // padding
        items.add(new Item(0, 0));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            items.add(new Item(W, V));
        }

        System.out.println(knapsack());
    }
}