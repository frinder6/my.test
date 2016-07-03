package com.share;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: LockShare
 * @Description: TODO(使用锁机制实现资源共享)
 * @date 2016年5月26日 下午2:51:16
 *
 */
public class LockShare implements Runnable {

	private static Logger logger = Logger.getLogger(LockShare.class);

	private ThreadLocal<Integer> saleCount = new ThreadLocal<Integer>();

	// 锁
	private Lock lock = new ReentrantLock();

	private int tickets;

	private boolean isOver = false;

	public LockShare(int tickets) {
		super();
		this.tickets = tickets;
	}

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		while (!isOver) {
			// syncBlockSale();
			// syncMethodSale();
			lockSale();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info(Thread.currentThread().getName() + " : " + (endTime - beginTime));
	}

	/**
	 * 
	 * @Title: lockSale
	 * @Description: TODO(使用锁实现资源共享)
	 * @return void
	 * @date 2016年5月25日 下午4:58:18
	 * @throws
	 */
	private void lockSale() {
		lock.lock();
		try {
			sale();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 
	 * @Title: syncMethodSale
	 * @Description: TODO(同步方法实现资源共享)
	 * @return void
	 * @date 2016年5月25日 下午4:49:45
	 * @throws
	 */
	private synchronized void syncMethodSale() {
		sale();
	}

	/**
	 * 
	 * @Title: synchronizedSale
	 * @Description: TODO(同步代码块实现资源共享)
	 * @return void
	 * @date 2016年5月25日 下午4:46:46
	 * @throws
	 */
	private void syncBlockSale() {
		synchronized (this) {
			sale();
		}
	}

	private void sale() {
		if (tickets > 0) {
			tickets--;
			int beforeCount = getBeforeCount();
			saleCount.set(++beforeCount);
			logger.debug(Thread.currentThread().getName() + " : " + saleCount.get());
		} else {
			isOver = true;
		}
	}

	private int getBeforeCount() {
		Integer beforeCount = saleCount.get();
		if (null == beforeCount) {
			logger.info("init ThreadLocal...");
			saleCount.set(0);
			beforeCount = saleCount.get();
		}
		return beforeCount;
	}

	public static void main(String[] args) {
		int tickets = 100;
		LockShare share = new LockShare(tickets);
		for (int i = 0; i < 3; i++) {
			new Thread(share).start();
		}
	}

}
