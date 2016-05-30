package org.luisyang.integration.tools.thread.concurrence;

/**
 *
 * 多个线程，每个线程都可以拿到自已指定的锁，分别获得锁之后，执行synchronized方法体的内容
 * 
 * 总结： 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当作锁，所以示例代码中那个线程先执行
 * synchronized关键字的方法，那个线程就持有该对象的锁（Lock），两个对象，线程获得就是两个不同的锁，他们互不影响。
 * 
 * 有一种情况则是相同的锁，即在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）
 * 
 * 
 * @author ly
 */
public class MultiThread {

	public static int num = 0;

	/** static */
	public static synchronized void printNum(String tag) {

		try {
			if (tag.endsWith("a")) {
				num = 100;
				System.out.println("tag a, set num over!");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b, set num over!");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tag " +tag+",num "+ num);

	}
	
	/** static */
	public static synchronized void printNum2(String tag) {

		try {
			if (tag.endsWith("a")) {
				num = 300;
				System.out.println("tag 2a, set num over!");
				Thread.sleep(1000);
			} else {
				num = 400;
				System.out.println("tag 2b, set num over!");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tag " +tag+",num "+ num);

	}

	public static void main(String[] args) {

		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				m1.printNum("a");
				m1.printNum2("a");
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				m2.printNum("b");
				m2.printNum2("b");
			}
		});
		
		thread1.start();
		thread2.start();

	}

}
