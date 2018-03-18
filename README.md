# Lambdas
Lambdas, functional interfaces, method references and streams are useful features introduced in Java 8.

Instead of defining many standalone classes for very simple implementations, such as event listeners, we can use anonymous inner classes to define new implementations along the way. For example:
```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello, runnable!");
    }
};

new Thread(r).start();
```

Code like this still looks needlessly verbose for single line methods, like above. Turns out there is an even neater way:
```java
Runnable r = () -> System.out.println("Hello, runnable!");
new Thread(r).start();
```

The `() -> System.out.println("Hello, runnable!")` construct with an arrow in the middle is called a **lambda**.
Before diving deeper into lambdas, we must understand functional interfaces.
 
## Functional interfaces
Functional interfaces are interfaces that have **exactly one abstract method**. For example, `Runnable` is a functional interface:
```java
interface Runnable {
    void run();
}
```

The following is not a functional interface because it has too many (two) abstract methods:
```java
interface TooManyMethods {
    int toInt();
    double toDouble();
}
```

The following again is a functional interface because the second method is not abstract:
```java
interface UltimateRunnable {
    void run();
    default int getUltimateAnswer() {
        return 42;
    }
}
```

## Lambdas
Lambdas consist of two parts, the parameters and the body, which are separated by an arrow: `(params) -> body`.

There can be
* no parameters: `() -> body`,
* one parameter: `(foo) -> body` or preferably `foo -> body`,
* more parameters: `(foo, bar, baz) -> body`.

The body can be
* a single line (implicit return): `num -> 2 * num`,
* multiple lines (explicit return):
    ```java
    num -> {
        System.out.println(num);
        return 2 * num;
    }
    ```

## Method references
## Streams
