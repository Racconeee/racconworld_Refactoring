package racconworld.raccon.global.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

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
