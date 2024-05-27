package modernjava.service;

import modernjava.domain.ProductInfo;
import modernjava.domain.ProductOption;
import modernjava.util.CommonUtil;
import modernjava.util.LoggerUtil;

import java.util.List;

public class ProductInfoService {
    public ProductInfo retrieveProductInfo(String productId, int seconds) {
        LoggerUtil.log("retrieveProductInfo method started, %d초 후에 완료".formatted(seconds));
        CommonUtil.sleep(seconds * 1000);
        //        throw new RuntimeException("retrieveProductInfo");
        List<ProductOption> productOptions = List.of(new ProductOption("64GB", "Black", 699.99),
                new ProductOption("128GB", "Black", 749.99));
        LoggerUtil.log("%d초 딜레이 후 retrieveProductInfo method 완료.".formatted(seconds));
        return new ProductInfo(productId, productOptions);
    }

    public ProductInfo retrieveProductInfoThrowsException(String productId, int seconds) {
        LoggerUtil.log("retrieveProductInfo method started, %d초 후에 예외 발생".formatted(seconds));
        CommonUtil.sleep(seconds * 1000);
        throw new RuntimeException("retrieveProductInfo Exception Occurred");
    }

}
