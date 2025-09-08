package com.harryplusplus.ioc.testapp.foo;

import com.harryplusplus.ioc.ApplicationContext;
import com.harryplusplus.ioc.Repository;
import com.harryplusplus.ioc.testapp.Connection;

import jakarta.validation.constraints.NotNull;

@Repository
public class FooRepository {
  private @NotNull final ApplicationContext context;

  public FooRepository(@NotNull ApplicationContext context) {
    this.context = context;
  }

  public String sayHello() {
    try {
      Connection connection = context.getBean(Connection.class);
      return "Hello! connectionId: " + connection.getConnectionId();
    } catch (Exception e) {
      return "Error: " + e;
    }
  }
}
