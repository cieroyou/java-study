package polymorphism_payment_service.domain.service;

import java.util.List;

import polymorphism_payment_service.domain.exception.UnsupportedPaymentRequestException;
import polymorphism_payment_service.domain.valueobjects.PayMethod;
import polymorphism_payment_service.domain.dto.PayRequest;

public class PaymentProcessor {
	private final List<PaymentApiCaller<? extends PayRequest>> paymentApiCallers;

	public PaymentProcessor(List<PaymentApiCaller<? extends PayRequest>> paymentApiCallers) {
		this.paymentApiCallers = paymentApiCallers;
	}

	public PayMethod pay(PayRequest payRequest) {
		PaymentApiCaller<? super PayRequest> paymentApiCaller = getPaymentApiCaller(payRequest.getPaymethod());
		return paymentApiCaller.pay(payRequest);
	}

	@SuppressWarnings("unchecked")
	PaymentApiCaller<PayRequest> getPaymentApiCaller(PayMethod payMethod) {
		return paymentApiCallers.stream()
			.filter(paymentApiCaller -> paymentApiCaller.supports(payMethod))
			.map(caller -> (PaymentApiCaller<PayRequest>)caller)
			.findFirst()
			.orElseThrow(
				() -> new UnsupportedPaymentRequestException("No payment api caller found for pay method: " + payMethod));
	}
}
