package datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @DisplayName("처음 데이터가 추가됨")
    @Test
    void addHead(){
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);

        assertEquals(1,list.getHead().getData());
        assertEquals(1, list.getTail().getData());
    }

    @DisplayName("데이터 추가")
    @Test
    void add(){
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1,list.getHead().getData());
        assertEquals(2, list.getHead().next.data);
        assertEquals(3, list.getTail().data);
        assertEquals(2, list.getTail().prev.data);

    }

    @Test
    void searchItemFromHead(){
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        DoubleLinkedList.Node node = list.searchFromHead(2);

        assertEquals(1, node.prev.data);
        assertEquals(3, node.next.data);
    }

    @Test
    void searchNullFromHead(){
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        DoubleLinkedList.Node node = list.searchFromHead(5);

        assertNull(node);
    }

    @Test
    void searchItemFromTail(){
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        DoubleLinkedList.Node node = list.searchFromTail(2);

        assertEquals(1, node.prev.data);
        assertEquals(3, node.next.data);
    }
}