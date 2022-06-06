package javastream.chapter4;

import javastream.chapter4.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {


    final List<User> users = new java.util.ArrayList<>(List.of(
            new User(3, "Alice"),
            new User(1, "Charlie"),
            new User(10, "Bob")
    ));

    // User 의 id 로 오름차순으로 정렬
    @DisplayName("UserId 오름차순으로 정렬 테스트")
    @Test
    void sortInAscendingOrderByUserId() {
        // when
        Comparator<User> idComparator = (user1, user2) -> user1.getId() - user2.getId();
//        Comparator<User> idComparator = Comparator.comparingInt(User::getId);
        users.sort(idComparator); // Collections.sort(users, idComparator);
        // then
        assertEquals("Charlie", users.get(0).getName());
    }

    @DisplayName("UserName 오름차순으로 정렬 테스트")
    @Test
    void sortInAscendingOrderByUserName() {
        // when
        Comparator<User> nameComparator = (user1, user2) -> user1.getName().compareTo(user2.getName());
//        Comparator<User> idComparator = Comparator.comparing(User::getName);
        users.sort(nameComparator); // Collections.sort(users, nameComparator);
        // then
        assertEquals("Alice", users.get(0).getName());
    }
}
