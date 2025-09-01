package com.harryplusplus.ioc.testfactory.foo;

import com.harryplusplus.ioc.Service;
import jakarta.validation.constraints.NotNull;

@Service
public class FooService {
    private @NotNull
    final FooRepository fooRepository;

    public FooService(@NotNull FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    public String sayHello() {
        return fooRepository.sayHello();
    }
}
