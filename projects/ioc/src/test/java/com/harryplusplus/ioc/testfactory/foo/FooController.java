package com.harryplusplus.ioc.testfactory.foo;

import com.harryplusplus.ioc.Controller;
import jakarta.validation.constraints.NotNull;

@Controller
public class FooController {
    private @NotNull
    final FooService fooService;

    public FooController(@NotNull FooService fooService) {
        this.fooService = fooService;
    }

    public String sayHello() {
        return fooService.sayHello();
    }
}
