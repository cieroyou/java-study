package modernjava.service;

import modernjava.domain.ProductInfo;
import modernjava.domain.ProductOption;
import modernjava.util.CommonUtil;
import modernjava.util.LoggerUtil;

import java.util.List;

public class ProductInfoService {
    public ProductInfo retrieveProductInfo(String productId) {
        LoggerUtil.log("retrieveProductInfo method started");
        CommonUtil.sleep(10000);
//        throw new RuntimeException("retrieveProductInfo");
        List<ProductOption> productOptions = List.of(new ProductOption("64GB", "Black", 699.99),
                new ProductOption("128GB", "Black", 749.99));
        LoggerUtil.log("retrieveProductInfo after Delay");
        return new ProductInfo(productId, productOptions);
    }
}
