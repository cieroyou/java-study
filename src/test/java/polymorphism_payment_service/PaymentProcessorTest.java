package polymorphism_payment_service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import polymorphism_payment_service.domain.dto.PayRequest;
import polymorphism_payment_service.domain.exception.UnsupportedPaymentRequestException;
import polymorphism_payment_service.domain.service.PaymentApiCaller;
import polymorphism_payment_service.domain.service.PaymentProcessor;
import polymorphism_payment_service.domain.valueobjects.PayMethod;
import polymorphism_payment_service.infrastructure.KakaoPayApiCaller;
import polymorphism_payment_service.infrastructure.NaverPayApiCaller;
import polymorphism_payment_service.infrastructure.PgCardApiCaller;
import polymorphism_payment_service.infrastructure.dto.KakaoPayRequest;

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
		Assertions.assertEquals(PayMethod.KAKAO_PAY, paymethod);
	}

	@DisplayName("지원하지 않는 결제요청시_UnsupprtedPaymentRequestException에러반환")
	@Test
	void testUnsupportedPaymentRequest() {
		// given
		var unsupportedPayRequest = new PayRequest(10000L, PayMethod.UNKNOWN);

		// when & then
		Assertions.assertThrows(UnsupportedPaymentRequestException.class, () -> {
			paymentProcessor.pay(unsupportedPayRequest);
		});
	}
}