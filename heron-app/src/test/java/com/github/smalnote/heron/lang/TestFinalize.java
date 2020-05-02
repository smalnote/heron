package com.github.smalnote.heron.lang;

import org.junit.Test;

@SuppressWarnings("ALL")
public class TestFinalize {

    @Test
    public void shouldExecuteFinalizedBeforeGC() {
        ObjectWithFinalize o = new ObjectWithFinalize();
        System.out.println(o);
        o = null;
        System.gc();
    }

}

class ObjectWithFinalize {

    // will be invoked when no reference and after System.gc()
    public void finalize() {
        System.out.println(" finalize() have been invoked ");
    }

}
