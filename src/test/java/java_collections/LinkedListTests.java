package java_collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListTests {


    @Test
    public void insertTest() {
        int size = 10000000;
        long linkedListTime = insertInLinkedList(size);
        long arrayListTime = insertInArrayList(size);
        System.out.println("LinkedList: " + linkedListTime + "ms");
        System.out.println("ArrayList: " + arrayListTime + "ms");
    }

    // https://www.baeldung.com/java-remove-first-element-from-list
    @DisplayName("remove First Item 성능테스트: LinkedList 가 ArrayList 보다 빠르다")
    @Test
    void removeFistTest() {
        int size = 100000;
        long removeFirstItemInLinkedList = removeFirstItemInLinkedList(getLinkedList(size));
        long removeFirstItemInArrayList = removeFirstItemInLinkedList(getArrayList(size));
        System.out.println("LinkedList: " + removeFirstItemInLinkedList + "ms");
        System.out.println("ArrayList: " + removeFirstItemInArrayList + "ms");
        assertTrue(removeFirstItemInLinkedList < removeFirstItemInArrayList);
    }

    private List<Integer> getLinkedList(int size) {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        return linkedList;
    }

    private List<Integer> getArrayList(int size) {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    private long insertInLinkedList(int size) {
        long start = System.currentTimeMillis();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private long insertInArrayList(int size) {
        long start = System.currentTimeMillis();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private long removeFirstItemInLinkedList(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.remove(0);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
