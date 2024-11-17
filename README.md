# Sorted Linked List Answer

This repo contains an answer to somewhat loose assignment to create a library that allows to use a sorted linked list.

Since I was interested in how Bjarne Stroustrup's recommendation to almost never use linked lists translates to Java, I tried to implement two linked list implementations and one implementation based on array list and provide basic benchmark capabilities to compare these implementations.


## Benchmark results

All results are from 10 repetitions, in random order of implementations.
We have pregenerated two lists of random integers, big with 10 000 elements and small with 1000 elements.

### Addition Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list.
And measure how much time it takes to add those integers.

```
Result[mean=PT0.002693901S, stdDev=PT0.000143991S, name=SortedArrayList]
Result[mean=PT0.075937342S, stdDev=PT0.000750222S, name=IteratorSortedLinkedList]
Result[mean=PT0.512177987S, stdDev=PT0.320325172S, name=NaiveSortedLinkedList]
```

We see that arrays beat both linked list implementations by a large margin. It's probably combination of O(n) vs. O(log(n)) for finding the insertion point in combination of possible cpu cache misses for linked lists.

###  Contains Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list as a preparation for our measurement.
But this time, we measure how much time it takes to determine if 1000 random integers are contained in our list.

```
Result[mean=PT0.000226309S, stdDev=PT0.000289077S, name=SortedArrayList]
Result[mean=PT0.016319637S, stdDev=PT0.000315023S, name=IteratorSortedLinkedList]
Result[mean=PT0.026853456S, stdDev=PT0.016545583S, name=NaiveSortedLinkedList]
```

We see pretty much similar results as with addition benchmark, probably same reasons.


### Filter Benchmark

We take the list of 10 000 random integers and add one by one to our sorted list as a preparation for our measurement.
This time we measure how much time it takes to filter the list to retain just even numbers.

```
Result[mean=PT0.00033296S, stdDev=PT0.000263669S, name=IteratorSortedLinkedList]
Result[mean=PT0.000413811S, stdDev=PT0.00037104S, name=NaiveSortedLinkedList]
Result[mean=PT0.000505244S, stdDev=PT0.000385013S, name=SortedArrayList]
```

Here we see that all the results are within the standard deviation within each other, so no apparent winners/loosers.


### What next

Those benchmarks are just a small example, that illustrates that Bjarne Stroustrup's recommendation to avoid linked lists and prefer compact structures probably translates well to java.

It would be nice to have more variety in benchmarks, but I did not have enough time to explore it more deeply.
