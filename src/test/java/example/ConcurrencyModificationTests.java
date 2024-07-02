package example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


class ConcurrencyModificationTests {

	@DisplayName("순회 중 map 수정 시_ConcurrentModificationException 발생")
	@Test
	void givenHashMap_WhenTraversedAndModifyAtTheSameTime_ThenOccurConcurrentModificationException_withIterator() {
		// given
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		// when
		Iterator<Integer> it = map.keySet().iterator();
		boolean exceptionThrown = false;
		try {
			while (it.hasNext()) {
				Integer key = it.next();
				map.put(3, "three");
			}
		} catch (ConcurrentModificationException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown, "ConcurrentModificationException이 발생해야 합니다.");
	}

	@DisplayName("순회 중 map 수정 시_ConcurrentModificationException 발생")
	@Test
	void givenHashMap_WhenTraversedAndModifyAtTheSameTime_ThenOccurConcurrentModificationException_withForEach() {
		// given
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		// when
		boolean exceptionThrown = false;
		try {
			map.keySet().forEach(key -> {
				map.put(3, "three");
			});
		} catch (ConcurrentModificationException e) {
			exceptionThrown = true;
		}
		// then
		assertTrue(exceptionThrown, "ConcurrentModificationException이 발생해야 합니다.");
	}

	@DisplayName("순회 중 map 수정 시_ConcurrentModificationException 발생")
	@Test
	void givenHashMap_WhenTraversedAndModifyAtTheSameTime_ThenOccurConcurrentModificationException_withForLoop() {
		// given
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		boolean exceptionThrown = false;
		// when
		try {
			for (Map.Entry<Integer, String> entry : map.entrySet()) {
				map.put(3, "three");
			}
		} catch (ConcurrentModificationException e) {
			exceptionThrown = true;
		}
		//then
		assertTrue(exceptionThrown, "ConcurrentModificationException이 발생해야 합니다.");
	}

    @Test
    void givenHashMap_whenSumParallel_thenError() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> sumList = parallelSum100(map, 100);

        assertNotEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);
    }

    @Test
    void givenConcurrentMap_whenSumParallel_thenCorrect()
            throws Exception {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Integer> sumList = parallelSum100(map, 1000);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertEquals(0, wrongResultCount);
    }

    private List<Integer> parallelSum100(Map<String, Integer> map,
                                         int executionTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        for (int i = 0; i < executionTimes; i++) {
            map.put("test", 0);
            System.out.println("test 개수: " + map.size());
            ExecutorService executorService =
                    Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < 10; k++) {
                        map.computeIfPresent(
                                "test",
                                (key, value) -> {
                                    value = value + 1;
                                    System.out.println("test 값: " + value);
                                    return value;
                                }
                        );
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            sumList.add(map.get("test"));
            System.out.println("map.getTest: " + map.get("test"));
        }
        return sumList;
    }

    @Test
    void givenConcurrentMap_whenRemoveItem_thenCorrenct() {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "test" + i);
        }

        map.forEach((i, v) -> {
            if (i.equals(4)) {
                map.remove(i);
            }
        });
        assertEquals(9, map.size());
    }

    @Test
    void givenHashMap_whenRemoveItem_thenConcurrencyModificationError() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "test" + i);
        }
        assertThrows(ConcurrentModificationException.class, () -> {
            map.forEach((i, v) -> {
                if (i.equals(4)) {
                    map.remove(i);
                }
            });
        });
    }

    @Test
    void givenConcurrentMap_whenRemoveItemInOtherThread_thenCorrect()
            throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                map.put(i, "test" + i);
            }
        }
        // 다른 쓰레드에서는 홀수값을 item에 추가함
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 1) {
                    map.put(i, "test" + i);
                    System.out.println(
                            "threadId: " + Thread.currentThread().getId() + ", added item: " + i);
                }
            }
        });
        // 짝수 Item만 들어있는 map을 for문을 돌며 읽는 것과, 다른쓰레드에서 해당 map에 홀수값을 집어넣는 것을 병렬로 실행
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer i = entry.getKey();
            System.out.println(i);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(1000, map.size());
    }

    @Test
    void givenConcurrentMap_whenAddItemInOtherThread_thenCorrect()
            throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                map.put(i, "test" + i);
            }
        }
        // 다른 쓰레드에서는 홀수값을 item에 추가함
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 1) {
                    map.put(i, "test" + i);
                    System.out.println(
                            "threadId: " + Thread.currentThread().getId() + ", added item: " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 짝수 Item만 들어있는 map을 for문을 돌며 읽는 것과, 다른쓰레드에서 해당 map에 홀수값을 집어넣는 것을 병렬로 실행
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer i = entry.getKey();
            System.out.println(entry.getValue());
            Thread.sleep(10);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(1000, map.size());
    }

    @Test
    void givenConcurrentMap_whenAddItemInOtherThreadWithIterator_thenCorrect()
            throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                map.put(i, "test" + i);
            }
        }
        // 다른 쓰레드에서는 홀수값을 item에 추가함
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 1) {
                    map.put(i, "test" + i);
                    System.out.println(
                            "threadId: " + Thread.currentThread().getId() + ", added item: " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 짝수 Item만 들어있는 map을 for문을 돌며 읽는 것과, 다른쓰레드에서 해당 map에 홀수값을 집어넣는 것을 병렬로 실행
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            System.out.println(map.get(key));
            Thread.sleep(10);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(1000, map.size());
    }

    @Test
    void givenHashMap_whenRemoveItem_thenCorrenct() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, "test" + i);
        }

        map.keySet().removeIf(m -> m % 2 == 0);
        assertEquals(500, map.size());
    }

    @Test
    void givenHashMap_whenRemoveItem_thenCorrenct2() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, "test" + i);
        }

        ExecutorService executorService =
                Executors.newFixedThreadPool(4);
        executorService.execute(() -> {
            map.keySet().removeIf(m -> m % 2 == 0);
        });
    }

}

