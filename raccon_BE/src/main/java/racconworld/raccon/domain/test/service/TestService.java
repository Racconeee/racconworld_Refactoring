package racconworld.raccon.domain.test.service;


import racconworld.raccon.domain.test.dto.Response.ShowTestResDto;
import racconworld.raccon.domain.upload.dto.Response.TestTotalVisitResDto;


public interface TestService {

    ShowTestResDto getTestListByPage(int pageNumber);
    TestTotalVisitResDto getTestTotalVisit();
}
