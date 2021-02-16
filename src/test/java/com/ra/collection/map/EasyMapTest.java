package com.ra.collection.map;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class EasyMapTest {

    @Test
    public void testSortingGivenHashMapByKeyUsingStreamAPI_thenAssertTrue()
    {
        //Given
        Map<String, String> map = new HashMap<>();
        map.put("j", "java");
        map.put("c", "c++");
        map.put("p", "python");
        map.put("n", "node");

        // Sort map using EasyMap
        Map<String , String> sortedMap = EasyMap.sortByKey(map , String::compareToIgnoreCase);

        //Assert
        Map.Entry<String , String> expectedItem = Map.entry("c", "c++");
        Assertions.assertEquals(4, sortedMap.entrySet().size());
        Assertions.assertEquals( expectedItem , sortedMap.entrySet().toArray()[0]);
    }


    @Test
    public void testSortingGivenHashMapByValueUsingStreamAPI_thenAssertTrue()
    {
        //Given
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Moscow");
        map.put(1, "Saint Petersburg");
        map.put(2, "Novosibirsk");
        map.put(3, "Barnaul");
        map.put(4, "Irkutsk");

        // Sort map using EasyMap
        Map<Integer , String> sortedMap = EasyMap.sortByValue(map , String::compareToIgnoreCase);

        //Assert
        Map.Entry<Integer , String> expectedItem = Map.entry(3, "Barnaul");
        Assertions.assertEquals(5, sortedMap.entrySet().size());
        Assertions.assertEquals( expectedItem , sortedMap.entrySet().toArray()[0]);


    }

    @Test
    public void testSortingGivenHashMapByIdUsingTreeMap_thenAssertTrue()
    {
        Map<String, String> map = new HashMap<>();
        map.put("j", "java");
        map.put("c", "c++");
        map.put("p", "python");
        map.put("n", "node");

        // Sort map using EasyMap
        Map<String , String> sortedMap = EasyMap.sortByKeyUsingTreeMap(map);

        //Assert
        Map.Entry<String , String> expectedItem = Map.entry("c", "c++");
        Assertions.assertEquals(4, sortedMap.entrySet().size());
        Assertions.assertEquals( expectedItem , sortedMap.entrySet().toArray()[0]);
    }


    @Test
    void testGetMaxUsingStream_thenAssertTrue() {

        //Given
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Moscow");
        map.put(1, "Saint Petersburg");
        map.put(2, "Novosibirsk");
        map.put(3, "Barnaul");
        map.put(4, "Irkutsk");

        // Sort map using EasyMap
        String maxValueByLength = EasyMap.getMaxUsingStream(map);
        Assertions.assertEquals("Saint Petersburg" , maxValueByLength);
    }

    @Test
    void testConvertToString()
    {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Moscow");
        map.put(1, "Saint Petersburg");
        map.put(2, "Novosibirsk");
        map.put(3, "Barnaul");
        map.put(4, "Irkutsk");

        Assertions.assertEquals(
                "{0=Moscow, 1=Saint Petersburg, 2=Novosibirsk, 3=Barnaul, 4=Irkutsk}" ,
                EasyMap.convertToString(map));
    }



    @Test
    void testCreateFromList_thenAssertTrue()
    {
        List<String> list = Arrays.asList("Russia" , "USA" , "China" , "UK" , "France");
        Map<String , String> mapFromList = EasyMap.createFromList(list);
        Assertions.assertTrue(mapFromList.containsValue("Russia"));

    }



}
