package javastream.chapter5;

import javastream.chapter4.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructReferenceTests {

    final String[][] carInputs = new String[][]{
            {"sedan", "Sonata", "Hyundai"},
            {"ven", "Sienna", "Toyota"},
            {"sedan", "Model S", "Tesla"},
            {"suv", "Sorento", "KIA"}
    };

    @DisplayName("ConstructorReference 를 이용한 유저 생성 테스트")
    @Test
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

    /**
     * type 에 따라 맞는 Car 를 생성하는 함수. 단점 : 확장성(유지보수성)이 좋지 않다.
     */
    Car createCarV1(String type, String name, String brand) {
        // 타입이 늘어날 때 마다 계속 if 문 분기가 증가한다.
        if (type.equals("sedan")) {
            return new Sedan(name, brand);
        } else if (type.equals("suv")) {
            return new Suv(name, brand);
        } else if (type.equals("ven")) {
            return new Ven(name, brand);
        }
        return null;
    }

    @DisplayName("createCarV1 를 이용한 Car 생성 테스트")
    @Test
    void createCarV1Test() {
        List<Car> cars = new ArrayList<>();
        for (String[] carInput : carInputs) {
            String carType = carInput[0];
            String carName = carInput[1];
            String carBrand = carInput[2];
            cars.add(createCarV1(carType, carName, carBrand));
        }
        System.out.println(cars);
    }


    /**
     * type 에 따라 맞는 Car 를 생성하는 함수. `createCarV1` 의 낮은 확장성 단점을 고려한 함수
     */
    Car createCarV2(String type, String name, String brand) {
        Map<String, BiFunction<String, String, Car>> typeMap
                = Map.of(
                "sedan", Sedan::new,
                "ven", Ven::new,
                "suv", Suv::new);
        return typeMap.get(type).apply(name, brand);
    }

    @DisplayName("확장성을 고려한 createCarV2 를 이용한 Car 생성 테스트")
    @Test
    void createCarV2Test() {
        List<Car> cars = new ArrayList<>();
        for (String[] carInput : carInputs) {
            String carType = carInput[0];
            String carName = carInput[1];
            String carBrand = carInput[2];
            cars.add(createCarV2(carType, carName, carBrand));
        }

        System.out.println(cars);
    }
}
