package com.harryplusplus.ioc;

import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
  private @NotNull
  final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
  private @NotNull
  final Map<String, Object> singletonBeanMap = new HashMap<>();

  public BeanFactory(@NotNull Class<?> mainClass) throws Exception {
    if (BeanUtils.hasAnnotation(mainClass, ComponentScan.class)) {
      BeanDefinitionScanner scanner = new BeanDefinitionScanner(beanDefinitionMap);
      scanner.scan(mainClass.getPackageName());
    }
  }

  public <T> T getBean(@NotNull Class<T> beanClass) throws Exception {
    String beanName = BeanUtils.getBeanName(beanClass);
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null)
      throw new Exception("Unexpected bean name. name: " + beanName);

    String scope = beanDefinition.getScope();
    if (beanClass != beanDefinition.getBeanClass())
      throw new Exception("Unexpected bean class. expected: " + beanClass + " actual: " + beanDefinition.getBeanClass());

    if (scope.equals(BeanDefinition.SCOPE_SINGLETON)) {
      Object singletonBean = singletonBeanMap.get(beanName);
      if (singletonBean == null) {
        singletonBeanMap.put(beanName, newBean(beanClass));
        singletonBean = singletonBeanMap.get(beanName);
      }
      return beanClass.cast(singletonBean);
    }

    if (scope.equals(BeanDefinition.SCOPE_PROTOTYPE)) {
      Object prototypeBean = newBean(beanClass);
      return beanClass.cast(prototypeBean);
    }

    throw new Exception("Unexpected bean scope. scope: " + scope);
  }

  private Object newBean(@NotNull Class<?> beanClass) throws InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<?> constructor = beanClass.getDeclaredConstructors()[0];
    Class<?>[] parameterTypes = constructor.getParameterTypes();
    Object[] args = Arrays.stream(parameterTypes).map(x -> {
      try {
        return getBean(x);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).toArray();
    return constructor.newInstance(args);
  }
}
