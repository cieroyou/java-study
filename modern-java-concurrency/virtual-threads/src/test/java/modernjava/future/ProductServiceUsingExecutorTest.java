package modernjava.future;

import modernjava.service.ProductInfoService;
import modernjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
    void retrieveProductDetailsException() throws InterruptedException {
        when(productInfoService.retrieveProductInfo(anyString())).thenThrow(new RuntimeException("Exception Occurred"));
        var exception = Assertions.assertThrows(ExecutionException.class, () -> productServiceUsingExecutor.retrieveProductDetails("ABC"));
        assertEquals("java.lang.RuntimeException: Exception Occurred", exception.getMessage());

        Thread.sleep(2000);
    }
}