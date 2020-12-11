import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class queue {

	private int order;
	private PQueue que;
	public queue() {
    	que= new PQueue();
    	order=100;
    }

    public void enqueue(int val){
        que.insert(val, order--);
    }

    public Integer dequeue(){
        if(que.isEmpty()){
            System.out.println("Stack Underflow");
            return null;
        }
        return (Integer) que.extractMax();
    }
    
    
    public static void main(String args[]){
        queue q = new queue();
        q.enqueue(50);
        q.enqueue(12);
        q.enqueue(12);
        q.enqueue(100);
        q.enqueue(112);
        q.enqueue(120);
        q.enqueue(500);
        q.enqueue(120);
        q.enqueue(126);
        
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

       }

}


