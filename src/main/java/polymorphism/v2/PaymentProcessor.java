package polymorphism.v2;

import java.util.List;

import polymorphism.v2.dto.PayRequest;

public class PaymentProcessor {
	private final List<PaymentApiCaller<? extends PayRequest>> paymentApiCallers;

	public PaymentProcessor(List<PaymentApiCaller<? extends PayRequest>> paymentApiCallers) {
		this.paymentApiCallers = paymentApiCallers;
	}

	public void pay(PayRequest payRequest) {
		PaymentApiCaller<? super PayRequest> paymentApiCaller = getPaymentApiCaller(payRequest.getPaymethod());
		paymentApiCaller.pay(payRequest);
	}

	@SuppressWarnings("unchecked")
	PaymentApiCaller<PayRequest> getPaymentApiCaller(PayMethod payMethod) {
		return paymentApiCallers.stream()
			.filter(paymentApiCaller -> paymentApiCaller.supports(payMethod))
			.map(caller -> (PaymentApiCaller<PayRequest>)caller)
			.findFirst()
			.orElseThrow(
				() -> new IllegalArgumentException("No payment api caller found for pay method: " + payMethod));
	}
}
