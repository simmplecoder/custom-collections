# custom-collections

The repository contains all of the code that I've written during CSCI152.

## Data structures

Most of the repository is data structures, namely:

 - Doubly Linked List
 - Stack
 - Queue
 - Sorted Queue
 - Deque
 - Map
 - HashSet
 - BSTSet (Binary search tree based set)

All of the data structures are implemented using linked list, except linked list itself. Linked list completely mirrors `java.util.LinkedList<E>`, except `subList()` (the feature was dropped because it was not needed), and was very thoroughely tested. It has proven to be worthy of development time, since every data structure based on it was correct by definition. Also it resulted in more clear, conscise, and even declarative code. Instructor didn't mention this approach, and rewrote the linked list code for every data structure in the lectures, perhaps to make the content of the course more comprehendible.

## Filesystem

The project inherited basic architecture from the code instructor gave, since it was mandatory. Unfortunately, it relied on virtual method `isFolder()` to differentiate between filesystem contents and filesystem itself to navigate it, which heavily severed opportunities for extension. As a result, I decided to focus on development time, and bringing some features such as filesystem navigation through the console, not hardcoded. Console navigation was not mandatory, but I decided to go above and beyond, and implemented linux like navigation, e.g. bare bones `cd`, `ls`, `mkdir`, `touch`, `rmdir`, `rmfile` were implemented.

## Store

Store is an application which has a user console (`Client.java`), which user uses to buy goods (the list is unbounded, e.g. no catalog is maintained), server itself (`ConsoleInterface.java`), which printed on console some useful messages for debugging purposes, and lane consoles (`LaneConsole.java`), to which the bought goods were delegated for further processing. The idea is that there are three consoles which process the requests coming from client console, but the connection is not direct. All of the data is routed through the server so that the server would know if one of the lanes disconnected, or malfunctioning in any way. Also it helped with the boot sequence, since server displayed useful data to stat the store software.

## Custom unit testing framework

During writing of the Linked List, I found a need for a tool that would allow me to write randomized unit tests, which was crucial in creating correct and robust code. The aim was to write something small, so that writing unit test wouldn't be cumbersome. More about it can be read in the `/src/custom/unit/UnitTest class.md`.

## Robot world

The project was mostly about implementing the interfaces and methods instructor provided. The code displays map read from a text file, then has a robot which has current direction and can move either forward or backwards, and change direction. The aim was to implement navigation, movement with checks (e.g. not stepping on walls), and undoing commands.
