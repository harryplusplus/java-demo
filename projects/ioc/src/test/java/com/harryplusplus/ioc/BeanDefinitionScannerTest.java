package com.harryplusplus.ioc;

import com.harryplusplus.ioc.testscanner.PrototypeComponent;
import com.harryplusplus.ioc.testscanner.SingletonComponent;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeanDefinitionScannerTest {
  @Test
  void testScan() throws Exception {
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    BeanDefinitionScanner scanner = new BeanDefinitionScanner(beanDefinitionMap);
    scanner.scan("com.harryplusplus.ioc.testscanner");

    BeanDefinition singletonDefinition = beanDefinitionMap.get("singletonComponent");
    assertEquals(SingletonComponent.class, singletonDefinition.getBeanClass());
    assertEquals(BeanDefinition.SCOPE_SINGLETON, singletonDefinition.getScope());

    BeanDefinition prototypeDefinition = beanDefinitionMap.get("prototypeComponent");
    assertEquals(PrototypeComponent.class, prototypeDefinition.getBeanClass());
    assertEquals(BeanDefinition.SCOPE_PROTOTYPE, prototypeDefinition.getScope());
  }
}
