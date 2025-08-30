package com.harryplusplus.ioc;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private Map<Class<?>, Object> beans = new HashMap<>();

    public void register(Object object) {
        if (beans.containsKey(object.getClass())) {
            throw new RuntimeException("Bean \"" + object.getClass().getName() + "\" already exists.");
        }
        beans.put(object.getClass(), object);
    }
}
