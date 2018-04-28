package me.shufork.common.service;

import java.util.List;
/**
 *
 * @param <S> source type(e.g dto )
 * @param <T> Domain Type
 */
public interface ImmutableEntityHandler<S,T> {
    T create(S source);
    List<T> create(Iterable<? extends S> source);
    T createOrGet(S source);
    List<T> createOrGet(Iterable<? extends S> source);
}
