package racconworld.raccon.domain.result.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racconworld.raccon.domain.result.dto.Response.ShowResultResDto;
import racconworld.raccon.domain.result.service.ResultService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    // Get에서는 Dto 사용 할 필요가없다.
    @GetMapping("/show")
    public ResponseEntity<BaseResponse<ShowResultResDto>> showResult(@RequestParam Long testId ,
                                                                     @RequestParam String score){

        resultService.incrementTestView(testId);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS ,
                resultService.showResult(testId, score)

        );
    }
}
