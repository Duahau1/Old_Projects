
public class Excersises {
	private static Queue<Integer> quet1;
	private static Queue<Character> quet2;
	private static Stack<Character> test ;
	private static Stack<Integer> base1 ;
	private static Stack<Integer> base2 ;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		quet1= new Queue<Integer>();
		quet2= new Queue<Character>();
		test= new Stack<Character>();
		base1= new Stack<Integer>();
		base2= new Stack<Integer>();
		int base=8;
		int num=95;
		//System.out.println("working baba");
		quet1.enqueue(1);
		quet1.enqueue(2);
		quet1.enqueue(3);
		quet2.enqueue('a');
		quet2.enqueue('b');
		quet2.enqueue('c');
		
		if(args[0].equals("s")) {
			System.out.println("working baba");
			stutter(quet1);

		}
		else if(args[0].equals("m")) {
			mirror(quet2);

		}
		else if(args[0].equals("b")) {
			baseConversion(base,num);

		}
		String expression ="A+B*C/D-E";
		quet1= new Queue<Integer>();
		quet2= new Queue<Character>();
		test= new Stack<Character>();
		base1= new Stack<Integer>();
		base2= new Stack<Integer>();

		
		//stutter(quet1);
		mirror(quet2);	
		//baseConversion(8,95);
	}
	public static void stutter(Queue<Integer> queue) {
		int temp =queue.size();
		for (int i=1;i<=temp;i++) {
			queue.enqueue(queue.front());
			queue.enqueue(queue.front());
			queue.dequeue();
		}
		int temps =queue.size();
		for(int i=1; i<=temps;i++) {
			System.out.println(queue.front());
			quet1.dequeue();
		}
	}
	public static void mirror(Queue<Character> queue) {
		int temp =queue.size();
		for (int i=1;i<=temp;i++) {
			test.push((Character) queue.dequeue());
		}
		for (int i=1;i<=temp;i++) {
			queue.enqueue(test.pop());
		}
		int temps=quet2.size();

		for(int i=1; i<=temps;i++) {
			System.out.println(queue.front());
			quet2.dequeue();
		}		
	}
	public static void baseConversion(int base, int num) {
		int div;
		while(num>0) {
			div=num%base;
			base1.push(div);
			num=num/base;
		}
		int tr=base1.size();

		for(int i=1;i<=tr;i++) {
			System.out.print(base1.pop());
		}

	}
	


}
