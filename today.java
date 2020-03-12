import java.util.*;

class Printer{
	 synchronized void printDocuments(int num, String docName){
		
		for(int i = 0; i <= num; i ++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Printing " + docName + "  " + i);
		}
	}
}
class MyTask extends Thread{
	Printer pRef;
	MyTask(Printer p){
		pRef = p;
	}
	public void run() {
			pRef.printDocuments(10, "MyTask..");
	}
}
class YourTask extends Thread{
	Printer pRef;
	YourTask(Printer p){
		pRef = p;
	}
	public void run() {
			pRef.printDocuments(10, "YourTask..");
	}
}
public class today {

	public static void main(String[]args) {
		System.out.println("application started...");
		Printer pr = new Printer();
		MyTask mytask = new MyTask(pr);
		YourTask yourtask = new YourTask(pr);
		mytask.start();
		
		yourtask.start();
		System.out.println("application ended...");
	
	}
}
