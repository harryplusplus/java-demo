package com.harryplusplus.ioc;

public class Main {

    static class Foo {
    }

    public static void main(String[] args) {
        System.out.println("This is a IoC Demo!");
        final var container = new Container();
        container.register(new Foo());
        container.register(new Foo());
    }
}
