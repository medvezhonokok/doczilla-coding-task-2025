package ru.medvezhonokok.wfs.cache.repository;

public interface InMemoryCache {
    void set(final String key, final Object value, final long ttlMillis);

    Object get(final String key);

    void remove(final String key);

    int size();

    boolean containsKey(final String key);

    void clear();
}
