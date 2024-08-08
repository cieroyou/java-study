# Polymorphism Payment Service

## Overview
다형성 결제 서비스(Polymorphism Payment Service)는 다형성 원리를 이용하여 다양한 결제 방식을 처리하도록 설계된 Java 기반 프로젝트입니다.

## Project Structure
```
main.java.polymorphism_payment_service
├── domain
│   ├── service
│   │   ├── PaymentApiCaller.java
│   │   └── PaymentProcessor.java
│   └── dto
│       └── PayRequest.java
│   └── exception
│       └── UnsupportedPaymentRequestException.java
├── infrastructure
│   └── KakaoPayApiCaller.java
```
- `domain`: 도메인 로직을 포함합니다.
   - `dto`: 데이터 전송 객체 (DTO) 클래스가 포함됩니다.
   - `exception`: 사용자 정의 예외 클래스가 포함됩니다.
   - `service`: 서비스 클래스가 포함됩니다.
   - `valueobjects`: 값 객체 클래스가 포함됩니다.
- `infrastructure`: 인프라스트럭처 관련 클래스가 포함됩니다.
   - `dto`: 인프라스트럭처 관련 DTO 클래스가 포함됩니다.

## Key Components
- **PaymentApiCaller**: 다양한 결제 API 호출을 처리하기 위한 Interface입니다.
- **PaymentProcessor**: 다양한 결제 API 호출자를 관리하고, 결제 요청을 처리합니다.
- **PayRequest**: 클래스는 결제 요청을 나타내는 DTO입니다.
- **PayMethod**: 지원되는 결제방법을 정의한 enum 클래스입니다.
- **UnsupportedPaymentRequestException**: 지원되지 않은 결제요청시 발생되는 CustomException 입니다.
- **KakaoPayApiCaller**: `PaymentApiCaller` 의 구현부입니다.

## Test
- 카카오페이로 요청한 경우, 실제 처리된 PaymentApiCaller의 구현부가 `KakaoPayApiCaller` 가 맞는지 확인합니다.
- 지원되지 않은 결제로 요청한 경우, `UnsupportedPaymentRequestException` 로 처리되는지 확인합니다.

