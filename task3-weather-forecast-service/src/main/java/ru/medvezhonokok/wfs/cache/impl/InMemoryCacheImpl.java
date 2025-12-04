package ru.medvezhonokok.wfs.cache.impl;

import ru.medvezhonokok.wfs.cache.repository.InMemoryCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InMemoryCacheImpl implements InMemoryCache, AutoCloseable {
    private final ConcurrentHashMap<String, CacheEntry> cache;
    private final ScheduledExecutorService scheduler;

    private record CacheEntry(Object value, long expirationTime) {
        private CacheEntry(Object value, long expirationTime) {
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + expirationTime;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }

    public InMemoryCacheImpl() {
        this.cache = new ConcurrentHashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.MINUTES);
    }

    @Override
    public void set(final String key, final Object value, final long ttlMillis) {
        cache.put(key, new CacheEntry(value, ttlMillis));
    }

    @Override
    public Object get(final String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            cache.remove(key);
            return null;
        }

        return entry.value;
    }

    @Override
    public void remove(final String key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean containsKey(final String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            return false;
        }

        if (entry.isExpired()) {
            cache.remove(key);
            return false;
        }

        return true;
    }

    private void cleanup() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    @Override
    public void close() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}