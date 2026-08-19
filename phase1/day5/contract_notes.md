## equals(), hashCode(), and the Object Contract

### Why this is important?

Every class in java implicitly extends Object class and the object class defines hashcode() and equals() methods and there is a specific contract between these two methods which we should never break. if broken the collections like `HashMap`, `HashSet`, `Hashtable`, `ConcurrentHashMap`, `LinkedHashMap`, `LinkedHashSet` will misbehave and cause bugs. Because each of these collection uses these two methods to store and fetch the object.

### Defaut Implementations

```java
public boolean equals(Object o) {
    return (this == o); // just if both references points to same object
}

public native int hashcode(); // JVM derived, typically tied to memory address.
```

### The equals() Contract

If you override the `equals()`, your implementation must satisfy five properties for any non null references x, y and z.

- **Reflexive:** `x.equals(x)` must be true.
- **Symmetric:** `x.equals(y)` must be equal to `y.equals(x)`.
- **Transitive:** `x.equals(y)` and `y.equals(z)` are true then `x.equals(z)` must be true.
- **Consistent:** multiple calls to `equals()` must return consistent result. if no fields used in comparison are changed.
- **Non null:** `x.equals(null)` should always return false.

### The hashCode() Contract

- **Rule 1:** `hashCode()` must return same integer during one execution of application. until no equals() related fields changes.
- **Rule 2:** hashCodes of equal objects must be equal
- **Rule 3:** for unequal objects it is not necessary to have equal hashcode

**Golden Rule:** `hashCode()` should use all those fields used by `equals()`, neither more nor less, same applies for equals.

## IdentityHashMap

Deliberately violates the general Map contract. instead of using `key.equals(k)` and `key.hashCode()` is compare references `key == k` and for hashcode `System.identityHashCode(key)`.

```java
public static void main(String[] args) {
    Map<String, String> idenHashMap = new IdentityHashMap<>();
    Map<String, String> hashMap = new HashMap<>();

    String s1 = new String("key1");
    String s2 = new String("key1");

    idenHashMap.put(s1, "value1");
    hashMap.put(s1, "value1");

    if (idenHashMap.containsKey(s2)) {
        System.out.println("found in iden");
    }

    if (hashMap.containsKey(s2)) {
        System.out.println("found in normal");
    }
}
```
In this code you can see content of both keys is same but they are different objects. so that's how IdentityHashMap allows you have same looking keys in map.
