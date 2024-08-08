package polymorphism_payment_service.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import polymorphism_payment_service.domain.dto.PayRequest;
import polymorphism_payment_service.domain.valueobjects.PayMethod;

@Getter
public class KakaoPayRequest extends PayRequest {
	private final String name;
	private final String cardNumber;
	private final String svv;

	@Builder
	public KakaoPayRequest(Long price, String name, String cardNumber, String svv) {
		super(price, PayMethod.KAKAO_PAY);
		this.name = name;
		this.cardNumber = cardNumber;
		this.svv = svv;
	}



	@Override
	public String toString() {
		return "KakaoPayRequest{" +
			"name='" + name + '\'' +
			", cardNumber='" + cardNumber + '\'' +
			", svv='" + svv + '\'' +
			", paymethod=" + paymethod +
			", price=" + price +
			'}';
	}
}
