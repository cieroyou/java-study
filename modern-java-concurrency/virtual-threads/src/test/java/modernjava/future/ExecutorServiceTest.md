# ExecutorService 를 이용한 동시성 적용

## 테스트 목적

- Future.get() 은 스레드 블록킹이다.
- 상위, 하위 작업들은 별개의 스레드로 돌고, 이 중 하나라도 예외가 발생하더라도 별개의 스레드로 계속 동작한다. 이것을 비구조적 동시성이라고 한다.

## 비즈니스 로직

상품정보(ProductInfo) 과 상품리뷰(Reviews) 를 Product 로 취합하여 반환한다.

- 상위작업 : Product 반환
- 하위작업
    - ProductInfo 반환
    - Reviews 반환

### Entity

- [Product](https://github.com/cieroyou/java-study/blob/main/modern-java-concurrency/virtual-threads/src/main/java/modernjava/domain/Product.java)
- [ProductInfo](https://github.com/cieroyou/java-study/blob/main/modern-java-concurrency/virtual-threads/src/main/java/modernjava/domain/ProductInfo.java)
- [Reviews](https://github.com/cieroyou/java-study/blob/main/modern-java-concurrency/virtual-threads/src/main/java/modernjava/domain/Reviews.java)

### Code

```java
public Product retrieveProductDetails(String productId) {
    Future<ProductInfo> productInfoFuture = executorService.submit(
            () -> productInfoService.retrieveProductInfo(productId));
    Future<Reviews> reviewFuture = executorService.submit(
            () -> reviewService.retrieveReviews(productId));

    LoggerUtil.log("ProductInfo Future 가 제출됨");
    ProductInfo productInfo = productInfoFuture.get(); // This is a  blocking call.
    LoggerUtil.log("ProductInfo 가 완료됨 ");
    LoggerUtil.log("Review Future 가 제출됨");

    Reviews reviews = reviewFuture.get(); // This is a  blocking call.
    LoggerUtil.log("Review가 완료됨");

    return new Product(productId, productInfo, reviews);
}
```

## 블로킹 동작 테스트

### 스레드 블락킹 테스트1 - ProductInfo 반환이 Review 반환보다 오래 걸리는 경우

- 결과

```java
[2024-05-24 18:46:42:624][pool-1-thread-2]-
retrieveReviews method
started
[2024-05-24 18:46:42:620][
Test worker]-
ProductInfo Future
가 제출됨
[2024-05-24 18:46:42:624][pool-1-thread-1]-
retrieveProductInfo method
started
[2024-05-24 18:46:45:964][pool-1-thread-2]-3
초 딜레이
후 retrieveReviews
method 완료.
        [2024-05-24 18:46:47:970][pool-1-thread-1]-5
초 딜레이
후 retrieveProductInfo
method 완료.
        [2024-05-24 18:46:47:970][
Test worker]-
ProductInfo 가
완료됨
[2024-05-24 18:46:47:971][
Test worker]-
Review Future
가 제출됨
[2024-05-24 18:46:47:971][
Test worker]-
Review가 완료됨
```

- `productInfoService.retrieveProductInfo`가 반환되는데 5초가 걸리고, ProductInfo 가 반환완료가 되면 `ProductInfo 가 완료됨`이 출력된다
    - Test worker 스레드는 ProductInfo 를 반환하는 작업이 완료될 때까지 Blocking 되어 있다.
- Review를 가져오는 `reviewFuture.get()`에 blocking 되지 않고 `ProductInfo가 완료됨`, `Review Future 가 제출됨`, `Review가 완료됨` 순으로 코드줄이
  실행됨
    - Review는 ProductInfo 보다 빨리 수행되므로 `reviewFuture.get()` 에서 blocking 되지 않는다. 만약 Review 가 ProductInfo 보다 더 완료가 늦다면 다른
      결과를 가져올 것이다.

### 스레드 블락킹 테스트2 - ProductInfo 반환이 Review 반환보다 짧게 걸리는 경우

- 결과

```java
[2024-05-24 18:39:23:757][pool-1-thread-1]-
retrieveProductInfo method
started
[2024-05-24 18:39:23:757][pool-1-thread-2]-
retrieveReviews method
started
[2024-05-24 18:39:23:754][
Test worker]-
ProductInfo Future
가 제출됨
[2024-05-24 18:39:27:126][pool-1-thread-1]-3
초 딜레이
후 retrieveProductInfo
method 완료.
        [2024-05-24 18:39:27:129][
Test worker]-
ProductInfo 가
완료됨
[2024-05-24 18:39:27:129][
Test worker]-
Review Future
가 제출됨
[2024-05-24 18:39:29:124][pool-1-thread-2]-5
초 딜레이
후 retrieveReviews
method 완료.
        [2024-05-24 18:39:29:125][
Test worker]-
Review가 완료됨
```

- Test worker 가 로그줄을 실행시키고, `pool-1-thread2` 가 Review 정보를 다 가져올 때까지 `reviewFuture.get()` 에서 블로킹된다.
    - productInfo 는 다 수행했지만 Review 정보는 아직 수행완료를 하지 않았기 때문에 Review 정보를 가져올 때까지 메인스레드가 블록킹 된다.

### 결론

- 하위작업을 가져오려면 future.get() 으로 결과값을 취합하여야 한다. 하지만 취합한 결과는 하위작업들이 전부 완료가 되지 않는 이상 계속 스레드는 블로킹됨을 유의한다.
- 상위작업실행(`Test worker`), 하위작업A(`pool-1-thread-1`), 하위작업B(`pool-1-thread-2`) 는 submit() 하는 순간 동시에
  실행되므로 `retrieveReviews method started`, `ProductInfo Future 가 제출됨` , `retrieveReviews method started` 는 랜덤으로 실행된다. 무조건
  상위작업이 먼저 실행되는 것이 아님에 유의한다.

## 비구조적 동시성 테스트

### 테스트1 - `retrieveProductInfo()` 에서 예외가 발생한 경우

- 결과

```java
[2024-05-27 19:45:46:176][Test worker]-
ProductInfo Future
가 제출됨
[2024-05-27 19:45:46:177][pool-1-thread-2]-
retrieveReviews method
started,5
초 후에
완료
[2024-05-27 19:45:46:177][pool-1-thread-1]-
retrieveProductInfo method
started,2
초 후에
예외 발생
[2024-05-27 19:45:51:487][pool-1-thread-2]-5
초 딜레이
후 retrieveReviews
method 완료.
```

- retrieveProductInfo() 에서 예외가 발생하지만, retrieveReviews()를 반환하는 함수는 취소되지 않고 별도의 스레드에서 계속 실행된다.

### 테스트2 - `retrieveReviews()` 에서 예외가 발생한 경우

- 결과

```java
[2024-05-27 19:54:17:509][Test worker]-
ProductInfo Future
가 제출됨
[2024-05-27 19:54:17:510][pool-1-thread-2]-
retrieveReviews method
started,2
초 후에
예외 발생
[2024-05-27 19:54:17:510][pool-1-thread-1]-
retrieveProductInfo method
started,5
초 후에
완료
[2024-05-27 19:54:22:834][pool-1-thread-1]-5
초 딜레이
후 retrieveProductInfo
method 완료.
        [2024-05-27 19:54:22:836][
Test worker]-
ProductInfo 가
완료됨 
[2024-05-27 19:54:22:836][
Test worker]-
Review Future
가 제출됨
```

- `retrieveReviews()` 는 예외가 빨리 발생했지만, `retrieveProduct()` 는 별개의 스레드에서 완료될 때가지 계속 실행한다.,
- `LoggerUtil.log("ProductInfo 가 완료됨 ");` 과 `LoggerUtil.log("Review Future 가 제출됨");` 코드줄을 실행한다.
    - 결국 `retrieveReviews()` 는 예외가 발생하더라도, `reviewFuture.get()` 까지의 상위작업 `retrieveProductDetails()` 은 영향을 끼치지 않고 계속
      실행한다.

### 테스트3 - 상위작업에서 예외가 발생한 경우

```java
public Product retrieveProductDetailsWithMainException(String productId) throws ExecutionException, InterruptedException {
    Future<ProductInfo> productInfoFuture = executorService.submit(
            () -> productInfoService.retrieveProductInfo(productId, 5));
    Future<Reviews> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId, 2));
    throwException();
    LoggerUtil.log("ProductInfo Future 가 제출됨");
    ProductInfo productInfo = productInfoFuture.get(); // This is a  blocking call.
    LoggerUtil.log("ProductInfo 가 완료됨 ");
    LoggerUtil.log("Review Future 가 제출됨");
    Reviews reviews = reviewFuture.get(); // This is a  blocking call.
    LoggerUtil.log("Review가 완료됨");
    return new Product(productId, productInfo, reviews);
}

public void throwException() {
    throw new RuntimeException("Main Exception Occurred");
}
```

- 결과

```java
[2024-05-27 20:12:42:579][pool-1-thread-1]-
retrieveProductInfo method
started,5
초 후에
완료
[2024-05-27 20:12:42:579][pool-1-thread-2]-
retrieveReviews method
started,2
초 후에
완료
[2024-05-27 20:12:44:881][pool-1-thread-2]-2
초 딜레이
후 retrieveReviews
method 완료.
        [2024-05-27 20:12:47:883][pool-1-thread-1]-5
초 딜레이
후 retrieveProductInfo
method 완료.
```

- retrieveProductDetailsWithMainException() 에서 에러가 발생하더라도, 하위작업들은 별개로 계속 실행된다.