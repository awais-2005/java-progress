# String Internals

In java strings immutable, means there content never changes after initialization.

### Java 6 and Earlier

Before java 7 String was backed by `char[]` and `offset/count`. Every String object had

- an array of character `char[]`
- a `int offset` field as a starting index of String
- a `int count` field as number of characters after that offset

**Memory Leak Issue:** `subString(begin, end)` used to create a new String object that contain that original `char[]`, `offset = begin` and `count = end - begin`. Now imagine you call subString() for 10 characters on a String whose size is 10000.

### In Java 7

String was still backed by `char[]` but `offset/count` got removed. New implementation copies substring to new array. that fixed memory leak!

### In Java 9

Storage implementation got changed from `char[]` to `byte[]`. and a field byte `coder`. if size of each character in string is 1 byte, implementation uses LATIN1 (1byte/char) and `coder is set to 0 to represent LATIN1` if any character in string require 2 bytes `coder is set to 1 to represent UTF16` takes (2bytes/char)

## String Pool, Immutability & intern()

**String Pool:** String pool is JVM managed region, JVM stores String literals here maintaining uniqueness. acts like a `HashMap<String, String>` where key and value are same String object.

- Saves memory from duplicate String objects
- Give faster comparison
- Before Java 7 it was part of PremGen
- In java 7 it was moved Heap

**Immutability:** Once a String object is created, it can never be changed.

**`intern();`** is a instance method of String. when it is called it does two things, first checks in String pool if the content of it object is pooled if yes returns the pooled reference if not then first it pools the content and then returns the pooled reference.

### Example Code

```java
public static void main(String[] args) {
    String s1 = "Hello"; // gets pooled
    String s2 = "Hello"; // reused from pool
    // s1 == s2 is true mean same object.
    System.out.println(s1 == s2);

    s1 = new String("Hello"); // forcefully creates an object in heap outside the String Pool
    // now s1 == s2 is false
    System.out.println(s1 == s2);

    s1 = s1.intern(); // intern() returns the pooled reference which s2 alreadly have!
    // now s1 == s2 is true
    System.out.println(s1 == s2);

}
```

### Constant Folding

It is performed by java compiler during compilation.

```java
public static void main(String[] args) {
    // Example 1
    String s1 = "Hello" + " world"; // During compilation, treated as "Hello world" and pooled
    String s2 = "Hello world"; // reused from pool
    // s1 == s2 is true mean same object.
    System.out.println(s1 == s2);

    // Example 2
    final String s3 = "Hello"; // Compile time Constant
    String s4 = s3 + " world"; // gets folded and pooled
    // Again s4 == s2 is true
    System.out.println(s4 == s2);

    // Example 3
    String s5 = "Hello"; // not a constant
    String s6 = s5 + " world"; // concatenated in runtime via StringBuilder, a new object in heap.
    // s6 == s2 is false
    System.out.println(s6 == s2);
}
```

### Immutability Rationale(Reason)

- String Pooling mechanism works just because of Immutability
- It make String secure, saves from TOCTOU attacks. because strings are used everywhere.
- we can cache hashCode because of Immutability.
- natural thread safty, no risk of corruption and torn value.

## StringBuilder & StringBuffer

Since String is immutable doing `string1 += string2` inside a loop was very expensive. solution came in form of StringBuilder. in simple words we can say it is a mutable string.

#### Difference between StringBuilder and StringBuffer

Only difference is that, StringBuffer is synchronized which further creates difference. like StringBuffer is slower than StringBuilder. StringBuffer gets locked when being mutated, means no other thread can mutate in that instance.

---

### BenchMark (String vs StringBuilder vs StringBuffer)

See the code in `BenchMark.java` to understand this output

```output
Time taken by String: 1268ms
Time taken by StringBuilder: 6.220ms
Time taken by StringBuffer: 19ms
```
