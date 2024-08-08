package polymorphism_payment_service.domain.dto;

import lombok.Getter;
import polymorphism_payment_service.domain.valueobjects.PayMethod;

@Getter
public class PayRequest {
	protected PayMethod paymethod;
	protected Long price;

	public PayRequest(Long price, PayMethod payMethod) {
		this.price = price;
		this.paymethod = payMethod;
	}
}
