package com.arouter.javapoet.sigleton;

public class DoubleSigleton {
    private static DoubleSigleton ds;

    private DoubleSigleton() {
    }

    public static DoubleSigleton getDs() {
        if (ds == null) {
            synchronized (DoubleSigleton.class) {
                if (ds == null) {
                    ds = new DoubleSigleton();
                }
            }
        }
        return ds;
    }
}
