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

While we can declare our own functional interfaces, Java also has plenty of built-in general interfaces including but not limited to:
`Runnable`, `Callable<V>`, `Supplier<T>`, `Consumer<T>`, `Predicate<T>`, `UnaryOperator<T>`, `Function<T, R>`, etc (in [`java.util.function` package](https://docs.oracle.com/javase/9/docs/api/java/util/function/package-summary.html)).

You may notice they have a `@FunctionalInterface` annotation. This is not necessary (the examples above without it are just as correct) but the annotation makes the compiler check that there is exactly one abstract method and also makes it clear to anyone reading the code.

## Lambdas
Lambda expressions are very similar to method declarations.
You can consider lambda expressions as anonymous methods - methods without a name.
Lambdas consist of two parts, the parameters and the body, which are separated by an arrow: `(params) -> body`.

For **parameters** there can be
* no parameters: `() -> body`,
* one parameter: `(foo) -> body` or _preferably_ `foo -> body`,
* many parameters: `(foo, bar, baz) -> body`.

_(Parameter types can be specified but it is unnecessary as we'll find out below.)_

The **body** can be
* a single line (implicit return): `num -> 2 * num`,
* multiple lines enclosed in braces (explicit return):
    ```java
    num -> {
        System.out.println(num);
        return 2 * num;
    }
    ```

### Lambda usage
Lambdas can be assigned to any variables, fields and parameters which's type is a functional interface. So the first example can be shortened ever further by avoiding the variable:
```java
Runnable r = () -> System.out.println("Hello, runnable!");
new Thread(r).start();

// simplified:
new Thread(() -> System.out.println("Hello, runnable!")).start();
```
**Lambdas are implementations of functional interfaces**: the signature of the lambda must match the signature of the single abstract method in the interface. In that case the body of the lambda will be the implementation of that method. This saves us from having to write lots of boilerplate for anonymous inner classes as seen in the first example.

Implicit return of single line lambdas means the expression written into the body will actually still be returned, even though the `return` keyword is not there. If the single line body has a `void` type (like calling `println`), it will also work and the lambda obviously won't try to return it.

While multiline lambdas are possible, they are often considered bad style. It is neater to extract the long body into a separate method and instead call that in a single line lambda (or even better, use a method reference).

### Lambda types
Java is a statically typed language and so far every variable, field and parameter type has been explicitly written out but that's not the case with lambdas: usually the parameters only have names and the return type isn't written. How can this possibly work and still be correct?

The answer is **type inference** (more specifically [target typing](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-typing)): the compiler automatically figures out the types and checks them based on the context where the lambda is written. For example:
```java
java.util.function.UnaryOperator<Integer> doubler = num -> 2 * num;
System.out.println(doubler.apply(7)); // prints 14
```
The `UnaryOperator<Integer>` interface has a (only!) method with one integer parameter that returns an integer. Knowing this, it only makes sense that the `num` parameter in the lambda is an integer as well and the body must be an integer expression to be implicitly returned.

Type inference is a double-edged sword:
* it makes code shorter and less repetitive when everything is correct,
* it makes error messages weird and cryptic when something is incorrect (beware!).

## Method references
When the body of the lambda is a single method call with all the same arguments as the lambda, such as
```java
List.of(1, 2, 3).forEach(num -> System.out.println(num));
```
it can be replaced with a **method reference**:
```java
List.of(1, 2, 3).forEach(System.out::println);
```

The `::` indicates a **method reference**. Method references can be used like lambdas: the signature of the referred method must match the signature of the abstract method in the functional interface.

Method references can be
* references to a static method with the same parameters, e.g. `Math::round` is the same as `num -> Math.round(num)`,
* references to an instance method of the parameter, e.g. `String::toLowerCase` is the same as `str -> str.toLowerCase()`,
* references to an instance method of an object with the same parameters, e.g. `out::println` is the same as `obj -> out.println(obj)`. This is the case for `System.out::println`.

All of the above work with multiple parameters as well. There's no need to really remember these three forms. You can always just write a lambda and allow IntelliJ to suggest using a method reference instead if possible.

## Streams
Streams are one of the main reasons for having lambdas in the first place. They are instances of the generic type `Stream<T>`, so they are a lot like collections (list, set, etc) but are also vastly different. While collections focus on storing elements, **streams focus on manipulating elements** and don't directly store them.

_These streams are completely unrelated to I/O streams like `InputStream` and `OutputStream`, don't confuse the two!_

There are three steps to using streams:
1. creation,
2. some number of intermediate operations,
3. one terminal operation.

### Creation
There are many things to get streams from:
* Hardcoded elements with `Stream.of()` similarly to `List.of()`, e.g. `Stream.of(1, 4, 10, 25)`.  
    Not very useful in practice but good for examples here.
* Collection (list, set, etc) elements with `stream()`, e.g. `List.of(1, 4, 10, 25).stream()`.  
    Most commonly used.
* Special streaming methods, e.g. `Files.lines(path)` gives a stream of strings of all lines in the file.

### Terminal operations
Once you have a stream, a terminal operation on it is required for anything to happen. As the name implies, such operation is always last.
Some of the most common ones are:
* `forEach(…)` runs something for every element, e.g. `Stream.of(1, 2, 3).forEach(System.out::println)` prints 1, 2 and 3.
* `collect(Collectors.toList())` returns a list of all elements, e.g. `Stream.of(1, 2, 3).collect(Collectors.toList())` returns a list of 1, 2 and 3.
* `count()` counts the elements, e.g. `Stream.of(1, 2, 3).count()` returns 3.
* `anyMatch(…)`, `allMatch(…)`, `noneMatch(…)` to check whether something is true for some/all/none elements, e.g. `Stream.of(1, 2, 3).anyMatch(num -> num % 2 == 0)` returns true (because the element 2 is even).

### Intermediate operations
Between creation and the terminal operation, more operations may be added that do something with the elements. These operations always return a new stream, which can be used for more intermediate operations or finally a terminal one.
Some of the most common ones are:
* `filter(…)` only keeps the elements for which the predicate is true, e.g. `Stream.of(1, 2, 3).filter(num -> num % 2 != 0)` returns a stream of 1 and 3.
* `map(…)` replaces elements according to a function, e.g. `Stream.of(1, 2, 3).map(num -> num + 7)` returns a stream of 8, 9 and 10.  
    The function may return objects of a different type. In this case the returned stream will be of different type also.
* `limit(…)` keeps at most the number of elements, e.g. `Stream.of(1, 2, 3, 4, 5).limit(3)` returns a stream of 1, 2 and 3.
* `distinct()` removes duplicates, e.g. `Stream.of(1, 2, 3, 1, 2).distinct()` returns a stream of 1, 2 and 3 (the second 1 and 2 are discarded).

### Chaining operations
Intermediate operations can create (long) chains of computational steps, for example
```java
IntStream.rangeClosed(1, 10)
        .filter(i -> i % 2 == 0)
        .map(i -> 2 * i)
        .limit(3)
        .forEach(System.out::println);
```
prints 4, 8 and 12. These are the doubles of first three even numbers in the range 1…10.
This makes streams very convenient for working with all kinds of data because the operations can be written in a simple step-by-step manner. The same code could be written with a for-loop (feel free to try!) but in the resulting code it would be much harder to understand the computational steps, especially if the chain gets even longer.

Note that the order of intermediate operations is important! Experiment with changing the order of `filter`, `map` and `limit` in this example and try to explain the changes that causes. 

### Laziness
Streams are lazy, which simply means they do as few operations as possible and as late as possible. For this reason, streams only start processing the elements when a terminal operation is run. All intermediate operations are simply "remembered" but not actually executed before necessary.

This is illustrated by the following example where intermediate operations print something so we can see whether and when they run (never do this in practice):
```java
IntStream.rangeClosed(1, 10)
        .filter(i -> {
            System.out.println("filter: " + i);
            return i % 2 == 0;
        })
        .map(i -> {
            System.out.println(" map: " + i);
            return 2 * i;
        })
        .limit(3)
        .forEach(i -> System.out.println("  forEach: " + i));
```
Its output is
```
filter: 1
filter: 2
 map: 2
  forEach: 4
filter: 3
filter: 4
 map: 4
  forEach: 8
filter: 5
filter: 6
 map: 6
  forEach: 12
```
Observe how 7 to 10 were never even filtered, only matching filtered elements were mapped and everything finished after finding the asked three elements, which also got printed in the `forEach`. It is neat that thanks to laziness the stream automatically behaved efficiently, avoiding unnecessary computations, which requires additional complexity if simply attempted with a for-loop.

Laziness also has one surprising consequence: it is possible to use **infinite streams**, which can be created with `Stream.generate(…)` and `Stream.iterate(…)`. Care must be taken to make sure the terminating operation finishes at all, i.e. such stream cannot be collected to a list but certain operations still work fine (e.g. `anyMatch`).
Infinite streams are quite obscure and not too useful in practice but it may be interesting to know that working with something infinite is possible.


# Tasks
Clone this project and open it in IntelliJ. You'll find tasks in respective packages in order of increasing difficulty. Read their description comments, look at examples and do everything marked as `// TODO`.  
_Beware that some tasks are marked `HARD`: they go slightly beyond this material so don't feel bad if you aren't able to solve them._
