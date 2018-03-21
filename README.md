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

While we can declare our own functional interfaces, there are also plenty of standard ones including but not limited to:
`Runnable`, `Callable<V>`, `Supplier<T>`, `Consumer<T>`, `Predicate<T>`, `UnaryOperator<T>`, `Function<T, R>`, etc.
You may notice they have a `@FunctionalInterface` annotation. This is not necessary (the examples above without it are just as correct) but the annotation makes the compiler check that there is exactly one abstract method and also makes it clear to anyone reading the code.

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

### Lambda usage
Lambdas can be assigned to any variables, fields and parameters which's type is a functional interface. So the first example can be shortened ever further by avoiding a variable:
```java
new Thread(() -> System.out.println("Hello, runnable!")).start();
```
Lambdas are implementations of functional interfaces: the signature of the lambda must match the signature of the single abstract method in the interface. In that case the body of the lambda will be the implementation of that method. This saves us from having to write lots of boilerplate for anonymous inner classes as seen in the first example.

### Lambda types
Java is a statically typed language and so far every variable, field and parameter type has been explicitly written out but that's not the case with lambdas: usually the parameters only have names and the return type isn't written. How can this possibly work and still be correct?

The answer is **type inference** (more specifically [target typing](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing)): the compiler automatically figures out the types and checks them based on the context where the lambda is written. For example:
```java
UnaryOperator<Integer> doubler = num -> 2 * num;
System.out.println(doubler.apply(7)); // prints 14
```
The `UnaryOperator<Integer>` interface has a (only!) method with one integer parameter that returns an integer. Knowing this, it only makes sense that the `num` parameter in the lambda is an integer as well and the body must be an integer expression to be implicitly returned.

Type inference is a double-edged sword:
* it makes code shorter and less repetitive when everything is correct,
* it makes error messages weird and cryptic when something is incorrect (beware!).

## Method references
When the body of the lambda is a single method call with all the same arguments as the lambda, like
```java
List.of(1, 2, 3).forEach(num -> System.out.println(num));
```
it can be replaced with a **method reference**:
```java
List.of(1, 2, 3).forEach(System.out::println);
```

The `::` indicates a method reference. Method references can be used like lambdas: the signature of the referred method must match the signature of the abstract method in the functional interface.

Method references can be
* references to a static method with the same parameters, e.g. `Math::round` is the same as `num -> Math.round(num)`,
* references to an instance method of the parameter, e.g. `String::toLowerCase` is the same as `str -> str.toLowerCase()`,
* references to an instance method of an object with the same parameters, e.g. `out::println` is the same as `obj -> out.println(obj)`.

All of the above work with multiple parameters as well. There's no need to really remember these three forms. You can always just write a lambda and allow IntelliJ to suggest using a method reference instead if possible.

## Streams
