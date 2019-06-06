package com.arouter.javapoet.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class UseReetrantLock {
    private ReentrantLock lock = new ReentrantLock();
    private List<String> datas = new ArrayList<>();
}
