/**
 * Class models the CPU process
 * 
 * @author Van Nguyen 
 *
 *  
 */
public class Process {
	private int priorityLevel, 
	timeToFinish,
	timeNotProcessed,
	arrivalTime,
	maxPriorityLevel;
/*
 * Constructor that creates a process 
 * @param int arrival Time, the priority level and the time it needs to finish
 */
	public Process(int arrivalTime, int priorityLevel, int timeToFinish) {
		this.arrivalTime=arrivalTime;
		this.priorityLevel=priorityLevel;
		this.timeToFinish=timeToFinish;
		timeNotProcessed = 0;
	}
	/*
	 * To reduce the Time remaining
	 */
	public void reduceTimeRemaining() {
		timeToFinish--;
	}
	/*
	 * To increase the not processing time
	 */
	public void incrementTimeNotProcessed() {
		timeNotProcessed++;
	}
	/*To get the not processing time
	 * @return int the not processing time
	 */
	public int getTimeNotProcessed() {
		return timeNotProcessed;
	}
	/*To get the remaining time
	 * @return int the remaining time
	 */
	public int getTimeRemaining() {
		return timeToFinish- timeNotProcessed;
	}
	/*To see if the process is done
	 * @return true if the process is done
	 */
	public boolean done() {
		return (timeToFinish==0);
	}

	/*To get the arrival time
	 * @return int the arrival time
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	/*To get the priority level
	 * @return int the priority level
	 */
	public int getPriority() {
		return priorityLevel;
	}
	/*
	 * to increment the priority level only if it is smaller than the max
	 * priority level
	 */
	public void incrementPriority() {
	if(priorityLevel<maxPriorityLevel) {
		priorityLevel++;
	}
		
	}
	/*
	 * To reset the time not process
	 */
	public void resetTimeNotProcessed() {
		timeNotProcessed=0;
	}
	/*
	 * To set the priority level of a process
	 * @param int new level 
	 */
	private void setPriorityLevel(int newlevel) {
		priorityLevel=newlevel;
	}
	/*To get the time to finish
	 * @return int the time to finish
	 */
	public int getTimeToFinish() {
		return timeToFinish;
	}
	/*
	 * To set the new time to finish the process
	 * @param int new time 
	 */
	private void setTimeToFinish(int newtime) {
		timeToFinish=newtime;
	}
	/*
	 * To set the new time not process
	 * @param int new time 
	 */
	private void setTimeNotProcessed(int newtime) {
		timeNotProcessed= newtime;
	}
	/*
	 * To set the new arrival time of a process
	 * @param int new time
	 */
	private void setArrivalTime(int newtime) {
		arrivalTime=newtime;
	}
	/*To get the max priority level
	 * @return int the not processing time
	 */
	public int getMaxPriorityLevel() {
		return maxPriorityLevel;
	}
	/*
	 * To set the max priority level of a process
	 * @param int new level 
	 */
	public void setMaxPriorityLevel(int newlevel) {
		maxPriorityLevel= newlevel;
	}


}
