package com.harryplusplus.ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.harryplusplus.ioc.testfactory.App;
import com.harryplusplus.ioc.testfactory.Connection;
import com.harryplusplus.ioc.testfactory.foo.FooController;

class BeanFactoryTest {
  private static BeanFactory factory;

  @BeforeAll
  static void setup() throws Exception {
    factory = new BeanFactory(App.class);
  }

  @Test
  void testSingleton() throws Exception {
    FooController controller1 = factory.getBean(FooController.class);
    FooController controller2 = factory.getBean(FooController.class);
    assertEquals(controller1, controller2);
  }

  @Test
  void testPrototype() throws Exception {
    Connection connection1 = factory.getBean(Connection.class);
    Connection connection2 = factory.getBean(Connection.class);
    assertNotEquals(connection1, connection2);
  }

  @Test
  void testSayHello0() throws Exception {
    FooController controller = factory.getBean(FooController.class);
    assertEquals("Hello! connectionId: 0", controller.sayHello());
  }

  @Test
  void testSayHello1() throws Exception {
    FooController controller = factory.getBean(FooController.class);
    assertEquals("Hello! connectionId: 1", controller.sayHello());
  }
}
