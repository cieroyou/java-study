package modernjava.service;

import modernjava.domain.Reviews;
import modernjava.util.CommonUtil;
import modernjava.util.LoggerUtil;

public class ReviewService {

    public Reviews retrieveReviews(String productId) {
        LoggerUtil.log("retrieveReviews method started");
        CommonUtil.sleep(3000);
        LoggerUtil.log("retrieveReviews after Delay");
        return new Reviews(200, 4.5);
    }
}
