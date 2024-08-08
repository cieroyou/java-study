package polymorphism_payment_service.domain.service;

import polymorphism_payment_service.domain.dto.PayRequest;
import polymorphism_payment_service.domain.valueobjects.PayMethod;

public interface PaymentApiCaller<T extends PayRequest> {
	boolean supports(PayMethod paymethod);

	PayMethod pay(T payRequest);
}
