<div style="font-size: 18pt;font-family:'Arial'">
  <center>
  <p style="font-size: 30pt;font-weight: bold;color:#FFFFFF;background-color:#248f24;">
   CS 321: Project
  </p>
  
  <p style="font-size: 28pt;">
   	 Priority Queue<br>
   	 (80 points)
  </p>
  
  <p>
     <i>"Saving the world happens one person at a time. Be at the front of the queue."</i><br>
	 	--Yaya Toure
 </p>
 
  </center>
 
 <h3 style="color:#FFFFFF;background-color:#248f24;">Objectives</h3>
  
  <p>
    </p><ul>
		<li>
			Implement a Max Heap Abtract Data Type (ADT) using an array
		</li>
	    <li>
			Implement a Priority Queue ADT using your Max Heap class
		</li>

		<li>
			Create other classes to support a CPU scheduling simulation 
		</li>
		<li>
			Use a Unified Markup Language (UML) diagram to implement these classes
		</li>
	</ul>
   <p></p>
   
<h3 style="color:#FFFFFF;background-color:#248f24;">Background</h3>

<p>
   For this assignment, you will run a program that simulates a CPU variable-priority, round-robin scheduling routine.  
   This type of CPU scheduling works as follows:
</p> 

<ol> 
   <li> When a process requests time on the CPU, it is assigned a priority rank.</li>
   <br>
   <li> The scheduler arranges all of the processes waiting for CPU time in a queue, the highest priority process first. </li>
   <br>
   <li> 
   		The scheduler assigns a fixed time unit per process, and the highest priority process runs for its assigned time slice.
		<ul> 
   			 <li>If process completes within its time-slice, it is terminated.</li>
			 <li>If not, it rescheduled according to its priority.</li>
		</ul>
   </li>
   <br>	
   <li> 
   		In order to avoid <i>starvation problem</i>, a situation in which a process is perpetually denied the resources necessary to complete its work, 
		 the system will sometimes increment the priority for lower priority processes. 
		<br>
		<br>
		In this simulation, 
		if any process does not get any time slice for a given amount of time, 
		its priority will be incremented by one until it reaches the maximum allowable priority level.
   </li>
</ol>

<h3 style="color:#FFFFFF;background-color:#248f24;">Unified Markup Language (UML) Class Diagrams</h3>

<p>
   For this assignment, you will implement some of the classes in the following UML class diagram: 
</p> 

<center>
   <img src="./files/PQueueUML.png" align="middle">
</center>

<h4>Reading UML Class Diagrams:</h4>
  
  <ul> 
  	   <li>Each box represents a class.</li>
	   <br>
	   <li>
	   	   Each box is labelled with the name of the class. For instance, the class associated with this box: 

<center>
   <img src="./files/ProcessGeneratorUML.png" align="middle">
</center>
	   	   is <tt>ProcessGenerator</tt>. 
	   </li>
	   <br>
	   <li>
	   	   Below the class name is a list of class variables. 
		   <br>
		   <br>
		   For instance, the <tt>ProcessGenerator</tt> class has two variables: 
	   	   <tt>probablity</tt> and <tt>random</tt>. The '-' indicates that the variables are private, and the data type after the ':' symbol is the 
		   data type for the variable. So the <tt>probablity</tt> variable is of type <tt>double</tt> and the <tt>random</tt> variable is 
		   of type <tt>Random</tt>, which is part of the <tt>java.util</tt> library. 
	   	   
	   </li>
  	   <br>
	   <li>
	   	   Below the class variables is a list of methods. 
		   <br>
		   <br>
		   For instance, the <tt>ProcessGenerator</tt> class has four methods: <tt>ProcessGenerator</tt>, a constructor, <tt>query</tt>, <tt>getNewProcess</tt>,
		   and <tt>setProbability</tt>. The '-' again indicates a private access modifier, and the '+' indicates the methods are public. To indicate a protected method 
		   or variable, the symbol '#' is used. 
		   <br>
		   <br>
		   If there any data types in the parentheses after the method name, that indicates the type and number of parameters required by the method. For 
		   instance, the <tt>getNewProcess</tt> method requires three parameters, all of type <tt>int</tt>. The <tt>query</tt> method doesn't have any 
		   data types listed, so it doesn't have any parameters. 
		   <br>
		   <br>
		   The data type list after ':' indicates the data type of the return value. So the <tt>query</tt> method returns a <tt>boolean</tt> value. If there is no
		   return value, the return value is 'void'.  
	   </li>
	   <br>
	   <li>
	   	   The connections between the classes indicate the relationship between the classes. In this instance, all of the relationships are the same. 
		   The black diamond indicates the classes are related by <i>composition</i>. The class closest to the diamond is composed of one or more instances of the other class.
		   For instance, the <tt>PQueue</tt> class is composed of an instance of the <tt>MaxHeap</tt> class. In other words, the <tt>PQueue</tt> class 
		   has an instance variable of type <tt>MaxHeap</tt>. 
		    
	   </li>
  </ul>

<h3 style="color:#FFFFFF;background-color:#248f24;">Tasks</h3>

<ol>
	<li>
		Create a Java class called <tt>MaxHeap</tt>, which represents a Max-Heap ADT.
		<br>
		<br>
		<ul>
			<li>
				Your class should use generics so it can hold objects of any type but maintains type-safety. 
			</li>
			<br>
			<li>
			    Your class should implement a MaxHeap using an array of <tt>HeapNode</tt> objects. 
				The <a href="./files/HeapNode.java" target="blank"><tt>HeapNode</tt> class</a> is provided. 
			</li>
			<br>
			<li>
				Your class should include all of the class variables and methods shown in the UML diagram. You may include 
				others, but at a minimum, you should complete the class in the diagram.  
			</li> 
			<br>
			<li>
				Your class should include two constructors. One constructor is a default constructor, which just creates an empty 
				heap. Your other constructor takes two parameters: an array of <tt>T</tt> objects and an array of <tt>int</tt> values.
				The first array is a list of objects that should be added to the heap, and the other is list of key values associated
				with each object. 
			</li>  
			<br>
			<li>
				Your class should set the <tt>DEFAULT_CAPACITY</tt> variable to 50, and the <tt>expandCapacity</tt> method should double the 
				capacity of the array.  
			</li>
			<br>
			<li>   
			    To test your <tt>MaxHeap</tt>, import the TestNG test classes in <a href="./files/TestNG.zip" target="blank">TestNG.zip</a>, and run the tests in 
				<tt>maxHeap.xml</tt>. When your class passes all of the tests, implement your <tt>PQueue</tt> class. 
			</li>
	 	</ul>
	</li>
	<br>
	<li>
		Create a Java class called <tt>PQueue</tt>, which represents a Priority Queue ADT.	
		<br>
		<br>
		<ul>
			<li>
				Your class should use generics so it can hold objects of any type but maintains type-safety. 
			</li>
			<br>
			<li>
			    Your class should implement a Priority Queue using an instance of the <tt>MaxHeap</tt> class. 
			</li>
			<br>
			<li>
				Your class should include all of the class variables and methods shown in the UML diagram. You may include 
				others, but at a minimum, you should complete the class in the diagram. 
				<br>
				<br>
				 These methods should not contain much code, because most of the funcationality should be provided by its
				 <tt>maxHeap</tt> instance variable. 
			</li> 
			<br>
			<li>
				Your class should also include two constructors. One constructor is a default constructor, which just creates an 
				priority queue. Your other constructor takes two parameters: an array of <tt>T</tt> objects and an array of <tt>int</tt> values.
				The first array is a list of objects that should be added to the priority queue, and the other is list of key values associated
				with each object. 
			</li>  
			<br>
			<li>   
			    To test your <tt>PQueue</tt>, run the TestNG test classes in <tt>pQueue.xml</tt>. 
				When your class passes all of the tests, implement your <tt>Process</tt> class. 
			</li>

		</ul>
    </li> 
	<br>
	<li>
		Create a Java class called <tt>Process</tt>, which models a CPU process.	
		<br>
		<br>
		<ul>
			<li>
				Your class should include all of the class variables and methods shown in the UML diagram. You may include 
				others, but at a minimum, you should complete the class in the diagram. 
			</li>
			<br> 
			<li>
				The constructor for this class should have the following integer parameters: the arrival time, the priority level, the time to finish,
				and the maximum priority level, all in that order.  
			</li>
			<br>
			<li>
				All of the increment and reduce methods should increment or decrement by 1. 
			</li> 
			<br>
			<li>   
				   Create one or more test classes to ensure the class is working correctly. You don't have to turn in these test classes. They're for your use only. 
			</li>
		</ul>
    </li> 
	<br>
	<li>
		Create a Java class called <tt>ProcessGenerator</tt>, which models processes arriving at a CPU.	
		<br>
		<br>
		<ul>
			<li>
				Your class should include all of the class variables and methods shown in the UML diagram. You may include 
				others, but at a minimum, you should complete the class in the diagram. 
			</li> 
			<br>
			<li>
				The <tt>query</tt> method should randomly generate a new double value. If that value is less than the value of the <tt>probability</tt> 
				variable, the method should return <tt>true</tt>. Otherwise, it should return <tt>false</tt>.   
			</li>
			<br>
			<li>  
				The <tt>getNewProcess</tt> should have integer parameters for creating a process: the current time, the maximum process time, and the maximum priority level. 
				The current time should be used at the arrival time of the new process object. To determine the time to finish for the process, you should 
				randomly generate a value with an upper limit of the maximum process time. And for the priority, you should randomly generate a value with the upper
				limit of the maximum priority level. 
			</li>
 			<br>
			<li>   
				   Create one or more test classes to ensure the class is working correctly. You don't have to turn in these test classes. They're for your use only. 
			</li>			 
		</ul>
    </li> 
	<br>
	<li>
		<p>After ensuring all of the classes you have created are working correctly, you should run the provided <tt>CPUScheduler</tt> class. 
		The <tt>Averager</tt> class, which you'll also need to run it, is provided. 
		However, to execute the scheduler, you will have to run it from the command-line on Onyx.</p>
		<ul>
		<li>The <tt>CPUScheduler</tt> program runs using the following command-line statement: 
				<dl>
					<dd style="font-size: 14pt;">
						<tt>java CPUScheduler maxProcessTime maxPriorityLevel timeToIncrementPriority simulationTime processArrivalRate</tt>
					</dd>
				</dl>
        </li>

		<p> where:</p> 
   		<ul>
   	   		<li><tt>maxProcessTime</tt>: largest possible time units required to finish a process.</li>
   	   		<li><tt>maxPriorityLevel</tt>: highest possible priority in this simulation. That is, a process can have a priority, ranging from 1 to the <tt>maxPriorityLevel</tt>.</li>
  	   		<li><tt>timeToIncrementPriority</tt>: if a process hasn't been assigned any CPU time for <tt>timeToIncrementPriority</tt> time units, 
	   	   		 the process's priority will be increased by one.
	       </li>
   	       <li><tt>simulationTime</tt>: the total time units for the simulation.</li>
   	       <li><tt>processArrivalRate</tt>: the probability that a new process will be generated in each time unit.</li>
        </ul>
		<br>
		<li>The output from this run of the program:

		<dl>
			<dd><tt>java CPUScheduler 5 5 5 100 0.4</tt></dd>
		</dl>
	         can be found in a file called <i>sample_result</i>, which is in <i>Tests.zip</i>. 
		</li>
		<li>
			<p>To facilitate running <tt>CPUScheduler</tt>, you can use the provided test script called <tt>run_test</tt>. </p>
			
			<p>To run the script:</p> 
			<ol>
				<li>Copy all test files in <a href="./files/CPUTests.zip" target="blank">CPUTests.zip</a> to this directory. Be sure that the file <tt>run_test</tt> is executable. 
				If it's not, use this command:
				<dl>
					<dd><tt>chmod 775 run_test</tt></dd>
    			</dl>
				</li>
				<br>
				<li>To run the test program, execute the following on the command line:
					   <dl>
					   	   <dd><tt>./run_test probability</tt></dd>
					   </dl>
   				where the argument <tt>probability</tt> can be any value between 0 and 1 (inclusive). </li>

				<p>The results of the CPUScheduler tests are in the file <tt>diff_output</tt>, which should be empty.
			</p></ol>  
		</li>
		 <p>
   	   	 	<i>Tests.zip</i> also contains class files and some test files. These test files are just examples. 
		 	You shouldn't assume that your program will be tested only with these files. 
   		 </p>
		</ul>  
	</li>

	

</ol>

<h3 style="color:#FFFFFF;background-color:#248f24;">Grading</h3>

<p>
   Points will be awarded according to the following breakdown:
</p>


<div style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><table width="70%" border="1" cellpadding="2" cellspacing="2" style="border-color: #000000; border-style: solid; background-color: #ffffff;">
<tbody><tr valign="top">
<td style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span style="font-weight: bold;">Tasks</span></p>
</td>
<td width="61" style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span style="font-weight: bold;">Points</span></p>
</td>
</tr>
<tr valign="top">
<td style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>Includes <a href="http://cs.boisestate.edu/~cs221/references/README.overview">README</a> file, comments</span></p>
</td>
<td width="61" style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: center; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>10</span></p>
</td>
</tr>
<tr valign="top">
<td style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>Functionality of <tt>Process</tt> and <tt>ProcessGenerator</tt> classes</span></p>
</td>
<td width="61" style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: center; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>10</span></p>
</td>
</tr>
<tr valign="top">
<td style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span><tt>PQueue</tt> class functionality</span></p>
</td>
<td width="61" style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: center; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>30</span></p>
</td>
</tr>
<tr valign="top">
<td style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: left; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span><tt>MaxHeap</tt> class functionality</span></p>
</td>
<td width="61" style="border-color : #000000 #000000 #000000 #000000; border-style: solid;"><p style=" text-align: center; text-indent: 0px; padding: 0px 0px 0px 0px; margin: 0px 0px 0px 0px;"><span>30</span></p>
</td>
</tr>
</tbody></table>
</div>

<h3 style="color:#FFFFFF;background-color:#248f24;">Required Files</h3>

<p>
  To receive full credit, all the submitted files should be well documented, and should include program and method headers that
  use common Javadoc notation, as well as appropriate in-line comments.
</p>

<p>
  Submit only the following files:
</p>

<ul>
	<li><i>MaxHeap.java</i></li>
	<li><i>PQueue.java</i></li>
	<li><i>Process.java</i></li>
	<li><i>ProcessGenerator.java</i></li>
	<li><i>README</i> (with no file extension)</li>
</ul>

<h3 style="color:#FFFFFF;background-color:#248f24;">Submission</h3>

<p>
   Submit this assignment electronically using the following command:<br><br>
   
   &nbsp; &nbsp;<tt>submit MhThomas cs321 p-queue</tt><br><br>
   
   Submit all files from the same directory. Do not include any unnecessary files. 
</p>

</div>
