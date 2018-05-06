package me.shufork.common.data;

import java.util.List;

/**
 *
 * @param <S> source type(e.g dto )
 * @param <K> PK type
 */
public interface FastImporter<S,K>  {
    K insertOrUpdate(S source);
    List<K> insertOrUpdate(Iterable<? extends S> source);
}
