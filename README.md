# KotlinTest vs JUnit 5

Tests can be used as specification when writing new code.
Quite often specification is rather complex which means we have to
write lot of tests for single specification.

Dealing with lot of tests require some kind of a test grouping,
otherwise test readability will be bad and written specification makes no sense anymore.

Standard JUnit4 test library is clumsy at grouping tests. When I write tests with JUnit4 I have to
split test specification to many files, even if implementation can be written in one file.
As an example dog implementation file Dog.java and tests in files WhenDogBarks.java and WhenDogEat.java.
It would be nice to have single DogSpec.java file which contains all test specifications.

There are few libraries created which make it possible to have some kind of test hierarchy inside a junit4 test file but
even so I do not recommend using them because there are better alternatives...

There are zillion of newer jvm unit test libraries out there but I could bet at least one of the following
will be popular: JUnit5 and KotlinTest. This raises a question, which one is better?

Of course test grouping is not only thing which matters when deciding which test library should be used.
To me programming experience is the most important thing, therefore library should be comfortable to use.

In testing context this means...

1) tests run fast (compile+run)
2) tests contains noise code as little as possible
3) tests are easy to group together
4) tests are easy to run with popular build tool (using maven)
5) tests are easy to run with IDE (I use IDEA)

Enough talk, let the code show details and decide your self which one is better.

In order to test testing libraries we need specs...

## bomb specification
```
Bomb tick after creating it.

Bomb disarmed when wires are cut in following order: green, yellow and blue.

Bomb explode
    after two seconds.
    when yellow wire is cut first.
    when blue wire is cut first.
    when green wire is cut first and then blue.

After bomb is disarmed
    it wont explode after two seconds
    it won't hurt if wires are cut again.

```

## Module structure

Both Junit5 and KotlinTest code are put into own module.
This way it's easier to compare things like compile and run time.

Naturally both test modules should test exactly same things in order to
contain same specification for bomb implementation.

```
pom.xml --> parent module for whole project, compile and run all tests with command 'mvn test'
├── bomb
│   ├── src --> contains bomb implementation
│   └── pom.xml --> libraries for bomb implementation (java8)
├── junit
│   ├── src --> test specification written with junit5
│   └── pom.xml --> libraries for Junit5
├── kotlintest
│   ├── src --> test specification written with kotlintest
│   └── pom.xml --> libraries for kotlintest
```

## Platform requirements
Maven3 and Java8 JDK