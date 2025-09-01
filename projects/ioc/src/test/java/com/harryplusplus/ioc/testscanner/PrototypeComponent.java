package com.harryplusplus.ioc.testscanner;

import com.harryplusplus.ioc.BeanDefinition;
import com.harryplusplus.ioc.Component;
import com.harryplusplus.ioc.Scope;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PrototypeComponent {
}
