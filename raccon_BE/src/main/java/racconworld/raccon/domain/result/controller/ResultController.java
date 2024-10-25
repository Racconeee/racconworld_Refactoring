package racconworld.raccon.domain.result.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "결과 image path 반환",
            description =  "결과 반환할 때 REDIS에 VIEW +1 증가")
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
