package me.shufork.biz.utils;

import me.shufork.biz.domain.ClanTracking;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrackerCache<T> {

    private final Map<String,T> trackers = new ConcurrentHashMap<>();
    private final int maxSize;

    public TrackerCache(int maxSize) {
        this.maxSize = maxSize;
    }


    public void add(String key,T val){
        if(trackers.size() < maxSize){
            trackers.put(key,val);
        }
    }

    public T remove(String key){
        return trackers.remove(key);
    }
    public List<T> retrieveSome(int max){
        List<T> result = new LinkedList<>();
        synchronized (trackers){
            int got = 0;
            Iterator<Map.Entry<String,T>> itr = trackers.entrySet().iterator();
            while (itr.hasNext() && got++ < max){
                result.add(itr.next().getValue());
            }
        }
        return result;
    }

    public int size(){
        return trackers.size();
    }
    public boolean isEmpty(){
        return trackers.isEmpty();
    }
    public static class SimpleClanTracker implements ClanTracking.ClanTracker{
        private final String clan;
        private final String name;

        public SimpleClanTracker(String clan, String name) {
            this.clan = clan;
            this.name = name;
        }

        @Override
        public String getClan() {
            return clan;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
