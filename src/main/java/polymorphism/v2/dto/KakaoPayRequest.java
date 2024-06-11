package polymorphism.v2.dto;

import lombok.Getter;
import polymorphism.v2.PayMethod;

@Getter
public class KakaoPayRequest extends PayRequest {
	private final String kakaoPayData;

	public KakaoPayRequest(Long price, String kakaoPayData) {
		super(price, PayMethod.KAKAO_PAY);
		this.kakaoPayData = kakaoPayData;
	}
}
