# KotlinTest vs JUnit4 vs Junit5

## Why bother comparing jvm test libraries

In java world tests are usually written with popular JUnit4 library.

JUnit4 works quite well when dealing with simple specification
but it's different story when specification is complex.

JUnit4 is bad at grouping test cases inside one file.
Few libraries can be used to tackle this problem by using junit runners.
I have tried few of them and been little disappointed and therefore stopped using them.

As a fix to "many junit test cases inside one file"- problem I have
spread test cases (about one specification) into several files.

I don't like this approach. It would be nice to have DogSpec.java file
which contains all test cases for Dob class implementation instead of
files WhenDogDoThis.java and WhenDogDoThat.java.

## Alternatives

It's year 2017, something better must be out there!
After a little search I came up with three possible solutions.

1) Stick to JUnit4, use best custom runner which support test grouping.
(It seems to be https://github.com/bechte/junit-hierarchicalcontextrunner/wiki)

2) Use JUnit5 which I could imagine has solved test grouping problem.

3) Use something totally different like kotlintest which seems to be very promising.

## Good programming experience

Grouping tests is not only thing which matters when choosing new library.

In the end programming experience is the most important thing,
therefore library should be comfortable to use.

Writing tests is fun when...

1. Tests run fast
1. Tests contains noise code as little as possible
1. Tests are easy to group together
1. Tests can be run with IDE (I use IDEA)
1. Tests are easy to run with popular build tools

## Module structure

```
pom.xml --> parent module for whole project, compile and run all tests with command 'mvn test'
├── bomb
│   ├── src --> Contains bomb implementation
│   └── pom.xml --> libraries for bomb implementation (java8)
├── junit4
│   ├── src --> test specification written with junit4
│   └── pom.xml --> libraries for Junit4
├── kotlintest
│   ├── src --> test specification written with kotlintest
│   └── pom.xml --> libraries for kotlintest
```


## Results

| Library    	| Compile and runtime using maven plugins 	| LOC 	| Grouping                            	| IDE support (IDEA 2017.2)                   	|
|------------	|-----------------------------------------	|-----	|-------------------------------------	|---------------------------------------------	|
| JUnit4     	| 5.218 s                                 	| 84  	| Implemented with annotations        	| Best, run fast and can run single test case 	|
| JUnit5     	| 5.834 s                                 	| 84  	| Implemented with annotations        	| Poor, did not run                           	|
| KotlinTest 	| 6.227 s                                 	| 63  	| Implemented with lambda expressions 	| Good, run all tests pretty fast             	|

Both junit libraries can be run faster than Kotlin tests because compiler
kotlin source code takes more time than compiling java code. All in all
compile and runtime difference quite small so I guess this is a tie.

Both JUnit libraries are written with java and it has huge impact on
test case readability. In this section kotlintest is a clear winner.

JUnit5 (RC2 version) test cases didn't run at all with Intellij Idea,
I don't know is this the case with other IDE's like eclipse.
JUnit4 shine with it's excellent IDE support .
KotlinTest test cases run pretty smoothly in IDEA which isn't a big surprise
because both kotlin and IDEA IDE are created by JetBrains.

## Winner --> KotlinTest

I recommend KotlinTest.

KotlinTest is best unit testing library I have ever used.

Tests are not bloated with noise code and they run fast enough
and other tools support it well.

## Platform requirements
Maven3 and Java8 JDK

## Specification found from all tests.

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

