import java.util.Scanner;

public class Reverse {
	static final int LIMIT=10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);

		Integer[] nums= new Integer[LIMIT];
		System.out.println("Enter 10 integers: ");
		for (int i=0; i<LIMIT;i++) {
			nums[i]=scan.nextInt();
		}
		for (int x: nums) {
			System.out.print(x+" ");
		}
		// reverseint
		Integer[] reverse = new Integer[nums.length];
		for (int i=0; i< nums.length;i++) {
			reverse[i]=nums[nums.length-i-1];
		}
		for (int x: reverse) {
			System.out.print(x+ " ");
		}
//		int temp;
//		temp= nums[0];
//		nums[0]= nums[nums.length-1];
//		nums[nums.length-1]=temp;
//		
		for(int i=0; i<nums.length/2;i++) {
int temp;
temp=nums[i];
nums[i]=nums[nums.length-i-1];
nums[nums.length-i-1]=temp;
}
for (int x: nums)
	System.out.println(x+ " ");
	}









}


