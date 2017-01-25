#`CustomLinkedList<T>` implementation status.

Checked checkbox will mean that feature is implemented, but not if tests are passing. 
 
 ###`Object` interface implementation status: full
 
 - [x] `toString()` method. Tests: passed
 - [x] `equals()` method. Tests: passed
 - [x] `hashCode()` method. Tests: passed. Hashcode of two identical lists agree. 
 
 ###`Collection<T>` interface implementation status: partial
 - [x] `add(T object)` method. Tests: passed
 - [x] `get(int index)` method. Tests: passed 
 
 ##Implemented functions (all):
 - [x] `add(T object)` method. Tests: passed
 - [x] `get(int index)` method. Tests: passed
 - [x] `toString()` method. Tests: passed
 - [x] `equals()` method. Tests: passed
 - [x] `hashCode()` method. Tests: passed. Hashcode of two identical lists agree.
 - [x] keeping track of size. Tests: no tests, but works according to `equals()`, since it relies on size being correct.
 
##TODO:
 - [ ] `add(int index, T object)` method.
 - [ ] `remove(int index)` method. 
 