package javastream.chapter5;

import javastream.chapter4.model.User;
import org.junit.jupiter.api.DisplayName;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructReferenceTests {

    @DisplayName("ConstructorReference 를 이용한 유저 생성 테스트")
    void createUserTest() {
        // given
        Integer userId = 11;
        String userName = "Alice";

        // when
//        BiFunction<Integer, String, User> createUser = (id, name) -> new User(id, name);
        BiFunction<Integer, String, User> createUser = User::new;
        User createdUser = createUser.apply(userId, userName);

        // then
        assertEquals(userId, createdUser.getId());
        assertEquals(userName, createdUser.getName());
    }
}
