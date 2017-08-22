package com.cn.saint.concurrent;

import java.util.concurrent.CountDownLatch;


/**
 *  countdownlatch 
 *  
 *  利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他若干个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了.
 *  
 *  CyclicBarrier 
 *  
 *  假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，这些线程才能继续做后面的事情，此时就可以利用CyclicBarrier了
 *
 *
 *  CountDownLatch 是能使一组线程等另一组线程都跑完了再继续跑。
 *  CyclicBarrier 能够使一组线程在一个时间点上达到同步，可以是一起开始执行全部任务或者一部分任务。同时，它是可以循环使用的；
 *  Semaphore 是只允许一定数量的线程同时执行一段任务
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(){
			public void run(){
				System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
			}
		}.start();
		
		new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(2000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            };
        }.start();
        
        System.out.println("等待2个子线程执行完毕");
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("2个子线程执行完毕");
        System.out.println("继续执行主线程任务");
	}
}
