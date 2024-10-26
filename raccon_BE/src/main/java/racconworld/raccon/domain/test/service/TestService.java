package racconworld.raccon.domain.test.service;


import racconworld.raccon.domain.test.dto.Response.ShowTestViewListResDto;
import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.test.dto.Response.TestTotalVisitResDto;

import java.util.List;


public interface TestService {

    ShowTestResDto getTestListByPage(int pageNumber);
    TestTotalVisitResDto getTestTotalVisit();
//    List<ShowTestViewListResDto> getTestViewCounts(List<Long> testIds);
    List<ShowTestViewListResDto> getTestViewList(List<Long> testIds);
}
