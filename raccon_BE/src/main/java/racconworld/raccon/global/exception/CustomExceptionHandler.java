package racconworld.raccon.global.exception;


import lombok.Getter;
import racconworld.raccon.global.common.code.ErrorCode;
/*
* Errocode 에서 열거한 에러들만 사용하여 에러 처리함
* super()을 통해 runtimeEeception 으로 message 보내기
* 예외에서 가장 상위인 runtimeEeception 사용
*
* */
@Getter
public class CustomExceptionHandler extends RuntimeException {

    private final ErrorCode errorCode;


    public CustomExceptionHandler(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomExceptionHandler(ErrorCode errorCode , String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
