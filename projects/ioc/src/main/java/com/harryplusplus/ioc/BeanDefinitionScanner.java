package com.harryplusplus.ioc;

import jakarta.validation.constraints.NotNull;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

public class BeanDefinitionScanner {
  private @NotNull final Map<String, BeanDefinition> beanDefinitionMap;

  public BeanDefinitionScanner(@NotNull Map<String, BeanDefinition> beanDefinitionMap) {
    this.beanDefinitionMap = beanDefinitionMap;
  }

  public void scan(@NotNull String packageName) throws Exception {
    String path = packageName.replace('.', '/');
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL resource = classLoader.getResource(path);
    if (resource == null)
      throw new Exception("Package not found. packageName: " + packageName);

    File directory = new File(resource.toURI());
    scanRecursive(directory, packageName);
  }

  private void scanRecursive(
      @NotNull File directory,
      @NotNull String packageName) throws Exception {
    if (!directory.exists())
      return;

    File[] files = directory.listFiles();
    if (files == null)
      return;

    for (File file : files) {
      if (file.isDirectory()) {
        scanRecursive(file, packageName + "." + file.getName());
      } else if (file.getName().endsWith(".class")) {
        String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
        Class<?> beanClass = Class.forName(className);
        if (BeanUtils.hasAnnotation(beanClass, Component.class)) {
          Scope scope = beanClass.getAnnotation(Scope.class);
          String scopeValue = scope != null ? scope.value() : BeanDefinition.SCOPE_SINGLETON;
          String[] scopes = { BeanDefinition.SCOPE_SINGLETON, BeanDefinition.SCOPE_PROTOTYPE };
          if (Arrays.asList(scopes).contains(scopeValue)) {
            String beanName = BeanUtils.getBeanName(beanClass);
            beanDefinitionMap.put(beanName, new BeanDefinition(beanClass, scopeValue));
          } else {
            throw new Exception("Unexpected scope value. value: " + scopeValue);
          }
        }
      }
    }
  }
}
