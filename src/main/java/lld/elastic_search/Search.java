package lld.elastic_search;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Search implements Cache {
    private final Map<String, Set<String>> map;

    public Search() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void add(String sentence) {
        String[] words = tokenize(sentence);
        for (String word : words) {
            // computeIfAbsent is atomic at bucket level
            // use Compare and Swap logic
            // ConcurrentHashMap.newKeySet()  create a Set backed by
            // concurrent hash map
            // So when adding element to this hashset will also
            // be thread-safe
            map.computeIfAbsent(word, k -> ConcurrentHashMap.newKeySet()).add(sentence);
        }
    }

    private String[] tokenize(String sentence) {
        return sentence
                .trim() // remove whitespace on edges
                .toLowerCase() // normalise
                .replaceAll("[^a-z0-9 ]", "") // remove all punctuation
                .split("\\s+"); // split on whitespaces
    }

    @Override
    public List<String> find(String word) {
        String normalised = word.trim().toLowerCase();
        return List.copyOf(map.getOrDefault(normalised, Set.of()));
    }
}
