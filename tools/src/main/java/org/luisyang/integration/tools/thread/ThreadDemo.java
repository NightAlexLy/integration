package org.luisyang.integration.tools.thread;

/**
 * 实现线程的两种方式：
 * 	1.实现Runnable接口
 *  2.继承Thread类重写run方法
 * @author ly
 */
public class ThreadDemo {

	public static void main(String[] args) {
		
		
		new Thread(new RunnableTest()).start();
		
		new ThreadTest().start();
		
		/**
		 
		   从实际出发，实现Runnable比继承Thread的方式各加方便，因为在Java中，一个类只能继承一个父类但是可以有个实现类，
		   更加方便，可扩展。
		  
		 */
	}
	
	
}


class RunnableTest  implements Runnable{

	@Override
	public void run() {
		
		System.out.println("Runnable Test.....");
		
	}
	
}


class ThreadTest extends Thread{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread Test.....");
		
	}
}



