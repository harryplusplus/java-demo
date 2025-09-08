package com.harryplusplus.ioc.testfactory;

import com.harryplusplus.ioc.BeanDefinition;
import com.harryplusplus.ioc.Component;
import com.harryplusplus.ioc.Scope;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Connection {
  private static int globalConnectionId = 0;
  private final int connectionId;

  public Connection() {
    this.connectionId = globalConnectionId++;
  }

  public int getConnectionId() {
    return connectionId;
  }
}
