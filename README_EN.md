# Java-DS-Core-Java- 

This project is an independent implementation of the core data structure labs from UC Berkeley's classic computer science course, **CS61B (Data Structures)**. 

While it replicates academic course labs, the core objective of this project remains to **build a high-quality, foundational Java data structures library**. By building these advanced components from scratch, this project aims to provide a deep, source-code-level understanding of the underlying principles of Disjoint Sets, Binary Search Trees, and Self-Balancing Trees. This foundational knowledge is crucial for mastering the internal mechanisms of the JDK Collections Framework (e.g., `TreeMap`, `TreeSet`).

## Core Modules (Completed Labs)

This project primarily encompasses the complete implementation of the following three core data structure modules:

### 1. Disjoint Sets
* **Overview**: Implemented a highly efficient data structure for solving dynamic connectivity problems.
* **Key Features**:
  * Supports rapid querying to determine if two elements belong to the same set (`isConnected`).
  * Supports efficiently merging two distinct sets (`connect`).
  * **Optimization Strategies**: Utilized advanced optimization techniques internally, including **Union-by-Size** and **Path Compression**, ensuring the amortized time complexity for nearly all operations approaches $O(1)$.

### 2. BSTMap
* **Overview**: A custom dictionary/map structure implemented based on a Binary Search Tree (BST).
* **Key Features**:
  * Implemented core APIs analogous to the JDK `Map` interface (e.g., `put`, `get`, `containsKey`, `size`, `clear`).
  * Solidified understanding of recursive and non-recursive tree traversal, as well as node insertion and search logic.
  * Serves as the foundational stepping stone for implementing advanced self-balancing trees (LLRBs) in subsequent labs.

### 3. Left-Leaning Red-Black Trees (LLRBs)
* **Overview**: Implemented the Left-Leaning Red-Black Tree, an equivalent structure to the 2-3 tree, representing one of the most challenging concepts in advanced data structures.
* **Key Features**:
  * Strictly maintains tree balance through core self-balancing mechanisms, including `rotateLeft`, `rotateRight`, and `colorFlip`.
  * Resolves the worst-case scenario of standard BSTs where the tree degenerates into a linked list.
  * Ensures that regardless of the data insertion order, the tree height remains logarithmic. This guarantees that the worst-case time complexity for all core operations (insert, delete, search) is strictly bounded to $O(\log N)$.

## Environment & Execution

* **Language**: Java 17+ *(adjust according to your actual version)*
* **Testing**: Utilized JUnit to write and execute unit tests targeting various edge cases and boundary conditions of the implemented data structures.
* **External Libraries**: Uses the official CS61B provided library-sp24.
