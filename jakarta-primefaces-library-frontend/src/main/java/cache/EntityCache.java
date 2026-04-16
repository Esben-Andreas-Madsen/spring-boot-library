package cache;

import java.util.*;
import java.util.function.Function;

public class EntityCache<ID, T> {

    private final Map<ID, T> cache = new HashMap<>();

    public List<T> resolve(Collection<ID> ids, Function<List<ID>, List<T>> loader, Function<T, ID> idGetter) {

        List<ID> missing = ids.stream()
                .filter(id -> !cache.containsKey(id))
                .toList();

        if (!missing.isEmpty()) {
            List<T> fetched = loader.apply(missing);
            fetched.forEach(e -> cache.put(idGetter.apply(e), e));
        }

        return ids.stream()
                .map(cache::get)
                .filter(Objects::nonNull)
                .toList();
    }
}
