package lld.elastic_search;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Search2 implements Cache {
    private final Map<Long, String> sentenceStore;
    private final Map<String, Set<Long>> map;
    private final AtomicLong id;

    // search1 is stable thread-safe implementation
    // but if I run a server which accepts request
    // I will see following challenges
    // first a single sentence is duplicated
    // for each word in the sentence
    // this will bloat the memory
    // we need to maintain the sentence store
    // and attach the sentenceId and word to sentence mapping
    // to generate sentence id we use atomic long
    public Search2() {
        map = new ConcurrentHashMap<>();
        sentenceStore = new ConcurrentHashMap<>();
        id = new AtomicLong(1);
    }

    @Override
    public void add(String sentence) {
        Long sentenceId = id.getAndIncrement();
        sentenceStore.put(sentenceId, sentence);
        String[] words = tokenize(sentence);
        // here also we can reduce keywords
        // by using set of words
        Set<String> uniqueWords = Set.of(words);
        for (String word : uniqueWords) {
            // computeIfAbsent is atomic at bucket level
            // use Compare and Swap logic
            // ConcurrentHashMap.newKeySet()  create a Set backed by
            // concurrent hash map
            // So when adding element to this hashset will also
            // be thread-safe
            map.computeIfAbsent(word, k -> ConcurrentHashMap.newKeySet()).add(sentenceId);
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
        Set<Long> ids = map.get(normalised);
        if (ids == null) {
            return List.of();
        }
        return ids.stream().map(sentenceStore::get).filter(Objects::nonNull).toList();
    }
}
