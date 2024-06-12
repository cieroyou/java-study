package polymorphism.v2;

import polymorphism.v2.dto.PayRequest;

public interface PaymentApiCaller<T extends PayRequest> {
	boolean supports(PayMethod paymethod);

	PayMethod pay(T payRequest);
}
