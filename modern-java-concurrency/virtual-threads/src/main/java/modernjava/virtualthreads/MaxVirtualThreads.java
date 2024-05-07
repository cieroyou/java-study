package modernjava.virtualthreads;

import static modernjava.util.LoggerUtil.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import modernjava.threads.MaxThreads;
import modernjava.util.CommonUtil;

/**
 * threads.MaxThreads와 모든 코드줄이 같고, Thread.ofPlatform()을 Thread.ofVirtual()로 변경한 것만 다름.
 * VirtualThread 최대 스레드 개수를 테스트 하는 프로그램으로써 10000개의 가상스레드는 가뿐히 생성될 뿐 아니라, 100만개의 스레드까지 생성이 가능하다.
 * 확장성 관섬에서 보면 가상스레드가 동일한 양의 리소스로 훨씬 많은 스레드를 생성하고 처리가 가능하다. 이는 가상 스레드가 아주 가볍기 때문이다.
 */
public class MaxVirtualThreads {
	static AtomicInteger atomicInteger = new AtomicInteger();

	public static void doSomeWork(int index) {
		log("started doSomeWork : " + index);
		//Any task that's started by a thread is blocked until it completes.
		//It could be any IO call such as HTTP or File IO call.
		CommonUtil.sleep(5000);
		log("finished doSomeWork : " + index);
	}

	public static void main(String[] args) {
		int MAX_THREADS =1_000_000;
		// For 1024 MB
		// Run with this thread count  -> 10_000, 100_000, 1_000_000
		//int MAX_THREADS = 10;
		//Demo 2- Blocking nature of Java Threads
		// Change the no of MAX_THREADS to 10
		// Enable the loggers
		IntStream.rangeClosed(1, MAX_THREADS)
			.forEach((i) -> {
				var threads = Thread.ofVirtual().start(() -> MaxThreads.doSomeWork(i));
				atomicInteger.incrementAndGet();
				log("No of threads : " + atomicInteger.get());
			});

		log("Program Completed!");

	}
}
