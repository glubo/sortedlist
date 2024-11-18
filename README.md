# Sorted Linked List Answer

This repo contains an answer to somewhat loose assignment to create a library that allows to use a sorted linked list.

Since I was interested in how Bjarne Stroustrup's recommendation to almost never use linked lists translates to Java, I tried to implement two linked list
implementations and one implementation based on array list and provide basic benchmark capabilities to compare these implementations.

## Uniqueness

I was particularly not sure if I want to implement uniqueness into my solution, but since the name suggested List and not Set, I went in the end with repeating
elements with same value (according to their comparator).

## How to use

You can see https://gitlab.com/g3476/sortedlist/-/packages and find the latest version and see how to configure your build system to include our library.

Example for gradle (Kotlin DSL):

```kotlin

// ...
dependencies {
// ...
    implementation("cz.glubo:sortedlist:<version>") // replace <version> with the latest version
}
// ...
repositories {
// ...
    maven("https://gitlab.com/api/v4/projects/64578383/packages/maven")
}
// ...
```

All our implementations share a common
interface [SortedList](https://gitlab.com/g3476/sortedlist/-/blob/main/src/main/java/cz/glubo/SortedList.java?ref_type=heads) which extends the standard
Java [Iterable](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Iterable.html) interface.

You can see how to use it in our [Integer Test](https://gitlab.com/g3476/sortedlist/-/blob/main/src/test/java/cz/glubo/IntTest.java?ref_type=heads)
and [String Test](https://gitlab.com/g3476/sortedlist/-/blob/main/src/test/java/cz/glubo/StringTest.java?ref_type=heads).

If you really want a linked list, use `SortedLinkedList`, but I would recommend using `SortedArrayList` instead, see benchmark results below.

## How to build

This is a pretty simple gradle project, so something like

```shell
./gradlew build
```

with a Java 21 should work.

## Benchmark results

All results are from 10 repetitions, in random order of implementations.
We have pregenerated two lists of random integers, big with 10 000 elements and small with 1000 elements.

You can see the [BenchmarkTest](https://gitlab.com/g3476/sortedlist/-/blob/main/src/test/java/cz/glubo/BenchmarkTest.java?ref_type=heads) for details.

### Addition Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list.
And measure how much time it takes to add those integers.

| Implementation        | Duration       |
|-----------------------|----------------|
| SortedArrayList       | 2.6±0.1 ms     |
| NaiveSortedArrayList  | 19.9±0.2 ms    |
| SortedLinkedList      | 73.6±0.5 ms    |
| NaiveSortedLinkedList | 400.0±400.0 ms |

We see that arrays beat both linked list implementations by a large margin. It's probably combination of O(n) vs. O(log(n)) for finding the insertion point in
combination of possible cpu cache misses for linked lists.

Even when we remove binary search advantage and cripple performance of ArrayList by using a linear search instead (`NaiveSortedArrayList`), it still is noticeably faster than linked lists.

### Contains Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list as a preparation for our measurement.
But this time, we measure how much time it takes to determine if 1000 random integers are contained in our list.

| Implementation        | Duration       |
|-----------------------|----------------|
| SortedArrayList       | 200.0±300.0 µs |
| NaiveSortedArrayList  | 3.0±1.0 ms     |
| SortedLinkedList      | 17.0±5.0 ms    |
| NaiveSortedLinkedList | 30.0±20.0 ms   |

We see pretty much similar results as with addition benchmark, probably same reasons.

### Filter Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list as a preparation for our measurement.
This time we measure how much time it takes to filter the list to retain just even numbers.

| Implementation        | Duration       |
|-----------------------|----------------|
| NaiveSortedArrayList  | 300.0±300.0 µs |
| SortedLinkedList      | 300.0±300.0 µs |
| NaiveSortedLinkedList | 400.0±300.0 µs |
| SortedArrayList       | 400.0±700.0 µs |

Here we see that all the results are within the standard deviation within each other, so no apparent winners/loosers.

### What next

Those benchmarks are just a small example,
that illustrates that Bjarne Stroustrup's recommendation to avoid linked lists
and prefer compact structures probably translates well to java.

It would be nice to have more variety in benchmarks, but I did not have enough time to explore it more deeply.
