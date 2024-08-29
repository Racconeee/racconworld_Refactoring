package racconworld.raccon.domain.test.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racconworld.raccon.domain.test.service.TestService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/test")
public class TestController {

    private final TestService testService;


//    @GetMapping("/list")
//    public ResponseEntity<List<ShowTestDto>> getTestList(@RequestParam(defaultValue = "0") int page) {
//        List<ShowTestDto> testList = testService.getTestListByPage(page);
//        return ResponseEntity.ok().body(testList);
//    }



}
