package springbootstarter.service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

//@Service
public class ThreadingServiceImpl implements IThreadingService {

	private final ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

	@Override
	public List<String> createDeadlock() {
		final Object lock1 = new Object();
		final Object lock2 = new Object();

		String firstThreadName = UUID.randomUUID().toString();
		String secondThreadName = UUID.randomUUID().toString();
		
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.execute(new Thread(firstThreadName) {
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("Thread1 acquired lock1");
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException ignore) {
					}
					synchronized (lock2) {
						System.out.println("Thread1 acquired lock2");
					}
				}
			}
		});
		
		executor.execute(new Thread(secondThreadName) {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("Thread2 acquired lock2");
					synchronized (lock1) {
						System.out.println("Thread2 acquired lock1");
					}
				}
			}
		});
/*
		Thread thread1 = new Thread(firstThreadName) {
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("Thread1 acquired lock1");
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException ignore) {
					}
					synchronized (lock2) {
						System.out.println("Thread1 acquired lock2");
					}
				}
			}
		};
		thread1.start();*/

		/*Thread thread2 = new Thread(secondThreadName) {
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println("Thread2 acquired lock2");
					synchronized (lock1) {
						System.out.println("Thread2 acquired lock1");
					}
				}
			}
		};
		thread2.start();*/
			executor.shutdown();
		List<String> threadNames = new ArrayList<String>(2);
		threadNames.add(firstThreadName);
		threadNames.add(secondThreadName);
		return threadNames;
	}

	@Override
	public boolean detectDeadlock(String firstThreadName, String secondThreadName) {
		long[] deadlockedThreadIds = ThreadingServiceImpl.this.mxBean.findDeadlockedThreads();
		boolean result = false;
		if (deadlockedThreadIds != null) {
			ThreadInfo[] deadlockThreadInfos = ThreadingServiceImpl.this.mxBean.getThreadInfo(deadlockedThreadIds);
			System.out.println("deadlockThreadInfo" + deadlockThreadInfos);
			for (ThreadInfo deadlockThreadInfo : deadlockThreadInfos) {
				System.out.println("deadlockThreadInfo" + deadlockThreadInfo.getThreadName());
				if (deadlockThreadInfo != null) {
					if (!deadlockThreadInfo.getThreadName().equals(firstThreadName)
							&& !deadlockThreadInfo.getThreadName().equals(secondThreadName)) {
						continue;
					}
					if (deadlockThreadInfo.getThreadName().equals(firstThreadName)) {
						if (!deadlockThreadInfo.getLockOwnerName().equals(secondThreadName)) {
							result = false;
						} else {
							result = true;
						}
					} else if (deadlockThreadInfo.getThreadName().equals(secondThreadName)) {
						if (!deadlockThreadInfo.getLockOwnerName().equals(firstThreadName)) {
							result = false;
						} else{
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadingServiceImpl m = new ThreadingServiceImpl();
		List<String> l = m.createDeadlock();
		Thread.sleep(2000);
		System.out.println("Name 1" + l.get(0));
		System.out.println("Name 2" + l.get(1));
		System.out.println("Result :: " + m.detectDeadlock(l.get(0), l.get(1)));

	}

}
