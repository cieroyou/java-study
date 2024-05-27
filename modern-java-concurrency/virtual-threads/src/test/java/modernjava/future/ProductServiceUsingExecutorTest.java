package modernjava.future;

import modernjava.service.ProductInfoService;
import modernjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingExecutorTest {

    @Spy
    ProductInfoService productInfoService;

    @Spy
    ReviewService reviewService;

    @InjectMocks
    ProductServiceUsingExecutor productServiceUsingExecutor;

    @Test
    void retrieveProductDetails() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("ProductDetails 함수 실행");
        long startTIme = System.currentTimeMillis();
        var product = productServiceUsingExecutor.retrieveProductDetails("ABC");
        long endTIme = System.currentTimeMillis();
        System.out.println("Time Taken : " + (endTIme - startTIme));
        assertNotNull(product);
        Thread.sleep(12000);
    }

    @Test
    void retrieveProductDetailsWithProductException() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("ProductDetails 함수 실행");
        long startTIme = System.currentTimeMillis();
        String expectedExceptionMessage = "retrieveProductInfo Exception Occurred";
        var exception = Assertions.assertThrows(ExecutionException.class,
                () -> productServiceUsingExecutor.retrieveProductDetailsThrowsProductDetailException("ABC"));
        assertEquals("java.lang.RuntimeException: %s".formatted(expectedExceptionMessage), exception.getMessage());
        Thread.sleep(10000);
    }

    @Test
    void retrieveProductDetailsWithReviewException() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("ProductDetails 함수 실행");
        long startTIme = System.currentTimeMillis();
        String expectedExceptionMessage = "retrieveReview Exception Occurred";
        var exception = Assertions.assertThrows(ExecutionException.class,
                () -> productServiceUsingExecutor.retrieveProductDetailsWithReviewException("ABC"));
        assertEquals("java.lang.RuntimeException: %s".formatted(expectedExceptionMessage), exception.getMessage());
        Thread.sleep(10000);
    }

    @DisplayName("Main Exception 발생하더라도 하위 Future는 실행되어야 한다.")
    @Test
    void retrieveProductDetailsWithMainException() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("ProductDetails 함수 실행");
        long startTIme = System.currentTimeMillis();
        String expectedExceptionMessage = "Main Exception Occurred";
        var exception = Assertions.assertThrows(RuntimeException.class,
                () -> productServiceUsingExecutor.retrieveProductDetailsWithMainException("ABC"));
        assertEquals(expectedExceptionMessage, exception.getMessage());
        Thread.sleep(10000);
    }

}