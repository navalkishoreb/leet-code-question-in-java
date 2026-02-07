package lld.elastic_search;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Search3 implements Cache {
    private final Map<Integer, String> sentenceStore;
    private final Map<String, Collection<Integer>> map;
    private final AtomicInteger id;

    // Long object were taking quite space
    // using Integer for sentence id
    // uIsng Concurrent ListQueue
    public Search3() {
        map = new ConcurrentHashMap<>();
        sentenceStore = new ConcurrentHashMap<>();
        id = new AtomicInteger(1);
    }

    @Override
    public void add(String sentence) {
        Integer sentenceId = id.getAndIncrement();
        sentenceStore.put(sentenceId, sentence);
        String[] words = tokenize(sentence);
        // here also we can reduce keywords
        // by using set of words
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        for (String word : uniqueWords) {
            // using synchronized list
            // instead of set
            // also using atomic integer instead of long
//            map.computeIfAbsent(word, k -> ConcurrentHashMap.newKeySet()).add(sentenceId);
            map.computeIfAbsent(word, k -> new ConcurrentLinkedQueue<>()).add(sentenceId);
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
        Collection<Integer> ids = map.get(normalised);
        if (ids == null) {
            return List.of();
        }
        return ids.stream().map(sentenceStore::get).filter(Objects::nonNull).toList();
    }
}
