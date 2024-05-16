package modernjava.virtualthreads;

import modernjava.util.CommonUtil;

import java.util.concurrent.locks.ReentrantLock;

import static modernjava.util.LoggerUtil.log;

public class Counter {
    private int counter;

    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final Object lock = new Object();

    /**
     * synchronized를 사용하여 동기화 처리
     *
     * @param index 스레드를 구분하기 위한 구분자
     * @return 해당함수가 호출된 만큼 counter가 증가
     */
    public int getAndIncrement(int index) {
        synchronized (lock) {
            log("started doSomeWork : " + index);
            CommonUtil.sleep(1000);
            log("finished doSomeWork : " + index);
            return counter++;
        }
    }

    /**
     * ReentrantLock을 사용하여 동기화 처리
     *
     * @param index 스레드를 구분하기 위한 구분자
     * @return
     */
    public int getAndIncrementUsingReentrantLock(int index) {
        reentrantLock.lock();
        try {
            log("started doSomeWork : " + index);
            CommonUtil.sleep(1000);
            log("finished doSomeWork : " + index);
            return counter++;
        } finally {
            reentrantLock.unlock();
        }
    }
}
