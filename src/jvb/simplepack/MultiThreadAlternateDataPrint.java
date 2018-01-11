//Communication between two threads:
//Create a custom class 'ParentForSynchData' with some data members.
//mention one data member of 'ParentForSynchData' in each Runnable class
//In Main, Create one instance of the class and share across all Runnable instances(through constructors)
//And enable communication between threads to alternately print Hi & Hello (in the same sequence) 5 times.

public class MultiThreadAlternateDataPrint {
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		ParentForSynchData syncData = new ParentForSynchData(0,true);
		ThreadingOne oneObj = new ThreadingOne(syncData);
		ThreadingTwo twoObj = new ThreadingTwo(syncData);
		Thread t1 = new Thread(oneObj);
		Thread t2 = new Thread(twoObj);
		t1.start();
		t2.start();
	}
}
class ParentForSynchData {
	public int counter;
	public boolean changed;
	public ParentForSynchData(int a,boolean b){
		counter = a;
		changed = b;
	}
}
class ThreadingOne implements Runnable {
	
	/*public ThreadingOne(){
		super()
	}*/
	private ParentForSynchData syncData;
	public ThreadingOne(ParentForSynchData a){
		syncData = a;
	}
	public void run(){
		//synchronized(counter){
			for(int i=0;i<5;i++){
			if(syncData.changed){
				System.out.println("Hi");
				syncData.changed = false;
			}
			
			try{
			Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
			}
	//	}
	}
}

class ThreadingTwo implements Runnable {
	/*public ThreadingTwo(int a,boolean b){
		super(a,b);
	}*/
	private ParentForSynchData syncData;
	public ThreadingTwo(ParentForSynchData b){
		syncData = b;
	}
	public void run(){
		//synchronized(counter){
		    for(int i=0;i<5;i++){
			System.out.println("Hello");
			syncData.changed = true;
			try{
			Thread.sleep(1000);
			}catch(InterruptedException e){
				
			}
		    }
	//	}
	}
}
