package app.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AvoidNulls {

    private AvoidNulls() {}

    public static <T> List<T> ensure(List<T> value) {
        return value != null ? value : Collections.emptyList();
    }

    public static <T> Set<T> ensure(Set<T> value) {
        return value != null ? value : Collections.emptySet();
    }

    public static <K, V> Map<K, V> ensure(Map<K, V> value) {
        return value != null ? value : Collections.emptyMap();
    }

}
