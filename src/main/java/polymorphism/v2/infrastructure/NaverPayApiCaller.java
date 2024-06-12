package polymorphism.v2.infrastructure;

import polymorphism.v2.PayMethod;
import polymorphism.v2.PaymentApiCaller;
import polymorphism.v2.dto.NaverPayRequest;

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
