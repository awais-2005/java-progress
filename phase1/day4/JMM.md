# Java Memory Model (JMM)

- **Visibility Issue:** often caused by cache when changes are not flush and fresh value is not refetched!
- **Instructions Reordering** done by compiler, JIT compiler, cpu for optimization purposes as long as it does not effect output.
- **happens-before** is terminology as it is read, if A happen before B, B will be aware of everything A did.
- `volatile` keyword, simple fix for visibility issue caused by cache and reordering, volatile field act like a wall, everything that comes before volatile field happens before volatile field. instructions which come before volatile field are never reordered in a way that, any of them will be executed after volatile field. even if it does not effect output of a single thread. **it does not give atomicity** means multiple threads performing action on a volatile field will cause bugs.
- `synchronized` fixes both issues visiblity and atomicity
- **Atomicity:** An operation is atomic if it is a single, indivisible step for all other threads.
- `java.util.concurrent.atomic` package has wrapper classes for int, long, boolean, hands you atomicity with Compare-And-Swap (CAS)

## Task

#### without `volatile`

- **main:** run
- **main:** start mythread (it sleeps for 1 second)
- **main:** enters loop
- **main:** jvm flags it as hot code so JIT comes in and caches variables like counter and stop along with other optimizations.
- **mythread:** sets `stop = true`
- **main:** never refetches updated value of stop

#### with `volatile`

- when mythread set `stop = true` main thread instantly reads it and stops.

