package racconworld.raccon.domain.test.service;


import racconworld.raccon.domain.test.dto.Response.showTestResDto;


public interface TestService {

    showTestResDto getTestListByPage(int pageNumber);
}
