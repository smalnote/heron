package com.github.smalnote.heron.lang;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ALL")
public class TestMap {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenPutNullKeyToHashTable() {
        thrown.expect(NullPointerException.class);
        Hashtable<String, String> stringMap = new Hashtable<>();
        String nullKey = null;
        String value = "value";
        stringMap.put(nullKey, value);
    }

    @Test
    public void shouldThrowExceptionWhenPutNullValueToHashTable() {
        thrown.expect(NullPointerException.class);
        Hashtable<String, String> stringMap = new Hashtable<>();
        String key = "key";
        String nullValue = null;
        stringMap.put(key, nullValue);
    }

    @Test
    public void shouldSuccessWhenPutNullKeyValueToHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        String nullKey = null;
        String nullValue = null;
        hashMap.put(nullKey, nullValue);
        // hashMap 计算key的哈希值时不直接用key.hashCode, 而是重新哈希
        // null -> 0
        // key.hashCode() ^ key.hashCode() >>> 16, spread higher bits to lower
        // operator >>> is unsigned right shift, it'll insert 0.
    }

    @Test
    public void shoudlThrowExceptionWhenPutNullKeyToConcurrentHashMap() {
        thrown.expect(NullPointerException.class);
        String nullKey = null;
        String value = "value";
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(nullKey, value);
    }

    @Test
    public void shouldThrowExceptionWhenPutNullValueToConcurrentHashMap() {
        thrown.expect(NullPointerException.class);
        String key = "key";
        String nullValue = null;
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(key, nullValue);
        // not allow null value to aviod ambiguites, cannot detect whether the key explicitly
        // map to null or the key itself is not mapped in concurrent context.
    }

    @Test
    public void shouldHaveInsertedOrderWhenPutToLinkedHashMap() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i);
        }
        Integer expected = 0;
        for (Integer i : linkedHashMap.keySet()) {
            Assert.assertEquals(expected++, i);
        }
    }

    @Test
    public void shoudlHaveAccessedOrderWhenGetFromLinkedHasMap() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap(16, 0.75f, true);
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i);
        }
        for (int i = 9; i >= 0; i--) {
            linkedHashMap.get(i);
        }
        Integer expected = 9;
        for (Integer i : linkedHashMap.keySet()) {
            Assert.assertEquals(expected--, i);
        }
        // loadFactor = buckets used / total buckets; maintain the get() and put() operation to complexity of O(1)
    }

}
