package datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {
	@Test
	void addTest(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list.toString());
	}

//	@Test
//	void printAllTest(){
//		SingleLinkedList<Integer> list = new SingleLinkedList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.printAll();
//	}
//
	@DisplayName("head에 값 삽입")
	@Test
	void addToHead(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		// 2 뒤에 5를 추가
		list.insert(5,1);

		assertEquals(5, list.getHead().getData());
	}

	@DisplayName("특정 위치에 값 삽입")
	@Test
	void addInside(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		// 2 뒤에 5를 추가
		list.insert(5,2);

		assertEquals(1, list.getHead().getData());
		assertEquals(5,  list.getHead().getNext().getNext().getData());
	}

	@Test
	void getNodeThenReturnNull() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);

		SingleLinkedList.Node node = list.getNode(2);
		assertNull(node);
	}


	@Test
	void getNode() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);

		SingleLinkedList.Node node = list.getNode(2);
		assertNotNull(node);
	}
//
	@DisplayName("삭제 데이터가 head이면 head.next가 head가 된다.")
	@Test
	void removeHead(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		list.remove(1);
		assertEquals(2, list.getHead().getData());
	}

	@DisplayName("삭제 데이터가 head가 아니면 삭제 데이터의 이전 노드의 next가 삭제 데이터의 다음 노드가 된다.")
	@Test
	void removeHeadInside() {
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);


		list.remove(2);

		assertEquals(1, list.getHead().getData());
		 assertEquals(3, list.getHead().getNext().getData());
	}

}