package polymorphism.v2.infrastructure;

import polymorphism.v2.PayMethod;
import polymorphism.v2.PaymentApiCaller;
import polymorphism.v2.dto.KakaoPayRequest;

public class KakaoPayApiCaller implements PaymentApiCaller<KakaoPayRequest> {
	@Override
	public boolean supports(PayMethod paymethod) {
		return PayMethod.KAKAO_PAY == paymethod;
	}

	@Override
	public void pay(KakaoPayRequest payRequest) {
		System.out.printf("카카오페이에만 필요한 데이터{%s}로 카카오페이에 결제요청함", payRequest.getKakaoPayData());
	}

}
