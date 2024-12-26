import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

class Song implements Comparable<Song> {
    private final int id;
    private final int playCount;

    Song(int id, int playCount) {
        this.id = id;
        this.playCount = playCount;
    }

    public int getId() {
        return id;
    }

    public int getPlayCount() {
        return playCount;
    }

    @Override
    public int compareTo(Song other) {
        int result = Integer.compare(other.getPlayCount(), this.playCount);
        
        if (result != 0) {
            return result;
        }
        
        return Integer.compare(this.id, other.getId());
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> genreToPlayCounts = new HashMap<>();
        Map<String, PriorityQueue<Song>> genreToSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];

            genreToPlayCounts.put(genre, genreToPlayCounts.getOrDefault(genre, 0) + playCount);

            PriorityQueue<Song> songs = genreToSongs.getOrDefault(genre, new PriorityQueue<>());
            songs.add(new Song(i, playCount));
            genreToSongs.put(genre, songs);
        }

        List<Map.Entry<String, Integer>> sortedGenreToPlayCounts = new ArrayList<>(genreToPlayCounts.entrySet());
        sortedGenreToPlayCounts.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedGenreToPlayCounts) {
            String genre = entry.getKey();
            PriorityQueue<Song> songs = genreToSongs.get(genre);
            int size = songs.size();

            for (int i = 0; i < Math.min(2, size); i++) {
                Song song = songs.poll();
                answer.add(song.getId());
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}