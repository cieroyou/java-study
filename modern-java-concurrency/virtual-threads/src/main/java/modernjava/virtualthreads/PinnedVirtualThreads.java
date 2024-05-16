package modernjava.virtualthreads;

import modernjava.util.CommonUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static modernjava.util.LoggerUtil.log;

/**
 * 집중해야 할 것: getAndIncrement(), getAndIncrementUsingReentrantLock() 을 스위칭하여 실행 후,
 * 각 결과에서 started doSomeWork - finished doSomeWork 로깅작업이 실행된 스레드가 일치하는지 아닌지 확인한다.
 *
 * - `synchronized` 를 이용한 코드의 결과는 `started doSomeWork : 1` 에서 사용된 스레드는 1이고, `finished doSomeWork : 1` 에서 사용된 스레드는 1 이다. 다른 모든 작업을 확인해도 started - finished 작업이 모두 같은 스레드에서 진행됨을 확인 할 수 있다.
 *     - 가상스레드는 sleep()과 같은 blocking io 작업을 만나면, 가상스레드가 캐리어스레드로부터 unmount 되어야 하지만, 이 코드는  started doSomeWork 로깅이 시작되고 1초 동안 sleep()이 되고, finished doSomeWork 를 실행할 동안 가상스레드 mount 되었음을 의미한다.
 * - 반면에 ReentrantLock 을 사용한 경우 started, finished 작업은 매우 별개의 스레드에서 실행된다. 이 말은 즉슨 Counter.getAndIncrementUsingReentrantLock() 에서는 blocking i/o를 만났을 때 다른 가상스레드가 캐리어스레드를 사용할 수 있도록 캐리어스레드를 잘 반납했다는 것을 의미한다.
 */
public class PinnedVirtualThreads {

    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        var counter = new Counter();
        int MAX_THREADS = 10;
        IntStream.rangeClosed(1, MAX_THREADS)
                .forEach(i -> {
                    Thread.ofVirtual().start(() -> new Counter().getAndIncrement(i));
//                    Thread.ofVirtual().start(() -> new Counter().getAndIncrementUsingReentrantLock(i));
                    atomicInteger.incrementAndGet();
                    log("No of threads : " + atomicInteger.get());
                });
        log("Program Completed!");

        CommonUtil.sleep(11000);
    }
}
