# Collections Internals II

## Map Interface

- `Map<K,V>` is NOT a `Collection` — separate root interface. Stores key-value pairs, keys are unique.
- Main impls: `HashMap` (unordered, hash table), `LinkedHashMap` (HashMap + remembers order), `TreeMap` (sorted, red-black tree), `Hashtable` (legacy, synchronized).

## HashMap Backing Structure

- Array of buckets: `Node<K,V>[] table`. Each bucket can hold 0, 1, or many entries, chained via `next`.
- Default: 16 buckets, lazily allocated on first `put()` (same lazy trick as ArrayList).
- Table size is **always a power of 2** — even `new HashMap<>(20)` gets rounded up to 32.
- Load factor default = 0.75. `threshold = capacity * loadFactor` → 16 × 0.75 = 12.

## Hashing

- Raw `hashCode()` isn't used directly — it's "spread" first: `hash ^ (hash >>> 16)`, mixing the high bits into the low bits to reduce collisions.
- Bucket index = `(n - 1) & hash` — a fast bitwise AND instead of modulo. Only works because table size is a power of 2 (this is *why* it must be a power of 2).

## Collisions & Treeification (Java 8+)

- Colliding keys chain together in the same bucket (linked list). Lookup in a bucket = O(k), k = chain length.
- If a chain hits **8 entries** AND the table is **≥ 64 buckets**, that bucket converts to a red-black tree → lookup becomes O(log n) instead of O(n).
- Below 64 buckets, HashMap just resizes the whole table instead of treeifying — a long chain that early usually means the table's too small, not a bad hash function.
- Tree shrinks back to a plain chain if it drops to 6 entries or fewer.

## Resizing

- Triggered when `size > threshold`. Capacity doubles (16→32→64...), threshold doubles too.
- Full O(n) redistribution — every entry gets re-placed since the bucket index depends on table size.
- Java 8 optimization: since capacity always doubles, each old entry only has two possible new spots — same index, or index + oldCapacity — decided by a single bit (`hash & oldCapacity`). No need to recompute the full hash.
- If you know the entry count ahead of time, pre-size the map to avoid repeated resize-and-rehash cycles.

## put() / get() Flow

- **put()** → hash the key → find bucket → if empty, insert directly → if not, walk the chain/tree comparing hash then `equals()` → replace on match, else append at tail (Java 8+) → treeify if chain just hit 8 → resize if threshold crossed.
- **get()** → same hash + bucket steps → walk chain/tree, compare cached hash first (cheap), only call `equals()` if hashes match.
- Must override **both** `hashCode()` and `equals()` consistently for custom keys — otherwise a key can go into the "wrong" bucket and lookups silently return `null`.

## HashMap vs Others

| Feature | HashMap | Hashtable | LinkedHashMap | TreeMap |
|---|---|---|---|---|
| Thread-safe | No | Yes | No | No |
| Null key | Yes, one | No | Yes, one | No |
| Order | None | None | Insertion/access | Sorted |
| get/put cost | O(1) | O(1) | O(1) | O(log n) |

## Fail-Fast Iteration

- Same `modCount` mechanism as `ArrayList`/`LinkedList`. `put()` of a *new* key, `remove()`, `clear()` bump `modCount`.
- Removing directly during a for-each over `keySet()`/`entrySet()` → CME.
- Replacing the value of an *existing* key with `put()` does NOT bump `modCount` — not a structural change.
- Safe removal: `Iterator.remove()` or `map.entrySet().removeIf(...)`.

## Cheat Sheet

| Concept | Note |
|---|---|
| Default capacity | 16, power of 2, lazy allocation |
| Default load factor | 0.75 |
| Threshold formula | `capacity * loadFactor` |
| Resize behavior | Capacity doubles, full redistribution |
| Bucket index formula | `(n - 1) & hash` |
| Hash spreading | `hash ^ (hash >>> 16)` |
| Treeify trigger | chain ≥ 8 AND capacity ≥ 64 |
| Untreeify trigger | chain ≤ 6 |
| Key contract | equal keys must return equal `hashCode()` |

