package me.shufork.common.service;

import java.util.List;

/**
 *
 * @param <S> source type(e.g dto )
 * @param <T> Domain Type
 */
public interface EntityHandler<S,T> {
    T create(S source);
    List<T> create(Iterable<? extends S> source);
    T createOrUpdate(S source);
    List<T> createOrUpdate(Iterable<? extends S> source);
}
