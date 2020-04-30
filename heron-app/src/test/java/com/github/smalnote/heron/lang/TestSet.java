package com.github.smalnote.heron.lang;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ALL")
public class TestSet {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSuccessWhenPutNullValueToHashMap() {
        HashSet<String> hashSet = new HashSet<>();
        String nullValue = null;
        hashSet.add(nullValue);
    }


    @Test
    public void shouldThrowExcpetionWhenPutNullValueToConcurrentHashMap() {
        thrown.expect(NullPointerException.class);
        ConcurrentHashMap.KeySetView<String, Boolean> set = ConcurrentHashMap.newKeySet();
        String nullValue = null;
        set.add(nullValue);
        // get a thread save hash set by using ConcurrentHashMap.newKeySet()
    }


}
