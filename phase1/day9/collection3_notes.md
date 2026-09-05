## TreeMap, TreeSet and Priority Queue

### TreeMap:

- Backed Red Black Tree a self balancing tree
- Uses compare()/compareTo() for comparing two nodes.
- keeps data/nodes sorted based on compare()/compareTo()

### TreeSet

- backed by TreeMap
- keeps sorted with no duplicates

### Priority Queue

- backed by array
- maintains a Binary Heap.
- by default it is min heap and you can make it max heap by passing custom comparator.
- only guarantees root to be the smallest/largest

## Compareable and Comparator

### Comparable

- class who implements it overrides `compareTo(obj)`.
- consider `compareTo(obj)` a insider function of that class.

### Comparator

- Normally an anonymous class is made.
- and that comparator is passed where needed. like in sort function.
- it is external to the class.
- we have to override `compare(obj1, obj2)`
