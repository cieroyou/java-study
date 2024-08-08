package polymorphism_payment_service.infrastructure.dto;

import lombok.Getter;
import polymorphism_payment_service.domain.dto.PayRequest;
import polymorphism_payment_service.domain.valueobjects.PayMethod;

@Getter
public class PgCardRequest extends PayRequest {
	private final String pgCardData;

	public PgCardRequest(Long price, String pgCardData) {
		super(price, PayMethod.CARD);
		this.pgCardData = pgCardData;
	}

}
