package com.harryplusplus.ioc;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class BeanUtils {
  public static String getBeanName(@NotNull Class<?> beanClass) {
    String simpleName = beanClass.getSimpleName();
    return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
  }

  public static boolean hasAnnotation(
      @NotNull Class<?> beanClass,
      @NotNull Class<? extends Annotation> annotationClass) {
    return hasAnnotationRecursive(beanClass, annotationClass, new HashSet<>());
  }

  private static boolean hasAnnotationRecursive(
      @NotNull Class<?> baseClass,
      @NotNull Class<? extends Annotation> annotationClass,
      @NotNull Set<Class<?>> visited) {
    if (!visited.add(baseClass))
      return false;
    if (baseClass.isAnnotationPresent(annotationClass))
      return true;
    for (Annotation annotation : baseClass.getAnnotations()) {
      if (hasAnnotationRecursive(annotation.annotationType(), annotationClass, visited))
        return true;
    }
    return false;
  }

}
