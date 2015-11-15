package co.infinum.productive.mvp.interactors.impl;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import co.infinum.productive.models.Organization;
import co.infinum.productive.models.Project;
import co.infinum.productive.models.User;
import co.infinum.productive.mvp.interactors.CacheInteractor;

/**
 * Created by mjurinic on 27.10.15..
 */
public class CacheInteractorImpl implements CacheInteractor {

    public static final String USER = "user";

    public static final String ORGANIZATIONS = "organizations";

    public static final String PROJECTS = "projects";

    private static final int CACHE_SIZE = 3 * 1024; // in number of items not in bytes

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
    public ArrayList<Organization> getOrganizations() {
        return (ArrayList<Organization>) getCache(ORGANIZATIONS);
    }

    @Override
    public void cacheUser(User user) {
        setCache(USER, user);
    }

    @Override
    public User getUser() {
        return (User) getCache(USER);
    }

    @Override
    public void cacheProjects(ArrayList<Project> projects) {
        HashMap<Integer, String> cachedProjects = new HashMap<>();
        for (Project iteratedProject : projects) {
            cachedProjects.put(iteratedProject.getId(), iteratedProject.getName());
        }
        setCache(PROJECTS, cachedProjects);
    }

    @Override
    public HashMap<Integer, String> getProjects() {
        return (HashMap<Integer, String>) getCache(PROJECTS);
    }
}
