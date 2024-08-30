package racconworld.raccon.domain.result.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import racconworld.raccon.domain.result.service.ResultService;
import racconworld.raccon.global.common.BaseResponse;
import racconworld.raccon.global.common.code.SuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    @GetMapping()
    public ResponseEntity<BaseResponse<String>> showResult(@RequestParam Long score,
                                                            @RequestParam Long testId){

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS ,
                resultService.showResult(score , testId);

        )
    }
}
