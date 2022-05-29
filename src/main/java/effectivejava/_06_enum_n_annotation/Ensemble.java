package effectivejava._06_enum_n_annotation;

// 합주단의 종류
// 연주자의 수가 1명에서 10명까지 정의한 열거 타입
public enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;



    public int numberOfMusicians() {
        // Bad Example
        return ordinal() + 1;

    }

}
