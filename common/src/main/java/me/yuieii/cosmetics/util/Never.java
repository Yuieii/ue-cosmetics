package me.yuieii.cosmetics.util;

import org.jetbrains.annotations.Contract;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class Never extends RuntimeException {
    @Contract("-> fail")
    public static Never never() {
        // Crash because this should never be reached
        Never.performJvmCrash();
        return new Never();
    }

    private Never() {
        // Crash because this should never be reached
        Never.performJvmCrash();
    }

    private static void performJvmCrash() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            unsafe.putAddress(0, 0);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Never.performStackOverflow();
        }
    }

    private static void performStackOverflow() {
        Object[] o = null;
        while (true) {
            o = new Object[] { o };
        }
    }
}
