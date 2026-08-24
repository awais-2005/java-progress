# Collections Internals I (My Notes)

## List Interface

- `List<E>` extends `Collection<E>` → ordered, allows duplicates, access by index.
- Two main implementations: `ArrayList` (array-based) and `LinkedList` (doubly linked list, also works as `Deque`).
- Both are **not thread-safe** and both are **fail-fast**.
- `Vector` = old thread-safe array list, grows 2x. `ArrayList` grows 1.5x.

## ArrayList Internals

- Backed by a plain `Object[]` array. `size` = elements actually stored, `elementData.length` = capacity.
- Lazy init — `new ArrayList<>()` does NOT create a 10-slot array immediately. Real array only created on first `add()`.
- `add()` at end → O(1) amortized (only O(n) on the rare resize).
- Growth factor = **1.5x**, not 2x like Vector. Reason: smaller growth leaves old freed memory reusable later; doubling wastes it forever.
- `add(index)` / `remove(index)` → O(n) worst case (has to shift elements). O(1) at the very end.
- `get`/`set` → O(1), direct array indexing.
- Capacity never shrinks on its own — call `trimToSize()` manually if needed.

## LinkedList Internals

- True doubly linked list, no backing array. Each `Node` holds item + next + prev.
- `addFirst`/`addLast`/`removeFirst`/`removeLast` → O(1) since `first`/`last` are tracked directly.
- `get(index)` → O(n), but walks from whichever end (first/last) is closer to cut distance in half.
- `add(index)`/`remove(index)` → O(n) to find the node, O(1) to relink it.
- Costs way more memory per item (~32–48 bytes overhead) vs ArrayList (~4–8 bytes), because of the Node objects scattered in memory.

## ArrayList vs LinkedList

| Operation | ArrayList | LinkedList |
|---|---|---|
| get/set(index) | O(1) | O(n) |
| add() at end | O(1) amortized | O(1) |
| addFirst/removeFirst | O(n) | O(1) |
| add/remove(index) | O(n) | O(n) |
| Memory per element | ~4–8 bytes | ~32–48 bytes |
| Works as Deque | No | Yes |

- In real life `ArrayList` usually wins even for middle inserts — array copy is cache-friendly, node walking is not.
- Rule of thumb: default to `ArrayList`. Need a deque? Use `ArrayDeque`, not `LinkedList`.

## Fail-Fast Iterators & ConcurrentModificationException

- `modCount` (in `AbstractList`) increments on every structural change (`add`/`remove`/`clear`) — NOT on `set()`.
- Iterator saves `modCount` as `expectedModCount` when created. Mismatch on `next()` → throws `ConcurrentModificationException`.
- Classic bug: calling `list.remove()` directly inside a for-each loop → CME.
- Gotcha: `hasNext()` doesn't check `modCount`. Removing the second-to-last item can end the loop silently, no exception thrown.
- In my implementaion I fix this `gotcha`
- Safe ways to remove while iterating: `Iterator.remove()`, `removeIf()`, `ListIterator`.
- `CopyOnWriteArrayList` = fail-safe, not fail-fast. Copies the whole array on every write; old iterators just see the old snapshot. Good for read-heavy, rarely-written lists.

## Cheat Sheet

| Concept | Note |
|---|---|
| Growth factor | 1.5x (Vector uses 2x) |
| Default capacity | 10, but lazy — created on first add() |
| Amortized add() cost | O(1) |
| Shrinking | Manual only — `trimToSize()` |
| modCount lives in | `AbstractList` |
| CME thrown by | `next()`, iterator's `remove()` |
| Safe removal while iterating | `Iterator.remove()` / `removeIf()` |
| Fail-safe alternative | `CopyOnWriteArrayList` |
