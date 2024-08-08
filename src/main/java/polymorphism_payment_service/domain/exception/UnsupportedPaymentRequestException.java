package polymorphism_payment_service.domain.exception;

public class UnsupportedPaymentRequestException extends RuntimeException{
	public UnsupportedPaymentRequestException(String message){
		super(message);
	}
}
