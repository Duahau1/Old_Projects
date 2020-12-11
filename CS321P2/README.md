
****************

* Project 2: Priority Queue

* Class : CS321

* Date : 09/21/2018

* Name:Van Nguyen

**************** 



OVERVIEW:

In this projec, we used an array to implement the Max Heap ADT, then we used the Max Heap ADT to implement the Priority Queue.
Additionally, we have many other clasess that help to implement the CPU scheduler program.


INCLUDED FILES:

* MaxHeap.java : A class that represent the max heap in a heap ADT

* PQueue.java: The class that represent the priority queue ADT

* Process.java: The model for CPU process

* ProcessGenerator.java: The class models the process arrives at a CPU 

* README : this file.

COMPILING AND RUNNING:

From the console, do the following commands:
$ javac CPUScheduler.java
Then to run the program enter valid arguments in the form
$ java CPUScheduler [maxProcessTime] [maxPriorityLevel] [timeToIncrementPriority] [simulationTime] [processArrivalRate]

PROGRAM DESIGN AND IMPORTANT CONCEPTS:
This program is designed to implement the maxHeap ADT using array and apply the maxheap ADT to implement priority
queue. A queue is where you are holding many processes that are waiting to be processed in turn with scheduled time
based on their priority. 

TESTING:

I tested the program on eclipse using the provided TestNG which will show you what you probably did wrong in the 
2 required classes. Then I used the run configuration in Eclipse to run the arguments 
 [maxProcessTime] [maxPriorityLevel] [timeToIncrementPriority] [simulationTime] [processArrivalRate]
DISCUSSION:
 
During the process of working on the max heap class, I bumped into a great deal of problem at first since in 
the book the max heap started at 1 so I had to adjust the array so that the max will be at position 1. 
There are sometimes in my methods I forgot that I skip position 0 so NULL Pointer exceptions appear so many times
in the TestNG. Thanks to the TestNG tests, I was able to find all of my mistakes. Also, it took me 2 hours to 
understand what I am supposed to do with the class Process.java and ProcessGenerator.java. 
 
EXTRA CREDIT:

 None

