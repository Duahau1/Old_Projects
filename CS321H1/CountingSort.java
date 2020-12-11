
public class CountingSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int temp[] =new int[5];
		temp[0]=0;
		temp[1]=5;
		temp[2]=8;
		temp[3]=12;
		temp[4]=12;
		try {
			System.out.println("The number of integer in range A1 is "+findNumIn(temp,0,3)+"\n");
			System.out.println("The number of integer in range A2 is "+findNumIn(temp,1,4)+"\n");
			System.out.println("The number of integer in range A3 is "+findNumIn(temp,1,2)+"\n");
			System.out.println("The number of integer in range A4 is "+findNumIn(temp,3,4)+"\n");
			System.out.println("The number of integer in range A5 is "+findNumIn(temp,0,1)+"\n");
			System.out.println("The number of integer in range A6 is "+findNumIn(temp,2,3)+"\n");
			System.out.println("The number of integer in range A7 is "+findNumIn(temp,0,0)+"\n");
			System.out.println("The number of integer in range A8 is "+findNumIn(temp,1,1)+"\n");
			System.out.println("The number of integer in range A9 is "+findNumIn(temp,2,2)+"\n");
			System.out.println("The number of integer in range A9 is "+findNumIn(temp,3,3)+"\n");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//bound b must be the maximum that have a number

	public static int findNumIn(int C[],int a, int b ){
		int count= b-a+1;
		if (a>b) {
			System.out.println("Haha");
		}

		if (count==1) {
			//case 1.1
			if (a==0&&C[a]==C[a+1]) {
				count=0;
			}
			//case 1.2
			if(a>0 && a<C.length-1) {
				if (C[a]==C[a+1]) {
					count=0;
				}
			}
			//case 1.3
			if(a==C.length-1) {
				count=1;
			}
		}
		else {
			for(int j = a; j<=b;j++) {
				//case 1
				if(a>0 && b<C.length-1) {
					if (C[j]==C[j+1]) {
						count--;
					}
				}
				//case 2
				else if(a>0 && b==C.length-1&&j<b) {
					if(C[j]==C[j+1]) {
						count--;
					}
				}
				//case 3
				else if(a==0 && b==C.length-1 && j<b) {
					if (C[j]==C[j+1]) {
						count--;
					}

				}
				//case 4
				else if(a==0 && b<C.length-1) {
					if(C[j]==C[j+1]) {
						count--;
					}

				}

			}
		}
		return count;
	}


}
