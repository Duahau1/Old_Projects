CS 321: Project

Priority Queue
(80 points)

"Saving the world happens one person at a time. Be at the front of the queue."
--Yaya Toure

Objectives
Implement a Max Heap Abtract Data Type (ADT) using an array
Implement a Priority Queue ADT using your Max Heap class
Create other classes to support a CPU scheduling simulation
Use a Unified Markup Language (UML) diagram to implement these classes
Background
For this assignment, you will run a program that simulates a CPU variable-priority, round-robin scheduling routine. This type of CPU scheduling works as follows:

When a process requests time on the CPU, it is assigned a priority rank.

The scheduler arranges all of the processes waiting for CPU time in a queue, the highest priority process first.

The scheduler assigns a fixed time unit per process, and the highest priority process runs for its assigned time slice.
If process completes within its time-slice, it is terminated.
If not, it rescheduled according to its priority.

In order to avoid starvation problem, a situation in which a process is perpetually denied the resources necessary to complete its work, the system will sometimes increment the priority for lower priority processes.

In this simulation, if any process does not get any time slice for a given amount of time, its priority will be incremented by one until it reaches the maximum allowable priority level.
Unified Markup Language (UML) Class Diagrams
For this assignment, you will implement some of the classes in the following UML class diagram:


Reading UML Class Diagrams:
Each box represents a class.

Each box is labelled with the name of the class. For instance, the class associated with this box:

is ProcessGenerator.

Below the class name is a list of class variables.

For instance, the ProcessGenerator class has two variables: probablity and random. The '-' indicates that the variables are private, and the data type after the ':' symbol is the data type for the variable. So the probablity variable is of type double and the random variable is of type Random, which is part of the java.util library.

Below the class variables is a list of methods.

For instance, the ProcessGenerator class has four methods: ProcessGenerator, a constructor, query, getNewProcess, and setProbability. The '-' again indicates a private access modifier, and the '+' indicates the methods are public. To indicate a protected method or variable, the symbol '#' is used.

If there any data types in the parentheses after the method name, that indicates the type and number of parameters required by the method. For instance, the getNewProcess method requires three parameters, all of type int. The query method doesn't have any data types listed, so it doesn't have any parameters.

The data type list after ':' indicates the data type of the return value. So the query method returns a boolean value. If there is no return value, the return value is 'void'.

The connections between the classes indicate the relationship between the classes. In this instance, all of the relationships are the same. The black diamond indicates the classes are related by composition. The class closest to the diamond is composed of one or more instances of the other class. For instance, the PQueue class is composed of an instance of the MaxHeap class. In other words, the PQueue class has an instance variable of type MaxHeap.
Tasks
Create a Java class called MaxHeap, which represents a Max-Heap ADT.

Your class should use generics so it can hold objects of any type but maintains type-safety.

Your class should implement a MaxHeap using an array of HeapNode objects. The HeapNode class is provided.

Your class should include all of the class variables and methods shown in the UML diagram. You may include others, but at a minimum, you should complete the class in the diagram.

Your class should include two constructors. One constructor is a default constructor, which just creates an empty heap. Your other constructor takes two parameters: an array of T objects and an array of int values. The first array is a list of objects that should be added to the heap, and the other is list of key values associated with each object.

Your class should set the DEFAULT_CAPACITY variable to 50, and the expandCapacity method should double the capacity of the array.

To test your MaxHeap, import the TestNG test classes in TestNG.zip, and run the tests in maxHeap.xml. When your class passes all of the tests, implement your PQueue class.

Create a Java class called PQueue, which represents a Priority Queue ADT.

Your class should use generics so it can hold objects of any type but maintains type-safety.

Your class should implement a Priority Queue using an instance of the MaxHeap class.

Your class should include all of the class variables and methods shown in the UML diagram. You may include others, but at a minimum, you should complete the class in the diagram.

These methods should not contain much code, because most of the funcationality should be provided by its maxHeap instance variable.

Your class should also include two constructors. One constructor is a default constructor, which just creates an priority queue. Your other constructor takes two parameters: an array of T objects and an array of int values. The first array is a list of objects that should be added to the priority queue, and the other is list of key values associated with each object.

To test your PQueue, run the TestNG test classes in pQueue.xml. When your class passes all of the tests, implement your Process class.

Create a Java class called Process, which models a CPU process.

Your class should include all of the class variables and methods shown in the UML diagram. You may include others, but at a minimum, you should complete the class in the diagram.

The constructor for this class should have the following integer parameters: the arrival time, the priority level, the time to finish, and the maximum priority level, all in that order.

All of the increment and reduce methods should increment or decrement by 1.

Create one or more test classes to ensure the class is working correctly. You don't have to turn in these test classes. They're for your use only.

Create a Java class called ProcessGenerator, which models processes arriving at a CPU.

Your class should include all of the class variables and methods shown in the UML diagram. You may include others, but at a minimum, you should complete the class in the diagram.

The query method should randomly generate a new double value. If that value is less than the value of the probability variable, the method should return true. Otherwise, it should return false.

The getNewProcess should have integer parameters for creating a process: the current time, the maximum process time, and the maximum priority level. The current time should be used at the arrival time of the new process object. To determine the time to finish for the process, you should randomly generate a value with an upper limit of the maximum process time. And for the priority, you should randomly generate a value with the upper limit of the maximum priority level.

Create one or more test classes to ensure the class is working correctly. You don't have to turn in these test classes. They're for your use only.

After ensuring all of the classes you have created are working correctly, you should run the provided CPUScheduler class. The Averager class, which you'll also need to run it, is provided. However, to execute the scheduler, you will have to run it from the command-line on Onyx.

The CPUScheduler program runs using the following command-line statement:
java CPUScheduler maxProcessTime maxPriorityLevel timeToIncrementPriority simulationTime processArrivalRate
where:

maxProcessTime: largest possible time units required to finish a process.
maxPriorityLevel: highest possible priority in this simulation. That is, a process can have a priority, ranging from 1 to the maxPriorityLevel.
timeToIncrementPriority: if a process hasn't been assigned any CPU time for timeToIncrementPriority time units, the process's priority will be increased by one.
simulationTime: the total time units for the simulation.
processArrivalRate: the probability that a new process will be generated in each time unit.

The output from this run of the program:
java CPUScheduler 5 5 5 100 0.4
can be found in a file called sample_result, which is in Tests.zip.
To facilitate running CPUScheduler, you can use the provided test script called run_test.

To run the script:

Copy all test files in CPUTests.zip to this directory. Be sure that the file run_test is executable. If it's not, use this command:
chmod 775 run_test

To run the test program, execute the following on the command line:
./run_test probability
where the argument probability can be any value between 0 and 1 (inclusive).
The results of the CPUScheduler tests are in the file diff_output, which should be empty.

Tests.zip also contains class files and some test files. These test files are just examples. You shouldn't assume that your program will be tested only with these files.

Grading
Points will be awarded according to the following breakdown:

Tasks

Points

Includes README file, comments

10

Functionality of Process and ProcessGenerator classes

10

PQueue class functionality

30

MaxHeap class functionality

30

Required Files
To receive full credit, all the submitted files should be well documented, and should include program and method headers that use common Javadoc notation, as well as appropriate in-line comments.

Submit only the following files:

MaxHeap.java
PQueue.java
Process.java
ProcessGenerator.java
README (with no file extension)
Submission
Submit this assignment electronically using the following command:

   submit MhThomas cs321 p-queue

Submit all files from the same directory. Do not include any unnecessary files.
