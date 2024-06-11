package polymorphism.v2.infrastructure;

import lombok.Getter;
import polymorphism.v2.PayMethod;
import polymorphism.v2.PaymentApiCaller;
import polymorphism.v2.dto.PgCardRequest;

@Getter
public class PgCardApiCaller implements PaymentApiCaller<PgCardRequest> {
	@Override
	public boolean supports(PayMethod paymethod) {
		return PayMethod.CARD == paymethod;
	}

	@Override
	public void pay(PgCardRequest payRequest) {
		System.out.printf("PG사 결제에만 필요한 데이터{%s}로 PGCard에 결제요청함", payRequest);
	}
}
