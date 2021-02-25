package com.ra.collection.map;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @param <K> K
 * @param <V> V
 */
public class EasyMap<K,V> {


    /**
     *
     * @param map {@link Map<K,V>} The Collection needs to be sorted
     * @param cmp {@link Comparable<? super K>}The Sorting Strategy
     * @param <K> K
     * @param <V> V
     * @return {@link Map<K,V>} The Sorted Map
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
     * @param map {@link Map<K,V>} The Collection needs to be sorted
     * @param cmp {@link Comparable<? super V>} The Sorting Strategy
     * @param <K> K
     * @param <V> V
     * @return {@link Map<K,V>} The Sorted Map
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
     * @param map {@link Map<K,V>}The Collection needs to be sorted
     * @param <K> K
     * @param <V> V
     * @return  {@link Map<K,V>} The Sorted Map in this case a TreeMap , keys
     * in which are sorted using their natural order
     */
    public static <K,V> Map<K,V> sortByKeyUsingTreeMap(Map<K,V> map)
    {
        return new TreeMap<>(map);
    }


    /**
     *
     * @param map {@link Map<K,V>} The Collection needs to be sorted
     * @param cmp {@link Comparable<? super K>} The Sorting Strategy
     * @param <K> Key Type
     * @param <V> Value Type
     * @return {@link Map<K,V>} The Sorted Map
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
     * @param map {@link Map<K,V>} The Collection needs to be sorted
     * @param cmp {@link Comparable<? super V>} The Sorting Strategy
     * @param <K> K
     * @param <V> V
     * @return {@link Map<K,V>} The Sorted Map
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
     * @param map  {@link Map<K,V>}
     * @param value V
     * @param <K> K
     * @param <V> V
     * @return <K> The found key for the source value
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
     * @param map {@link Map<K,V>}
     * @param <K> K Type
     * @param <V> V Type
     * @return <K , V extends {@link Comparable<V>}>
     */
    public static <K,V extends Comparable<V>> V getMaxUsingStream(Map<K,V> map)
    {
        Optional<Map.Entry<K,V>>
                maxEntry = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        return maxEntry.get().getValue();
    }


    /**
     *
     * @param map {@link Map<K,V>}
     * @param <K> K Type
     * @param <V> V Type
     * @return {@link String}
     */
    public static <K,V> String convertToString(Map<K,V> map)
    {
        return map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", " , "{", "}"));
    }

    /**
     *
     * @param map {@Link Map<K,V>}
     * @param <K> K Type
     * @param <V> V Type
     * @return {@link Set<V>}
     */
    public static <K,V> Set<V> convertToSet(Map<K,V> map)
    {
        return new HashSet<>(map.values());
    }


    /**
     *
     * @param map {@link Map<K,V>}
     * @param <K> K Type
     * @param <V> V Type
     * @return {@link List<V>}
     */
    public static <K,V> List<V> convertToList(Map<K,V> map)
    {
        return new ArrayList<>(map.values());
    }


    /**
     *
     * @param firstMap {@link Map<K,V>}
     * @param secondMap  {@link Map<K,V>}
     * @param <K> K Type
     * @param <V> V Type
     * @return {@link Map<K,V>}
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
     * @param list {@link List<V>}
     * @param <V> Type V
     * @return {@link Map<String,V>}
     */
    public static <V> Map<String , V> createFromList(List<V> list)
    {
        return list
                .stream()
                .collect(HashMap::new , (m, c) -> m.put(UUID.randomUUID().toString() , c) , (m , u) -> {});
    }

}
