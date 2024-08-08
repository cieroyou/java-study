package polymorphism_payment_service.infrastructure;

import lombok.Getter;
import polymorphism_payment_service.domain.valueobjects.PayMethod;
import polymorphism_payment_service.infrastructure.dto.PgCardRequest;
import polymorphism_payment_service.domain.service.PaymentApiCaller;

@Getter
public class PgCardApiCaller implements PaymentApiCaller<PgCardRequest> {
	@Override
	public boolean supports(PayMethod paymethod) {
		return PayMethod.CARD == paymethod;
	}

	@Override
	public PayMethod pay(PgCardRequest payRequest) {
		System.out.printf("PG사 결제에만 필요한 데이터{%s}로 PGCard에 결제요청함", payRequest);
		return payRequest.getPaymethod();
	}
}
