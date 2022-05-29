package effectivejava._06_enum_n_annotation;

// 합주단의 종류
// 연주자의 수가 1명에서 10명까지 정의한 열거 타입
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), NONET(9), DECTET(10),

    DOUBLE_QUARTET(8), TRIPLE_QUARTET(12);


    private final int numberOfMusicians;

    Ensemble(int numberOfMusicians) {
        this.numberOfMusicians = numberOfMusicians;
    }

    public int numberOfMusicians() {
        // Bad Example : 연주자가 열거타입이 정의 순서에 너무 의존성을 가진다.
//        return ordinal() + 1;
        // 유지보수 하기 좋은 확정성 있는 코드
        return numberOfMusicians;
    }

}
