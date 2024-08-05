package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingleLinkedListTest {
	@Test
	void addTest(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list.toString());
	}

	@Test
	void printAllTest(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.printAll();
	}

	@Test
	void addInside(){
		SingleLinkedList<Integer> list = new SingleLinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		// 2 뒤에 5를 추가
		list.add(5,2);
	}

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


		list.remove(3);

		assertEquals(1, list.getHead().getData());
		// assertEquals(3, list.getHead().getNext().getData());
	}
}