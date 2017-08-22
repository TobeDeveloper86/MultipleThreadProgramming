package com.cn.saint.concurrent;

import java.util.concurrent.CountDownLatch;


/**
 *  countdownlatch 
 *  
 *  ����������ʵ�����Ƽ������Ĺ��ܡ�������һ������A����Ҫ�ȴ��������ɸ�����ִ�����֮�����ִ�У���ʱ�Ϳ�������CountDownLatch��ʵ�����ֹ�����.
 *  
 *  CyclicBarrier 
 *  
 *  ���������ɸ��̶߳�Ҫ����д���ݲ���������ֻ�������̶߳����д���ݲ���֮����Щ�̲߳��ܼ�������������飬��ʱ�Ϳ�������CyclicBarrier��
 *
 *
 *  CountDownLatch ����ʹһ���̵߳���һ���̶߳��������ټ����ܡ�
 *  CyclicBarrier �ܹ�ʹһ���߳���һ��ʱ����ϴﵽͬ����������һ��ʼִ��ȫ���������һ��������ͬʱ�����ǿ���ѭ��ʹ�õģ�
 *  Semaphore ��ֻ����һ���������߳�ͬʱִ��һ������
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(){
			public void run(){
				System.out.println("���߳�"+Thread.currentThread().getName()+"����ִ��");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("���߳�"+Thread.currentThread().getName()+"ִ�����");
                latch.countDown();
			}
		}.start();
		
		new Thread(){
            public void run() {
                try {
                    System.out.println("���߳�"+Thread.currentThread().getName()+"����ִ��");
                    Thread.sleep(2000);
                    System.out.println("���߳�"+Thread.currentThread().getName()+"ִ�����");
                    latch.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            };
        }.start();
        
        System.out.println("�ȴ�2�����߳�ִ�����");
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("2�����߳�ִ�����");
        System.out.println("����ִ�����߳�����");
	}
}
