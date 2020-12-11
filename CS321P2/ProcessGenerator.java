import java.util.Random;

/**
 * Class models the processes arrives at a CPU. 
 * 
 * @author Van Nguyen 
 *
 *  
 */
public class ProcessGenerator {

	private double probability;
	private Random random;
	/*
	 * The constructor which takes in a probability
	 * @param double x
	 * 
	 */
	public ProcessGenerator(double x) {
		setProbability(x);
	
	}
	/*To generate a random value
	 * @return true if the random number is less than the probility
	 */
	public boolean query() {
		random= new Random();
		double rand= random.nextDouble();
		if(rand<probability) {
			return true;
		}
		else {
			return false;
		}
	}
	/* To create a new process 
	 * @param int current time, int maximum Process Time and int Maximum Priority Level 
	 * @return a new process
	 */
	public Process getNewProcess(int currentTime, int maximumProcessTime, int maximumPriorityLevel) {
		int timeToFinish = random.nextInt(maximumProcessTime) + 1;
		int priorityLevel = random.nextInt(maximumPriorityLevel) + 1;
		Process pro = new Process(currentTime,timeToFinish,priorityLevel);
		pro.setMaxPriorityLevel(maximumPriorityLevel);
		return pro;
	}
	/*
	 * To set the probability of the process
	 * @param double prop
	 */
	private void setProbability(double prop) {
		probability=prop;
	}

}
