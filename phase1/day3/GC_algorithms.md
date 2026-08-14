# Garbage Collectors

### Serial GC

- Single threaded
- Stops the world pause
- Usable for small apps
- Flag: `-XX:+UseSerialGC`

### parallel GC

- multi-threaded
- Shorter stop the world pauses
- Good if pauses < throughput
- Good for backend jobs
- Flag: `-XX:+UseParallelGC`
- Was default in Java8

### CMS (Concurrent Mark and Sweep)

- Collects Old generation concurrently
- minimal pauses
- uses mark-sweep (no compaction)
- Deprecated in java9 removed in java14

### G1

- uses it own heap distribution
- divides into equal regions, each labeled as E, S or O,
- set each region size by `-XX:G1HeapRegionSize`
- each region have a RSet.
- **Remembered Set (RSet):** contains the indices of cards in other regions those points into the region the RSet belongs to.
- Flag: `-XX:+UseG1GC`

### ZGC

- works concurrently
- Divided into different sized Zpages
- uses color pointer - additional information (GC state) attached in object's reference
- a load barrier redirects to the new location of moved object
- minimum stop the world pause `< 10ms`
- Flag: `-XX:+UseZGC`

# Task

Ran code with java -Xlog:gc GCDemo
```logs
PS E:\javaProgress> java -Xlog:gc  phase1/day3/GCDemo
[0.014s][info][gc] Using G1
[0.122s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 29M->1M(130M) 1.497ms
[0.190s][info][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 59M->1M(130M) 1.090ms
[0.263s][info][gc] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 76M->1M(130M) 1.115ms
[0.327s][info][gc] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 76M->1M(130M) 1.267ms
```

### Observations

- by default uses G1
- In test all created objects were instant garbage, so never promoted to servivor and old.
- `Pause Young (normal)` means ran on young generation
- `(G1 Evacuation Pause)` says everything is paused `(STW)`
- `29M -> 1M` claimed 28M
- STW of 1.497ms
