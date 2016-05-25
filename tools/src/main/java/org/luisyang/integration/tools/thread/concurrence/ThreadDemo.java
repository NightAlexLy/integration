package org.luisyang.integration.tools.thread.concurrence;

/**
 * 并发测试类
 * 
 * 		线程安全的概念：当多个线程访问某一个类（对象或方法）时,这个类始终都能表现出正确的行为。
 * 			那么这个类是线程安全的。
 * 
 * 		synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为“互斥区”或者“临界区“。
 * 
 * @author ly
 */
public class ThreadDemo extends Thread {

	private static int count = 5;
	
	@Override
	@SuppressWarnings("static-access")
	public synchronized void run() {
		// TODO Auto-generated method stub
		count --;
		System.out.println("Thread Name :::"+ this.currentThread().getName() +"... Count:::" + count);
	}
	
	public static void main(String[] args) {
		
		ThreadDemo demo = new ThreadDemo();
		Thread t1 = new Thread(demo, "t1");
		Thread t2 = new Thread(demo, "t2");
		Thread t3 = new Thread(demo, "t3");
		Thread t4 = new Thread(demo, "t4");
		Thread t5 = new Thread(demo, "t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}
	
	
	/**
	 * 示例总结：
	 * 		
	 * 		当多个线程访问ThreadDemo的run()方法时，以排队的方式进行处理（这里排队是按照CPU分配的先后顺序而定的），
	 * 			一个线程想要执行synchronized修饰的方法里的代码，首先是尝试获得锁，如果拿到锁，执行synchronized代码
	 * 			体内容：拿不到锁，这个线程会不断的尝试获得这把锁，直到拿到为止。而且是多个线程同时去竞争这把锁。（也就是会
	 * 			有锁竞争的问题）。
	 * 
	 * 
	 * 
	 */
}
