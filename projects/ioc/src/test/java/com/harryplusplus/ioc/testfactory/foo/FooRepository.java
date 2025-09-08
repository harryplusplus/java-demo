package com.harryplusplus.ioc.testfactory.foo;

import com.harryplusplus.ioc.Repository;
import com.harryplusplus.ioc.testfactory.Connection;
import jakarta.validation.constraints.NotNull;

@Repository
public class FooRepository {
  private @NotNull final Connection connection;

  public FooRepository(@NotNull Connection connection) {
    this.connection = connection;
  }

  public String sayHello() {
    return "Hello! connectionId: " + connection.getConnectionId();
  }
}
