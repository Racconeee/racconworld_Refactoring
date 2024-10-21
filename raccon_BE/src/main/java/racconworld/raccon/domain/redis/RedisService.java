package racconworld.raccon.domain.redis;


import com.fasterxml.jackson.core.JsonProcessingException;
import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;

import java.util.Map;

public interface RedisService {
    void syncViewCountsToDB();
    void saveTestViewInRedis(Long testId);
    ShowTestViewListResDto getViewCount(Long testId);
    void incrementViewCount(Long testId);
    Long totalTestViewCount();
}