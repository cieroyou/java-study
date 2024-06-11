package polymorphism.v2;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import polymorphism.v2.dto.KakaoPayRequest;
import polymorphism.v2.dto.PayRequest;
import polymorphism.v2.infrastructure.KakaoPayApiCaller;
import polymorphism.v2.infrastructure.NaverPayApiCaller;
import polymorphism.v2.infrastructure.PgCardApiCaller;

class PaymentProcessorTest {
	private PaymentProcessor paymentProcessor;
	private List<PaymentApiCaller<? extends PayRequest>> paymentApiCallers;

	@BeforeEach
	void setUp() {
		paymentApiCallers = List.of(new KakaoPayApiCaller(), new NaverPayApiCaller(), new PgCardApiCaller());
		paymentProcessor = new PaymentProcessor(paymentApiCallers);
	}

	@Test
	void testKakaoPay() {
		PayRequest payRequest = new KakaoPayRequest(20000L, "kakao pay data");
		paymentProcessor.pay(payRequest);
		// then
		// kakao pay api caller 호출 확인. KakaoPayApiCaller.pay() 메서드가 호출되었는지 확인
	}
}