package polymorphism_payment_service.infrastructure.dto;

import lombok.Getter;
import polymorphism_payment_service.domain.dto.PayRequest;
import polymorphism_payment_service.domain.valueobjects.PayMethod;

@Getter
public class NaverPayRequest extends PayRequest {
	private final String naverPayData;

	public NaverPayRequest(Long price, String naverPayData) {
		super(price, PayMethod.NAVER_PAY);
		this.naverPayData = naverPayData;
	}

}
