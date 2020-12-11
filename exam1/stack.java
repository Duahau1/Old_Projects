import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class stack {

	private int order;
	private PQueue que;
	public stack() {
    	que= new PQueue();
    	order=0;
    }

    public void push(int val){
        que.insert(val, order++);
    }

    public Integer pop(){
        if(que.isEmpty()){
            System.out.println("Stack Underflow");
            return null;
        }
        return (Integer) que.extractMax();
    }
    public Integer peak() {
    	return (Integer) que.maximum();
    }
    
    public static void main(String args[]){
        stack q = new stack();
        q.push(50);
        q.push(12);
        q.push(12);
        q.push(100);
        q.push(112);
        q.push(120);
        q.push(500);
        q.push(120);
        q.push(126);
        
        System.out.println(q.peak());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());

       }

}


