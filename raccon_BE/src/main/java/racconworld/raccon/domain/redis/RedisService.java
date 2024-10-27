package racconworld.raccon.domain.redis;


import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;

public interface RedisService {
    // 단일 조회수
    void syncViewCountsToDB();
    ShowTestViewListResDto getViewCount(Long testId);
    void incrementViewCount(Long testId);

    // 전체 조회수
    void syncTotalTestViewCount();
    Long totalTestViewCount();
    Long calculateTotalViewCount();
    void clearTestListCache();

}