package datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DoubleLinkedListTest {

    @DisplayName("처음 데이터가 추가됨")
    @Test
    void addHead() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);

        assertEquals(1, list.getHead().getData());
        assertEquals(1, list.getTail().getData());
    }

    @DisplayName("데이터 추가")
    @Test
    void add() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.getHead().getData());
        assertEquals(2, list.getHead().getNext().getData());
        assertEquals(3, list.getTail().getData());
        assertEquals(2, list.getTail().getPrev().getData());

    }

    @Test
    void searchItemFromHead() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        var node = list.searchFromHead(2);

        assertEquals(1, node.prev.data);
        assertEquals(3, node.next.data);
    }

    @Test
    void searchNullFromHead() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        var node = list.searchFromHead(5);

        assertNull(node);
    }

    @Test
    void searchItemFromTail() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var node = list.searchFromTail(2);

        assertEquals(1, node.prev.data);
        assertEquals(3, node.next.data);
    }

    @DisplayName("head앞에 추가")
    @Test
    void insertFrontOfHead() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);
        list.add(2);

        list.insertToFront(1, 5);

        var secondNode = list.getHead().next;

        assertEquals(5, list.getHead().data);
        assertEquals(1, list.getHead().next.data);
        assertEquals(5, secondNode.prev.data);
    }

    @DisplayName("특정 노드 앞에 추가")
    @Test
    void insertFront() {
        var list = new DoubleLinkedList<Integer>();
        list.add(1);
        list.add(2);

        list.insertToFront(2, 5);
        // 1-5-2
        var head = list.getHead();
        var secondNode = list.getHead().next;
        var thirdNode = secondNode.next;


        assertEquals(5, head.next.data);
        assertEquals(1, secondNode.prev.data);
        assertEquals(2, secondNode.next.data);
        assertEquals(5, thirdNode.prev.data);

    }
}