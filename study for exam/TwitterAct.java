import java.util.ArrayList;
import java.util.Scanner;

public class TwitterAct {
	private String id="";
	private String email="";
	private ArrayList<String> hashtags= new ArrayList<String>();
	private String knope="n";
	private String tag="";
	Scanner scan= new Scanner(System.in);
	public TwitterAct(String id, String email) {
		this.id=id;
		this.email=email;
		System.out.print(" what is your hastags?if you feel like stop, type in n");
		String v=scan.nextLine();
	
		while(!v.equals(knope)) {
			System.out.print(" what is your hastags?if you feel like stop, type in\"knope \"");
			hashtags.add(v);
			v=scan.nextLine();
		
			}
		}
	public boolean checkhastags(String tag) { 
	this.tag=tag;
		if(hashtags.contains(tag)) {
		System.out.println("true");
			return true;
		}
		else {
		return false;
		}
		
		
	}
	public String toString() {
		String s="Your id" +id +" " + " Your email"+email ;
		for(String h: hashtags) {
			
			System.out.println("#"+h);
		}
		return s;
	}

}