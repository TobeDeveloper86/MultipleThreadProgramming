package com.cn.saint.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		int n = 4; 
		CyclicBarrier barrier = new CyclicBarrier(n, new Runnable(){
			@Override
			public void run() {
				System.out.println("��ǰ�߳�"+Thread.currentThread().getName());
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
				System.out.println("�߳�"+Thread.currentThread().getName()+"����ִ��");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("�߳�"+Thread.currentThread().getName()+"ִ����ϣ��ȴ������߳�");
				try {
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				
				System.out.println("�����߳�ִ����ϣ�����������������");
			}
	  }
}
