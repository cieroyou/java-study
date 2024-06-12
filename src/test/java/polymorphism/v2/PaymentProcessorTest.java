package polymorphism.v2;

import static org.junit.jupiter.api.Assertions.*;

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
		// given
		var payRequest = KakaoPayRequest.builder()
			.name("하나카드")
			.cardNumber("1234-1234-1234-1234")
			.price(10000L)
			.svv("338")
			.build();
		// when
		var paymethod = paymentProcessor.pay(payRequest);
		// then
		// kakao pay api caller 호출 확인. KakaoPayApiCaller.pay() 메서드가 호출되었는지 확인
		assertEquals(PayMethod.KAKAO_PAY, paymethod);
	}
}