
public class ThreadExample extends Thread{

	public void run() {
		int i=1;
		while(i<10) {
			System.out.println("Thread printing: "+i*5);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i++;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadExample server = new ThreadExample();

		server.start();

		int i = 1;
		while (i < 10) {

			System.out.println("Main printing: " + i * 10);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}

	}

}
