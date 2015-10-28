package co.infinum.productive.mvp.interactors.impl;

import android.util.LruCache;

import java.util.ArrayList;

import javax.inject.Inject;

import co.infinum.productive.models.Organization;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.interactors.CacheInteractor;

/**
 * Created by mjurinic on 27.10.15..
 */
public class CacheInteractorImpl implements CacheInteractor {

    public static final String USER = "user";
    public static final String ORGANIZATIONS = "organizations";

    private static final int CACHE_SIZE = 2 * 1024; // in number of items not in bytes
    private volatile LruCache<String, Object> lruCache;

    @Inject
    public CacheInteractorImpl() {
        lruCache = new LruCache<>(CACHE_SIZE);
    }

    private synchronized void setCache(String key, Object object) {
        lruCache.put(key, object);
    }

    private synchronized Object getCache(String key) {
        return lruCache.get(key);
    }

    @Override
    public void cacheOrganizations(ArrayList<Organization> organizations) {
        setCache(ORGANIZATIONS, organizations);
    }

    @Override
    public Object getOrganizations() {
        return getCache(ORGANIZATIONS);
    }

    @Override
    public void cacheUser(User user) {
        setCache(USER, user);
    }

    @Override
    public Object getUser() {
        return getCache(USER);
    }
}
