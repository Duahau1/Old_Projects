
public class Solution {
public	static boolean isSubsetSum(int set[], 
            int n, int sum) 
{ 
// Base Cases 
if (sum == 0 && n!=set.length) {
	System.out.println("true");
return true; }
if (n == 0 && sum != 0) {
	return false; 
}

// If last element is greater than  
// sum, then ignore it 
if (set[n-1] > sum) 
return isSubsetSum(set, n-1, sum); 

/* else, check if sum can be obtained  
by any of the following 
(a) including the last element 
(b) excluding the last element */
return isSubsetSum(set, n-1, sum) ||  
isSubsetSum(set, n-1, sum-set[n-1]); 
}
   
    public static void main (String []args) {
    	int a[]= {-3,-2,-1,2,5};
    	isSubsetSum(a,5,0);
    }


}