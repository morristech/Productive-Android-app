package co.infinum.productive.mvp.interactors.impl;

import android.util.LruCache;

import javax.inject.Inject;

import co.infinum.productive.mvp.interactors.CacheInteractor;

/**
 * Created by mjurinic on 27.10.15..
 */
public class CacheInteractorImpl implements CacheInteractor {

    private static final int CACHE_SIZE = 2 * 1024; // in number of items not in bytes
    private volatile LruCache<String, Object> lruCache;

    @Inject
    public CacheInteractorImpl() {
        lruCache = new LruCache<>(CACHE_SIZE);
    }

    @Override
    public synchronized void setCache(String key, Object object) {
        lruCache.put(key, object);
    }

    @Override
    public synchronized Object getCache(String key) {
        return lruCache.get(key);
    }
}
