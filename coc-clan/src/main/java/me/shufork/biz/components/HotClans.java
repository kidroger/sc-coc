package me.shufork.biz.components;

import me.shufork.biz.vo.ClanInfoVo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class HotClans {
    private final Map<String,ClanInfoVo> registry = new TreeMap<>();
    private final List<String> index = new LinkedList<>();
    private final int capacity;

    public HotClans(int capacity) {
        this.capacity = capacity;
    }

    public void add(ClanInfoVo val){
        final String key = val.getTag();
        synchronized (registry){
            if(registry.containsKey(key)){
                registry.replace(key,val);
            }else{
                if(index.size() >= capacity){
                    pop();
                }
                push(key,val);
            }
        }
    }
    public void addAll(Iterable<ClanInfoVo> val){
        val.forEach(o->add(o));
    }
    public List<ClanInfoVo> getHotClans(int max){
        List<ClanInfoVo> list = new LinkedList<>();
        synchronized (registry){
            final int size = Math.min(max,index.size());
            final int length = index.size();
            for(int i = 0 ;i<size ;++i ){
                list.add( registry.get(index.get(length-i-1)));
            }
        }
        return list;
    }
    private ClanInfoVo pop(){
        String indexed = index.remove(0);
        return registry.remove(indexed);
    }

    private void push(String key,ClanInfoVo val){
        index.add(key);
        registry.put(key,val);
    }
}
