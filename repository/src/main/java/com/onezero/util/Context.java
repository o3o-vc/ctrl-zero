package com.onezero.util;

import java.util.HashMap;
import java.util.Map;

public interface Context {
    ThreadLocal<Map<String, Object>> paramsLocal = ThreadLocal.withInitial(() -> new HashMap<>());

    static <T> T get(String key, Class<T> clazz) {
        var val = paramsLocal.get().get(key);
        return clazz.cast(val);
    }

    static <T> T put(String key, T t) {
        paramsLocal.get().put(key, t);
        return t;
    }

    static void remove(String key) {
        paramsLocal.get().remove(key);
    }

}
