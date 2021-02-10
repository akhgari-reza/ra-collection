package com.ra.collection.map;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EasyMapTest {

    @Test
    public void testSortingGivenHashMapByKey_thenAssertTrue() throws Exception
    {
        // Create a Mock Object
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
}
