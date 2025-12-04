package ru.medvezhonokok.wfs.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.medvezhonokok.wfs.cache.impl.InMemoryCacheImpl;

@Configuration
public class InMemoryCacheConfiguration {

    @Value("${weather.cache.ttl.minutes}")
    private int cacheTtlMinutes;

    @Bean
    public InMemoryCacheImpl inMemoryCache() {
        return new InMemoryCacheImpl();
    }

    public long getCacheTtlMillis() {
        return cacheTtlMinutes * 60 * 1000L;
    }
}