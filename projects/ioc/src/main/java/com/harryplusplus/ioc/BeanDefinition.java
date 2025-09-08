package com.harryplusplus.ioc;

import jakarta.validation.constraints.NotNull;

public class BeanDefinition {
  public static final String SCOPE_SINGLETON = "singleton";
  public static final String SCOPE_PROTOTYPE = "prototype";

  private final Class<?> beanClass;
  private final String scope;

  public BeanDefinition(@NotNull Class<?> beanClass, @NotNull String scope) {
    this.beanClass = beanClass;
    this.scope = scope;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public String getScope() {
    return scope;
  }
}
