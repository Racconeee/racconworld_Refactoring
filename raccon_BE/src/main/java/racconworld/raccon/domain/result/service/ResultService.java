package racconworld.raccon.domain.result.service;


import racconworld.raccon.domain.result.dto.Response.ShowResultResDto;

public interface ResultService {

    ShowResultResDto showResult(Long testId , String score);

}
