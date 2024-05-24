package modernjava.service;

import modernjava.domain.Reviews;
import modernjava.util.CommonUtil;
import modernjava.util.LoggerUtil;

public class ReviewService {

	public Reviews retrieveReviews(String productId) {
		LoggerUtil.log("retrieveReviews method started");
		int seconds = 3;
		CommonUtil.sleep(seconds * 1000);
		LoggerUtil.log("%d초 딜레이 후 retrieveReviews method 완료.".formatted(seconds));
		return new Reviews(200, 4.5);
	}
}
