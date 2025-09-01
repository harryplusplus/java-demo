package com.harryplusplus.ioc;

import jakarta.validation.constraints.NotNull;

public class BeanUtils {
    public static String getBeanName(@NotNull Class<?> beanClass) {
        final String simpleName = beanClass.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }
}
