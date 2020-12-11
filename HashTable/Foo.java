
public class Foo extends Thread {

	public static void main(String[] args) {
		//descending(200);
		Foo t1= new Foo();
		Foo t2 = new Foo();
		t1.start();
		t2.start();
	}
public void run() {
	while(serverIsRunning) {
		
	}
}
	
public static void descending(int num) {
	System.out.println(num);
	if(num==1) {
	}
	else {
		descending(num-1);
	}

}
public static int expo(int x, int m  ) {
	// x mu m 
	if(m==0) {
		return 1;
	}
	return expo(x, m-1)*x;
}


}
