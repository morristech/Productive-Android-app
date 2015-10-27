package co.infinum.productive.mvp.interactors;

/**
 * Created by mjurinic on 27.10.15..
 */
public interface CacheInteractor {

    void setCache(String key, Object object);

    Object getCache(String key);
}
