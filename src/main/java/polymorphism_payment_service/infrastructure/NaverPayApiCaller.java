package polymorphism_payment_service.infrastructure;

import polymorphism_payment_service.domain.valueobjects.PayMethod;
import polymorphism_payment_service.infrastructure.dto.NaverPayRequest;
import polymorphism_payment_service.domain.service.PaymentApiCaller;

public class NaverPayApiCaller implements PaymentApiCaller<NaverPayRequest> {
	@Override
	public boolean supports(PayMethod paymethod) {
		return PayMethod.NAVER_PAY == paymethod;
	}

	@Override
	public PayMethod pay(NaverPayRequest payRequest) {
		System.out.printf("네이버페이에만 필요한 데이터{%s}로 카카오페이에 결제요청함", payRequest.getNaverPayData());
		return payRequest.getPaymethod();
	}
}
