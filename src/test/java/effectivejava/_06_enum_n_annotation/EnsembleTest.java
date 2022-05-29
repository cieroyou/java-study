package effectivejava._06_enum_n_annotation;

import org.junit.jupiter.api.Test;

import static effectivejava._06_enum_n_annotation.Ensemble.DOUBLE_QUARTET;
import static effectivejava._06_enum_n_annotation.Ensemble.OCTET;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnsembleTest {


    // 8중주 (OCTET) 의 연주자 수 가져오기
    @Test
    void getNumberOfMusicians_OfOctet() {
        assertEquals(8, OCTET.numberOfMusicians());
    }


    // 복4중주 연주자 수 가져오기
    // 복4중주는 OCTET 과 같이 8명의 연주자인데, 현재의 Ensemble 에서 확장성 있게 어떻게 가져가야 할까?
    @Test
    void getNumberOfMusicians_OfDoubleQuartet() {
        assertEquals(8, DOUBLE_QUARTET.numberOfMusicians());
    }

}