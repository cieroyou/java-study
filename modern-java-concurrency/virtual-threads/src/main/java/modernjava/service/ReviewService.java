package modernjava.service;

import modernjava.domain.Reviews;
import modernjava.util.CommonUtil;
import modernjava.util.LoggerUtil;

public class ReviewService {

    /**
     * 특정 시간 후에 동작 완료 시뮬레이팅 함수
     */
    public Reviews retrieveReviews(String productId, int seconds) {
        LoggerUtil.log("retrieveReviews method started, %d초 후에 완료".formatted(seconds));
        // int seconds = 3;
        CommonUtil.sleep(seconds * 1000);
        LoggerUtil.log("%d초 딜레이 후 retrieveReviews method 완료.".formatted(seconds));
        return new Reviews(200, 4.5);
    }

    /**
     * 특정 시간 후에 동작 예외 발생 시뮬레이팅 함수
     */
    public Reviews retrieveReviewsThrowsException(String productId, int seconds) {
        LoggerUtil.log("retrieveReviews method started, %d초 후에 예외 발생".formatted(seconds));
        CommonUtil.sleep(seconds * 1000);
        throw new RuntimeException("retrieveReview Exception Occurred");
    }
}
