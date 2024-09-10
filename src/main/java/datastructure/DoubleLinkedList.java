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

        public void setPrevNode(Node<T> prevNode) {
            prevNode.next = this;
            this.prev = prevNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.next = nextNode;
            next.prev = this;
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

    // a -> node
    // a -> addNode -> node
    public boolean insertToFront(T nodeData, T addNodeData) {
        // a = node.prev
        // a.next = addNode
        // addNode.prev = a
        // addNode.next = node;
        // node.prev = addNode

        if (this.head == null) return false;
        if (isHead(nodeData)) {
            Node<T> newHead = new Node<>(addNodeData);
            Node<T> currentHead = this.head;
            newHead.setNextNode(currentHead);
//            newHead.next = this.head;
//            currentHead.prev = newHead;
            this.head = newHead;
            return true;
        }

        Node<T> node = this.head;
        while (node != null) {
            if (node.data == nodeData) {
                // prevNode -> node
                // prevNode -> addNode -> node

                // prevNode = node.prev;
                // prevNode.next = addNode;
                // addNode.prev = prevNode;
                // addNode.next = node;
                // node.prev = addNode;
                Node<T> addNode = new Node<>(addNodeData);
                Node<T> prevNode = node.prev;
                addNode.setPrevNode(prevNode);
                addNode.setNextNode(node);
                return true;
            }


            node = node.next;
        }

        return false;

    }

    public boolean isHead(T data) {
        if (this.head.data == data) return true;
        return false;
    }
}
