package com.github.smalnote.heron.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

public class TestList {

    @Test
    public void shouldBeTrueThatArrayListIsRandomAccessible() {
        boolean implementRandomAccess = false;
        for (Class<?> anInterface : ArrayList.class.getInterfaces()) {
            if (anInterface == RandomAccess.class) {
                implementRandomAccess = true;
            }
        }
        assertTrue(implementRandomAccess);
    }

    @Test
    public void shouldBeTrhueThatVectorIsRandomAccessible() {
        boolean implementRandomAccess = false;
        for (Class<?> anInterface : Vector.class.getInterfaces()) {
            if (anInterface == RandomAccess.class) {
                implementRandomAccess = true;
            }
        }
        assertTrue(implementRandomAccess);
    }

}
