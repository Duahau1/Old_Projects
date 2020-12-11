import java.util.Arrays;

public class test {
	private static int maxIndex(int[] ar) {
		int l=0;
		int r=ar.length-1;
		if(l==r) {
			return l;
		}
		else {
			int piv=(int) Math.floor((l+r)/2);
			int term1=	maxIndex(Arrays.copyOfRange(ar,l, piv));
			int term2=	maxIndex(Arrays.copyOfRange(ar,piv+1, r));
					if(ar[term1]>=ar[term2]) {
						return term1;
					}
					else {	
						return term2;	
					}
		}
	}

	public void main(String[] args) {

		int ar[]= {1,3,5};
		//maxIndex(ar);
		System.out.println("lala");
	}

}
