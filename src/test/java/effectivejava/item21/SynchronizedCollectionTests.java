package effectivejava.item21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.junit.jupiter.api.Test;

/**
 *  SynchronizedCollection 으로 removeIf를 사용하게 되면 thread safe 하지 못하게 된다.
 *  list 중 item.equals("바보") 를 만족하면 삭제를 하는데, thread safe 하다면 여러 쓰레드가 동시에 실행을 하여도
 *  딱 한번만 removeIf 를 실행해야 한다.
 */
public class SynchronizedCollectionTests {

    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        SynchronizedCollection<String> sc = SynchronizedCollection.synchronizedCollection(list);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // "바보" 를 remove 하는 쓰레드가 여러개 달라붙었을 때
        for(int i = 0; i < 10; i++) {
            executorService.submit(()->{
                System.out.println(
                    "threadId = " + Thread.currentThread().getId() + " removeIf=" + sc.removeIf(
                        p -> p.equals("1")));
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // "바보" 를 remove 하는 쓰레드가 여러개 달라붙었을 때
        for(int i = 0; i < 10; i++) {
            executorService.submit(()->{
                System.out.println(
                    "threadId = " + Thread.currentThread().getId() + " removeIf=" + list.removeIf(
                        p -> p.equals("1")));
            });
        }

        executorService.shutdown();
    }
}
