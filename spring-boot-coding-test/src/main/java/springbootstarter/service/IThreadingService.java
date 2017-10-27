package springbootstarter.service;

import java.util.List;

public interface IThreadingService {
	
	List<String> createDeadlock();
	
	boolean detectDeadlock(String firstThreadName, String secondThreadName);
}
