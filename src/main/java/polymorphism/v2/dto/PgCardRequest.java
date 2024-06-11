package polymorphism.v2.dto;

import lombok.Getter;
import polymorphism.v2.PayMethod;

@Getter
public class PgCardRequest extends PayRequest {
	private final String pgCardData;

	public PgCardRequest(Long price, String pgCardData) {
		super(price, PayMethod.CARD);
		this.pgCardData = pgCardData;
	}

}
