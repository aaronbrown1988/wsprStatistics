package net.aaronbrown.wsprstatistics.services;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by aaron on 8/04/17.
 */
@Service
public class CacheService {

    private MemcacheService syncCache;

    public CacheService() {
        this.syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
    }

    public Object getObject(String key) {
        Object object = syncCache.get(key);
        if (object == null) {
            Logger.getLogger("CacheService").info("Cache Miss for " + key);
        } else {
            Logger.getLogger("CacheService").info("Cache Hit for " + key);
        }
        return object;
    }

    public void putObject(String key, Object object) {
        syncCache.put(key, object);
    }

    public void putObjectWithExpiry(String key, Object object, Integer expiry) {
        Expiration expiration = Expiration.byDeltaSeconds(expiry);
        syncCache.put(key, object, expiration);
    }


}
