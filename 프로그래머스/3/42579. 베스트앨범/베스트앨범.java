import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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
        if (this.playCount > other.getPlayCount()) {
            return -1;
        } else if (this.playCount < other.getPlayCount()) {
            return 1;
        } else {
            if (this.id < other.getId()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> genreToPlayCounts = new HashMap<>();
        Map<String, List<Song>> genreToSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];
            Song song = new Song(i, playCount);

            genreToPlayCounts.put(genre, genreToPlayCounts.getOrDefault(genre, 0) + playCount);
            List<Song> songs = genreToSongs.getOrDefault(genre, new ArrayList<>());
            songs.add(song);
            genreToSongs.put(genre, songs);
        }

        List<Map.Entry<String, Integer>> sortedGenreToPlayCounts = genreToPlayCounts.entrySet()
                                                                                    .stream()
                                                                                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                                                                                    .collect(Collectors.toList());

        for (Map.Entry<String, List<Song>> entry : genreToSongs.entrySet()) {
            List<Song> songs = entry.getValue();
            songs.sort(null);
        }

        for (Map.Entry<String, Integer> entry : sortedGenreToPlayCounts) {
            String genre = entry.getKey();
            List<Song> songs = genreToSongs.get(genre);

            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                answer.add(songs.get(i).getId());
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}