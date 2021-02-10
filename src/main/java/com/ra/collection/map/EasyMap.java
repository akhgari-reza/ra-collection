package com.ra.collection.map;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @param <K>
 * @param <V>
 */
public class EasyMap<K,V> {


    /**
     *
     * @param map The Collection needs to be sorted
     * @param cmp The Sorting Strategy
     * @param <K> key Type
     * @param <V> Value Type
     * @return The Sorted Map
     */
    public static <K,V> Map<K,V> sortByKey(Map<K,V> map ,  Comparator<? super K> cmp)
    {
        return
                map.entrySet()
                        .stream()
                        .sorted(Map.Entry.<K,V>comparingByKey(cmp))
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey ,
                                        Map.Entry::getValue ,
                                        (oldValue , newValue) -> oldValue , LinkedHashMap::new));
    }

    /**
     *
     * @param map The Collection needs to be sorted
     * @param cmp The Sorting Strategy
     * @param <K> key Type
     * @param <V> Value Type
     * @return The Sorted Map
     */

    public static <K,V> Map<K,V> sortByValue(Map<K,V> map ,  Comparator<? super V> cmp)
    {
        return
                map.entrySet()
                        .stream()
                        .sorted(Map.Entry.<K,V>comparingByValue(cmp))
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey ,
                                        Map.Entry::getValue ,
                                        (oldValue , newValue) -> oldValue , LinkedHashMap::new));
    }

}
