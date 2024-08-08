package polymorphism_payment_service.infrastructure;

import polymorphism_payment_service.domain.valueobjects.PayMethod;
import polymorphism_payment_service.domain.service.PaymentApiCaller;
import polymorphism_payment_service.infrastructure.dto.KakaoPayRequest;

public class KakaoPayApiCaller implements PaymentApiCaller<KakaoPayRequest> {
	@Override
	public boolean supports(PayMethod paymethod) {
		return PayMethod.KAKAO_PAY == paymethod;
	}

	@Override
	public PayMethod pay(KakaoPayRequest payRequest) {
		System.out.printf("카카오페이에만 필요한 데이터{%s}로 카카오페이에 결제요청함", payRequest.toString());
		return payRequest.getPaymethod();
	}

}
