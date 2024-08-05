package datastructure;

// list.add 구현하기
// 데이터가 아예없으면 head가 됨
// LinkedList 에 사용하면 좋은 데이터 구조도는 트리!
// head.data=1
// head.next.data=2
// head.next.next.data=3
// head.next.next.next.data=4
// head.next.next.next.next....next = null 인 데이터가 결국엔 가장 마지막 데이터
// 내부에 인덱스를 추가한다고 될 수 있는게 아니고 어쩔수 없이 null 나올때까지 계속 확인을 해야한다.

import lombok.Getter;

// 중복 데이터가 들어가지 않는 LinkedList
public class SingleLinkedList<T> {
	private Node<T> head;

	public Node<T> getHead() {
		return head;
	}
	@Getter
	public class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	public void add(T data) {
		if (head == null) {
			this.head = new Node(data);
		} else {
			Node<T> node = head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new Node<>(data);
		}
	}
	
	public void add(T data, T isData) {
		Node<T> searchedNode = this.search(isData);
		if(searchedNode == null) {
			this.add(data);
		} else {
			Node<T> nextNode = searchedNode.next;
			searchedNode.next = new Node<>(data);
			searchedNode.next.next = nextNode;
		}
	}

	public boolean isHead(T isData){
		Node<T> node = this.head;
		return node.data == isData;
	}

	// 삭제할 데이터가 head이면 head 다음 데이터가 head여야한다.
	// 삭제할 데이터가 head가 아니라면 삭제를 한다.
	public boolean remove(T isData) {
		if(head == null) return false;
		if(this.isHead(isData)){
			this.head = this.head.next;
			return true;
		}
		// 방법1
		Node<T> node = this.head;
		while(node.next != null) {
			if(node.next.data == isData) {
				node.next = node.next.next;
				return true;
			}
			node = node.next;
		}
		return false;

		// 방법2
		// Node<T> before = null;
		// while(node.data != isData){
		// 	before = node;
		// 	node = node.next;
		// }
		// if(before == null){
		// 	return false;
		// }
		// before.next = node.next;
		// return true;
	}
	private Node<T> search(T data) {
		if (this.head == null) {
			return null;
		} else {
			Node<T> node = this.head;
			while (node != null) {
				if (node.data == data) {
					return node;
				} else {
					node = node.next;
				}
			}
			return null;
		}

	}

	public void printAll() {
		if (head != null) {
			Node<T> node = head;
			System.out.println(node.data);
			while (node.next != null) {
				node = node.next;
				System.out.println(node.data);
			}
		}
	}
}