package com.harryplusplus.ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.harryplusplus.ioc.testapp.App;
import com.harryplusplus.ioc.testapp.Connection;
import com.harryplusplus.ioc.testapp.foo.FooController;

@TestMethodOrder(OrderAnnotation.class)
class ApplicationContextTest {
  private static ApplicationContext context;

  @BeforeAll
  static void setup() throws Exception {
    context = new ApplicationContext(App.class);
  }

  @Test
  void testSingleton() throws Exception {
    FooController controller1 = context.getBean(FooController.class);
    FooController controller2 = context.getBean(FooController.class);
    assertEquals(controller1, controller2);
  }

  @Test
  void testPrototype() throws Exception {
    Connection connection1 = context.getBean(Connection.class);
    Connection connection2 = context.getBean(Connection.class);
    assertNotEquals(connection1, connection2);
  }

  @Test
  @Order(1)
  void testSayHello0() throws Exception {
    FooController controller = context.getBean(FooController.class);
    assertEquals("Hello! connectionId: 0", controller.sayHello());
  }

  @Test
  @Order(2)
  void testSayHello1() throws Exception {
    FooController controller = context.getBean(FooController.class);
    assertEquals("Hello! connectionId: 1", controller.sayHello());
  }
}
