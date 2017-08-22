package com.cn.saint.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		int n = 4; 
		CyclicBarrier barrier = new CyclicBarrier(n, new Runnable(){
			@Override
			public void run() {
				System.out.println("当前线程"+Thread.currentThread().getName());
			}
		});
		
		for(int i=0;i<n;i++){
			new abc(barrier).start();
		}
	}
	
	static class abc extends Thread{
		 CyclicBarrier barrier; 
            public abc(CyclicBarrier barrier){
				this.barrier = barrier;
			}
			public void run(){
				System.out.println("线程"+Thread.currentThread().getName()+"正在执行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程"+Thread.currentThread().getName()+"执行完毕，等待其他线程");
				try {
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				
				System.out.println("所有线程执行完毕，继续处理其他任务");
			}
	  }
}
