package polymorphism.v2.dto;

import lombok.Getter;
import polymorphism.v2.PayMethod;

@Getter
public class PayRequest {
	PayMethod paymethod;
	Long price;

	public PayRequest(Long price, PayMethod payMethod) {
		this.price = price;
		this.paymethod = payMethod;
	}
}
