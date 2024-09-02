package racconworld.raccon.global.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //Token
    INVALID_ACCESS_TOKEN_EXCEPTION(403, "Token", "유효하지 않은 ACCESS TOKEN 입니다."),
    INVALID_REFRESH_TOKEN_EXCEPTION(403, "Token", "유효하지 않은 REFRESH TOKEN 입니다."),

    EXPIRED_ACCESS_TOKEN_EXCEPTION(403, "Token", "만료된 ACCESS TOKEN 입니다."),
    EXPIRED_REFRESH_TOKEN_EXCEPTION(403, "Token", "만료된 REFRESH TOKEN 입니다."),


    NONEXTRACT_ACCESS_TOKEN_EXCEPTION(403, "Token", "REQUEST-HEADER에 ACCESS TOKEN이 존재하지 않습니다.."),
    NONEXTRACT_REFRESH_TOKEN_EXCEPTION(403, "Token", "REQUEST-HEADER에 REFRESH TOKEN이 존재하지 않습니다.."),

    INCONSISTENT_ACCESS_TOKEN_EXCEPTION(403, "Token", "일치하지 않는 ACCESS TOKEN 입니다."),
    INCONSISTENT_REFRESH_TOKEN_EXCEPTION(403, "Token", "일치하지 않는 REFRESH TOKEN 입니다."),
    EXISTENCE_TOKEN_EXCEPTION(403, "Token", "존재하지 않는 REFRESH TOKEN 입니다."),
    TMP(999, "Token", "TEMP 값을 변경하세요"),


    BAD_REQUEST(400, "400", "Bad Request"),
    NOT_FOUND(404, "404", "Not Found"),
    INTERNAL_SERVER_ERROR(500, "500", "Internal Server Error");

    private final int status;
    private final String errorCode;
    private final String message;

    ErrorCode(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
