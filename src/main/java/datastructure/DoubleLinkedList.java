package datastructure;

import lombok.Getter;

/**
 * Node에 prev, next 두개 다 있는 LinkedList
 */
public class DoubleLinkedList<T> {
    @Getter
    private Node<T> head;
    @Getter
    private Node<T> tail;


    public class Node<T> {
        @Getter
        T data;
        @Getter
        Node<T> prev;
        @Getter
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            next = null;
        }
    }

    public void add(T data) {
        if (this.head == null) {
            this.head = new Node<>(data);
            this.tail = this.head;
            return;
        }
        Node<T> node = this.head;
        Node<T> tobeAdded = new Node<>(data);
        while (node.next != null) {
            // head, tail
            node = node.next;
        }

        node.next = tobeAdded;
        tobeAdded.prev = node;
        this.tail = tobeAdded;
    }

    public Node<T> searchFromHead(T data) {
        if (this.head == null) return null;
        Node<T> node = this.head;
        while (node != null) {
            // 0 ,1 인 경우: 0 -> node = 1, 1 -> node = null ==> 빠져나옴
            if (node.data == data) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public Node<T> searchFromTail(T data) {
        if (this.head == null) return null;
        Node<T> node = this.tail;
        while (node != null) {
            if (node.data == data) {
                return node;
            }
            node = node.prev;
        }
        return null;
    }
}
