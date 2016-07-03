package com.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName: ConcurrentShare
 * @Description: TODO(使用AtomicInteger实现多线程资源共享)
 * @date 2016年5月26日 下午2:50:21
 *
 */
public class ConcurrentShare implements Runnable {

	private static Logger logger = Logger.getLogger(ConcurrentShare.class);

	private ThreadLocal<Map<String, List<Integer>>> saleTickets = new ThreadLocal<Map<String, List<Integer>>>();

	/**
	 * 使用atomic保证资源共享
	 */
	private AtomicInteger tickets;

	private boolean isOver = false;

	public ConcurrentShare(AtomicInteger tickets) {
		super();
		this.tickets = tickets;
	}

	@Override
	public void run() {
		while (!isOver) {
			sale();
		}
		logger.info(JSON.toJSONString(saleTickets.get()));
		logger.info(saleTickets.get().get(Thread.currentThread().getName()).size());
	}

	/**
	 * 
	 * @Title: sale
	 * @Description: TODO(售卖方法)
	 * @return void
	 * @date 2016年5月26日 上午9:50:10
	 * @throws
	 */
	private void sale() {
		if (tickets.get() > 0) {
			int t = tickets.decrementAndGet();
			getBeforeTickts().get(Thread.currentThread().getName()).add(t);
		} else {
			isOver = true;
		}
	}

	private Map<String, List<Integer>> getBeforeTickts() {
		Map<String, List<Integer>> beforeTickets = saleTickets.get();
		if (null == beforeTickets) {
			Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
			map.put(Thread.currentThread().getName(), new ArrayList<Integer>());
			saleTickets.set(map);
			beforeTickets = saleTickets.get();
		}
		return beforeTickets;
	}

	public static void main(String[] args) {
		AtomicInteger tickets = new AtomicInteger(100);
		ConcurrentShare share = new ConcurrentShare(tickets);
		for (int i = 0; i < 3; i++) {
			new Thread(share).start();
		}
	}

}
