
****************

* Project 3: HashTable

* Class : CS321

* Date : 10/27/2018

* Name:Van Nguyen

**************** 



OVERVIEW:

In this project, we used an array to implement the Hash Table ADT, then we used the Hash Table ADT to store data scanned from files. 


INCLUDED FILES:

* HashObject.java : A class that represents an object with a value and a key.

* HashTest.java: The driver class that takes in arguments and print out storing statements.

* HashTable.java: The class that implements array of hash objects to store data

* OpenAddressType.java: The enum class that contains 3 types of hashing

* README : this file.

COMPILING AND RUNNING:

From the console, do the following commands:
$javac OpenAddressType.java
$ javac HashTest.java
$javac HashObject.java
$javac HashTable.java
Then to run the program enter valid arguments in the form
$ java HashTest [load factor] [hash table capacity] [input file] [input type] [ | debug level]

PROGRAM DESIGN AND IMPORTANT CONCEPTS:
This program is designed to implement the Hash Table to store data with different input type. The hash table will give us the average number of probes 
as well as the frequency when the same hash object has been accessed as well as number of duplicates. Duplicate is only counted when the value and the key of the object
is the same as one another. Additionally, this project supports all 3 types of opening hashing: linear, double hashing, quadratic. 

TESTING:

I tested the program on eclipse using the provided TestNG(HashTable folder) which will show you what you probably did wrong in the 
2 required classes. Then I used the run configuration in Eclipse to run the arguments 
 [load factor] [hash table capacity] [input file] [input type] [ | debug level] and check them with all the test files provided under the HashTest folder
DISCUSSION:
During the process of working on this project, I bumped into some problems as the duplicates are not counted correctly and the average number of probes did not work 
as I expected. Then, when I run the xml file test on the HashTable, there are some failed tests on the getNumProbes() method. I spent at least 2 hours try to debug and
fix it and the results go as the same as the given tested files from the HashTest folder. 
It was really fascinating to learn how if you create your own HashObject class the .equals() method only compares the memory addresses and it is
really necessary to overwrite the .equals() method with your own method to compare the elements and the key.

 
EXTRA CREDIT:

 I did the Quadratic open hashing . 

