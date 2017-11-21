package de.comparus.opensource.longmap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LongMapImplTest {

    private LongMapImpl<String> map;

    @Before
    public void setUp() throws Exception {
        map = new LongMapImpl<>();
    }

    @org.junit.Test
    public void put() throws Exception {
        map.put(1L, "1");
        assertTrue(map.containsKey(1L));
        assertTrue(map.containsValue("1"));
    }

    @org.junit.Test
    public void putWrongParam() throws Exception {
        //map.put(852L , 852L); - compile exception (the second param must be of type String)
    }

    @org.junit.Test
    public void putValueToExistingKey() throws Exception {
        map.put(1001L, "1001");
        assertEquals("1001", map.get(1001L));
        map.put(1001L, "20002");
        assertEquals("20002", map.get(1001L));
    }

    @org.junit.Test
    public void putPairWithNullValue() throws Exception {
        map.put(2L, null);
        assertTrue(map.containsKey(2L));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @org.junit.Test
    public void putNegativeLongException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Value of the key cannot be negative!");
        map.put(-1L, "Value 1");
    }


    @org.junit.Test
    public void get() throws Exception {
        map.put(3L, "3");
        String v = map.get(3L);
        assertEquals("3", v);
    }

    @org.junit.Test
    public void getNullIfNoSuchElement() throws Exception {
        map.put(4L, "4");
        String v = map.get(44L);
        assertEquals(null, v);
    }

    @org.junit.Test
    public void remove() throws Exception {
        assertTrue(map.size() == 0);
        map.put(5L, "5");
        assertTrue(map.size() == 1);
        String v = map.remove(5L);
        assertTrue(map.size() == 0);
        assertEquals("5", v);
    }

    @org.junit.Test
    public void removeReturnNullIfNoSuchElement() throws Exception {
        assertTrue(map.size() == 0);
        map.put(5L, "5");
        assertTrue(map.size() == 1);
        String v = map.remove(55L);
        assertTrue(map.size() == 1);
        assertEquals(null, v);
    }

    @org.junit.Test
    public void isEmpty() throws Exception {
        assertTrue(map.isEmpty());
    }

    @org.junit.Test
    public void isNotEmpty() throws Exception {
        map.put(6L, "6");
        assertFalse(map.isEmpty());
    }

    @org.junit.Test
    public void containsKey() throws Exception {
        map.put(7L, "7");
        assertTrue(map.containsKey(7L));
    }


    @org.junit.Test
    public void notContainsKey() throws Exception {
        map.put(8L, "8");
        assertFalse(map.containsKey(88L));
    }

    @org.junit.Test
    public void containsValue() throws Exception {
        map.put(9L, "9");
        assertTrue(map.containsValue("9"));
    }

    @org.junit.Test
    public void notContainsValue() throws Exception {
        map.put(10L, "10");
        assertFalse(map.containsValue("0"));
    }

    @org.junit.Test
    public void containsComplexValue() throws Exception {
        LongMap<List<Long>> arrayListLongMap = new LongMapImpl<>();
        List<Long> list = new ArrayList<>();
        list.add(123L);
        list.add(125L);
        arrayListLongMap.put(147L, list);

        assertTrue(arrayListLongMap.containsValue(list));

        List<Long> list2 = new ArrayList<>();
        list2.add(123L);
        list2.add(125L);

        assertTrue(arrayListLongMap.containsValue(list2));

        List<Long> list3 = new ArrayList<>();

        assertFalse(arrayListLongMap.containsValue(list3));
    }

    @org.junit.Test
    public void keys() throws Exception {
        map.put(100L, "100");
        map.put(200L, "200");
        map.put(300L, "300");
        long[] keys = map.keys();
        assertTrue(keys.length == 3);
        assertEquals(100L, keys[0]);
        assertEquals(200L, keys[1]);
        assertEquals(300L, keys[2]);
    }

    @org.junit.Test
    public void keys_ZeroLengthArrayIfMapIsEmpty() throws Exception {
        long[] keys = map.keys();
        assertTrue(keys.length == 0);
    }


    @org.junit.Test
    public void values() throws Exception {
        map.put(100L, "100");
        map.put(200L, "200");
        map.put(300L, "300");
        String[] values = map.values();
        assertTrue(values.length == 3);
        assertEquals("100", values[0]);
        assertEquals("200", values[1]);
        assertEquals("300", values[2]);
    }

    @Ignore
    @org.junit.Test
    /*
     * Problem's here! Cannot get an empty array of generic type
     * (only of type Object) without explicit declaring the
     * generic type in LongMapImpl constructor or invoking
     * (at least once) of the method 'put'
     */
    public void values_getEmptyArray() throws Exception {
        String[] values = (String[]) map.values();// 'Object[] values' is working
        assertTrue(values.length == 0);
    }


    @org.junit.Test
    public void size() throws Exception {
        assertTrue(map.size() == 0L);
        map.put(125L, "125");
        map.put(126L, "126");
        assertTrue(map.size() == 2L);
        map.clear();
        assertTrue(map.size() == 0L);
    }

    @org.junit.Test
    public void clear() throws Exception {
        map.put(85L, "85");
        map.put(86L, "86");
        assertTrue(map.size() == 2L);

        map.clear();
        assertTrue(map.size() == 0L);
    }

}