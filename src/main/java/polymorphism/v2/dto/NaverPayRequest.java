package polymorphism.v2.dto;

import lombok.Getter;
import polymorphism.v2.PayMethod;

@Getter
public class NaverPayRequest extends PayRequest {
	private final String naverPayData;

	public NaverPayRequest(Long price, String naverPayData) {
		super(price, PayMethod.NAVER_PAY);
		this.naverPayData = naverPayData;
	}

}
