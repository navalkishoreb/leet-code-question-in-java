package lld.elastic_search;

import java.util.List;

public interface Cache {
    void add(String sentence);

    List<String> find(String word);
}
