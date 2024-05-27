package modernjava.future;

import modernjava.domain.Product;
import modernjava.domain.ProductInfo;
import modernjava.domain.Reviews;
import modernjava.service.ProductInfoService;
import modernjava.service.ReviewService;
import modernjava.util.LoggerUtil;

import java.util.concurrent.*;

public class ProductServiceUsingExecutor {

    static ExecutorService executorService = Executors.newFixedThreadPool(6);

    private final ProductInfoService productInfoService;
    private final ReviewService reviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetailsThrowsProductDetailException(String productId) throws ExecutionException, InterruptedException {
        Future<ProductInfo> productInfoFuture = executorService.submit(
                () -> productInfoService.retrieveProductInfoThrowsException(productId, 2));
        Future<Reviews> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId, 5));
        LoggerUtil.log("ProductInfo Future 가 제출됨");

        ProductInfo productInfo = productInfoFuture.get(); // This is a  blocking call.
        LoggerUtil.log("ProductInfo 가 완료됨 ");
        LoggerUtil.log("Review Future 가 제출됨");
        Reviews reviews = reviewFuture.get(); // This is a  blocking call.
        LoggerUtil.log("Review가 완료됨");
        return new Product(productId, productInfo, reviews);
    }

    public Product retrieveProductDetailsWithReviewException(String productId) throws ExecutionException, InterruptedException {
        Future<ProductInfo> productInfoFuture = executorService.submit(
                () -> productInfoService.retrieveProductInfo(productId, 5));
        Future<Reviews> reviewFuture = executorService.submit(() -> reviewService.retrieveReviewsThrowsException(productId, 2));
        LoggerUtil.log("ProductInfo Future 가 제출됨");
        ProductInfo productInfo = productInfoFuture.get(); // This is a  blocking call.
        LoggerUtil.log("ProductInfo 가 완료됨 ");
        LoggerUtil.log("Review Future 가 제출됨");
        Reviews reviews = reviewFuture.get(); // This is a  blocking call.
        LoggerUtil.log("Review가 완료됨");
        return new Product(productId, productInfo, reviews);
    }

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

    public Product retrieveProductDetails(String productId) throws
            ExecutionException,
            InterruptedException,
            TimeoutException {

        Future<ProductInfo> productInfoFuture = executorService.submit(
                () -> productInfoService.retrieveProductInfo(productId, 5));
        Future<Reviews> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId, 3));

        LoggerUtil.log("ProductInfo Future 가 제출됨");
        ProductInfo productInfo = productInfoFuture.get(); // This is a  blocking call.
        LoggerUtil.log("ProductInfo 가 완료됨 ");
        LoggerUtil.log("Review Future 가 제출됨");
        //ProductInfo productInfo = productInfoFuture.get(2, TimeUnit.SECONDS);
        Reviews reviews = reviewFuture.get(); // This is a  blocking call.
        LoggerUtil.log("Review가 완료됨");
        //Review review = reviewFuture.get(2, TimeUnit.SECONDS);

        return new Product(productId, productInfo, reviews);

        //        결과: 첫번째 테스트 시나리오- productInfo 가 먼저 완료(3초)되고, review 가 나중에 완료(5)됨
        //         ProductDetails 함수 실행
        //          [2024-05-24 18:39:23:757][pool-1-thread-1] - retrieveProductInfo method started
        //          [2024-05-24 18:39:23:757][pool-1-thread-2] - retrieveReviews method started
        //          [2024-05-24 18:39:23:754][Test worker] - ProductInfo Future 가 제출됨
        //          [2024-05-24 18:39:27:126][pool-1-thread-1] - 3초 딜레이 후 retrieveProductInfo method 완료.
        //          [2024-05-24 18:39:27:129][Test worker] - ProductInfo 가 완료됨
        //          [2024-05-24 18:39:27:129][Test worker] - Review Future 가 제출됨
        //          [2024-05-24 18:39:29:124][pool-1-thread-2] - 5초 딜레이 후 retrieveReviews method 완료.
        //          [2024-05-24 18:39:29:125][Test worker] - Review가 완료됨

        // 결과 해석: 첫번째 테스트 시나리오- productInfo 가 먼저 완료(3초)되고, review 가 나중에 완료(5)됨
        // `retrieveProductInfo method started` 와 `retrieveReviews method started`, `ProductInfo Future가 제출됨` 는 거의 동시에 별개의 각기 스레드로 실행이되므로
        // 세개의 순서는 보장못하고, 랜덤으로 출력된다.
        // ProductInfo 가 완료됨 코드줄은 productInfo를 가져오는 3초가 완료된 후에 출력되므로, 해당 코드줄은 블로킹되어 있다고 볼 수 있다.
        // 블로킹 해제 후, ProductInfo 가 완료됨, Review Future 가 제출됨까지 출력되게 된다.
        // 하지만 reviewFuture.get()에서 블로킹되어 있으므로, Review가 완료됨은 reviewFuture.get()가 완료되고 나서 출력된다.
        // fureture.get()은 블로킹되어 있으므로, 해당 코드줄이 완료되기 전까지 다음 코드줄은 실행되지 않고, CompletebleFuture를 사용하여 논블로킹으로 처리할 수 있다.

        //        결과: 두번째 테스트 시나리오- productInfo 가 나중에 완료(5)되고, review 가 빨리 완료(3)됨
        //         [2024-05-24 18:46:42:624][pool-1-thread-2] - retrieveReviews method started
        //         [2024-05-24 18:46:42:620][Test worker] - ProductInfo Future 가 제출됨
        //         [2024-05-24 18:46:42:624][pool-1-thread-1] - retrieveProductInfo method started
        //         [2024-05-24 18:46:45:964][pool-1-thread-2] - 3초 딜레이 후 retrieveReviews method 완료.
        //         [2024-05-24 18:46:47:970][pool-1-thread-1] - 5초 딜레이 후 retrieveProductInfo method 완료.
        //         [2024-05-24 18:46:47:970][Test worker] - ProductInfo 가 완료됨
        //         [2024-05-24 18:46:47:971][Test worker] - Review Future 가 제출됨
        //         [2024-05-24 18:46:47:971][Test worker] - Review가 완료됨

        // 결과 해석: 두번째 테스트 시나리오- productInfo 가 나중에 완료(5)되고, review 가 빨리 완료(3)됨
        // `retrieveProductInfo method started` 와 `retrieveReviews method started`, `ProductInfo Future가 제출됨` 는 거의 동시에 별개의 각기 스레드로 실행이되므로
        // 세개의 순서는 보장못하고, 랜덤으로 출력된다.
        // 주목할 것은 ProductInfo 가 완료됨 코드줄 이전에 Review 완료 출력이 된 후, ProductInfo 가 완료됨이 출력되는 사실이다.
        // 이는 productInfoFuture.get()가 블로킹되어 있어서, productInfoFuture.get()가 완료되기 전까지 다음 코드줄은 실행되지 않는다.
        // 하지만 review 는 product 보다  먼저 완료되기 때문에 출력될 수 밖에 없다는 것이다.
        // Review와 Product 작업은 모두 완료되었기 때문에 블로킹될 필요 없이, 33 번 코드줄 부터 순차적으로 쭉 실행된다.

    }
}
