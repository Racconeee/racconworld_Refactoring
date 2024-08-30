package racconworld.raccon.global.common.code;

import lombok.Getter;

/*
 * final 키워드는 불변성을 위해 사용
 *
 * status    HTTP 상태 코드
 * errorCode 오류 코드 (식별자)
 * message   오류 메시지
 *
 * */

@Getter
public enum SuccessCode {
    SELECT_SUCCESS(200, "200", "SELECT SUCCESS");


    private final int status;
    private final String code;
    private final String message;

    // 생성자 구성
    SuccessCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}