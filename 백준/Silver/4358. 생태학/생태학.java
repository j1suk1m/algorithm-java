import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> treeCount = new TreeMap<>();
		StringBuilder answer = new StringBuilder();
		int totalCount = 0;
		
		while (true) {
			String tree = br.readLine();
			
			if (tree == null) break;
			
			if (treeCount.containsKey(tree)) {
				treeCount.put(tree, treeCount.get(tree) + 1);
			} else {
				treeCount.put(tree, 1);
			}
			
			totalCount++;
		}
		
		for (String tree : treeCount.keySet()) {
			double percentage = (double) treeCount.get(tree) / totalCount * 100;
			
			answer.append(tree)
			      .append(" ")
			      .append(String.format("%.4f", percentage))
			      .append("\n");
		}
		
		System.out.println(answer);
	}
}