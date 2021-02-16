package com.ra.collection.map;

import com.sun.security.auth.UnixNumericUserPrincipal;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    /**
     *
     * @param map The Collection needs to be sorted
     * @param <K> key Type
     * @param <V> Value Type
     * @return The Sorted Map in this case a TreeMap , keys in which are sorted using their natural order
     */
    public static <K,V> Map<K,V> sortByKeyUsingTreeMap(Map<K,V> map)
    {
        return new TreeMap<>(map);
    }


    /**
     *
     * @param map he Collection needs to be sorted
     * @param cmp The Sorting Strategy
     * @param <K> Key Type
     * @param <V> Value Type
     * @return The Sorted Map
     */
    public static <K,V> Map<K,V> sortByKeyUsingArrayList(Map<K,V> map , Comparator<? super K> cmp)
    {
        List<K> mediatorList = new ArrayList<>(map.keySet());
        mediatorList.sort(cmp);
        return mediatorList.stream().collect(Collectors.toMap(
                k -> k ,
                map::get));
    }


    /**
     *
     * @param map The Collection needs to be sorted
     * @param cmp The Sorting Strategy
     * @param <K> Key Type
     * @param <V> Value Type
     * @return The Sorted Map
     */
    public static <K,V> Map<K,V> sortByValueUsingArrayList(Map<K,V> map , Comparator<? super V> cmp)
    {
        List<V> mediatorList = new ArrayList<>(map.values());
        mediatorList.sort(cmp);
        return mediatorList.stream().collect(Collectors.toMap(
                v ->  EasyMap.getKeyFromValue(map , v),
                v -> v ));

    }

    /**
     *
     * @param map  The Source Map
     * @param value The Source Value
     * @param <K> Key Type
     * @param <V> Value Type
     * @return The fond key for the source value
     */

    public static <K,V> K getKeyFromValue(Map<K,V> map, V value)
    {
        return
                map
                        .entrySet()
                        .stream()
                        .filter(entry -> value.equals(entry.getValue()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .get();
    }


    /**
     *
     * @param map The Source Map
     * @param <K> K Type
     * @param <V> V Type
     * @return Max V Type value
     */
    public static <K,V extends Comparable<V>> V getMaxUsingStream(Map<K,V> map)
    {
        Optional<Map.Entry<K,V>>
                maxEntry = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        return maxEntry.get().getValue();
    }


    /**
     *
     * @param map The Source Map
     * @param <K> K Type
     * @param <V> V Type
     * @return String
     */
    public static <K,V> String convertToString(Map<K,V> map)
    {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", " , "{", "}"));
    }


    /**
     *
     * @param map The Source Map
     * @param <K> K Type
     * @param <V> V Type
     * @return return Set<V>
     */
    public static <K,V> Set<V> convertToSet(Map<K,V> map)
    {
        return new HashSet<>(map.values());
    }


    /**
     *
     * @param map The Source Map
     * @param <K> K Type
     * @param <V> V Type
     * @return return List<V>
     */
    public static <K,V> List<V> convertToList(Map<K,V> map)
    {
        return new ArrayList<>(map.values());
    }


    /**
     *
     * @param firstMap First Map
     * @param secondMap  Second Map
     * @param <K> K Type
     * @param <V> V Type
     * @return Merged Map
     */
    public static <K,V> Map<K,V> merge(Map<K,V> firstMap , Map<K,V> secondMap)
    {
        return Stream
                .of(firstMap , secondMap)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue));
    }


    /**
     *
     * @param list List of V Type
     * @param <V> Type V
     * @return Map<String , V>
     */
    public static <V> Map<String , V> createFromList(List<V> list)
    {
        return list
                .stream()
                .collect(HashMap::new , (m, c) -> m.put(UUID.randomUUID().toString() , c) , (m , u) -> {});
    }


}
