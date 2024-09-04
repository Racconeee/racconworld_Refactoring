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
    SELECT_SUCCESS(200, "SELECT_SUCCESS", "SELECT SUCCESS"),
    DELETE_SUCCESS(200, "DELETE_SUCCESS", "DELETE SUCCESS"),
    SIGNUP_SUCCESS(201, "SIGNUP_SUCCESS", "SIGNUP SUCCESS"),
    UPLOAD_SUCCESS(201, "UPLOAD_SUCCESS", "UPLOAD SUCCESS"),
    LOGIN_SUCCESS(200, "LOGIN_SUCCESS", "로그인에 성공했습니다.");


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