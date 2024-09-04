package racconworld.raccon.global.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //Token
    INVALID_ACCESS_TOKEN(403, "TOKEN_INVALID", "유효하지 않은 ACCESS TOKEN 입니다."),
    INVALID_REFRESH_TOKEN(403, "TOKEN_INVALID", "유효하지 않은 REFRESH TOKEN 입니다."),
    EXPIRED_ACCESS_TOKEN(403, "TOKEN_EXPIRED", "만료된 ACCESS TOKEN 입니다."),
    EXPIRED_REFRESH_TOKEN(403, "TOKEN_EXPIRED", "만료된 REFRESH TOKEN 입니다."),
    MISSING_ACCESS_TOKEN(403, "TOKEN_MISSING", "REQUEST-HEADER에 ACCESS TOKEN이 존재하지 않습니다."),
    MISSING_REFRESH_TOKEN(403, "TOKEN_MISSING", "REQUEST-HEADER에 REFRESH TOKEN이 존재하지 않습니다."),
    MISMATCH_ACCESS_TOKEN(403, "TOKEN_MISMATCH", "일치하지 않는 ACCESS TOKEN 입니다."),
    MISMATCH_REFRESH_TOKEN(403, "TOKEN_MISMATCH", "일치하지 않는 REFRESH TOKEN 입니다."),
    INVALID_ACCESS_TOKEN_SIGNATURE(403, "TOKEN_INVALID_SIGNATURE", "토큰의 서명이 유효하지 않습니다."),
    TOKEN_NOT_FOUND(403, "TOKEN_NOT_FOUND", "존재하지 않는 REFRESH TOKEN 입니다."),

    // 그 외 예외 코드
    BAD_REQUEST(400, "BAD_REQUEST", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),
    FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),
    NOT_FOUND(404, "NOT_FOUND", "요청한 리소스를 찾을 수 없습니다."),

    TEST_NOT_FOUND(404, "NOT_FOUND", "요청한 TEST 리소스를 찾을 수 없습니다."),
    EXIST_SAME_TEST(404, "EXIST_SAME", "이미 같은 이름의 TEST가 존재합니다."),

    RESULT_NOT_FOUND(404, "NOT_FOUND", "요청한 RESULT 리소스를 찾을 수 없습니다."),



    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", "허용되지 않은 메서드입니다."),
    NOT_ACCEPTABLE(406, "NOT_ACCEPTABLE", "수락할 수 없는 요청입니다."),
    UNSUPPORTED_MEDIA_TYPE(415, "UNSUPPORTED_MEDIA_TYPE", "지원되지 않는 미디어 타입입니다."),
    TOO_MANY_REQUESTS(429, "TOO_MANY_REQUESTS", "요청이 너무 많습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 내부 오류입니다."),
    NOT_IMPLEMENTED(501, "NOT_IMPLEMENTED", "지원되지 않는 기능입니다."),
    BAD_GATEWAY(502, "BAD_GATEWAY", "잘못된 게이트웨이입니다."),
    SERVICE_UNAVAILABLE(503, "SERVICE_UNAVAILABLE", "서비스를 사용할 수 없습니다."),
    GATEWAY_TIMEOUT(504, "GATEWAY_TIMEOUT", "게이트웨이 시간 초과입니다."),

    // 사용자 관련 예외 코드
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(409, "USER_ALREADY_EXISTS", "사용자가 이미 존재합니다."),
    INVALID_USER_CREDENTIALS(401, "INVALID_USER_CREDENTIALS", "잘못된 사용자 자격 증명입니다."),
    USER_LOCKED(423, "USER_LOCKED", "사용자 계정이 잠겼습니다."),
    USER_DISABLED(403, "USER_DISABLED", "사용자 계정이 비활성화되었습니다."),

    // 데이터 관련 예외 코드
    DATA_INTEGRITY_VIOLATION(409, "DATA_INTEGRITY_VIOLATION", "데이터 무결성 위반입니다."),
    CONSTRAINT_VIOLATION(400, "CONSTRAINT_VIOLATION", "제약 조건 위반입니다."),
    RESOURCE_ALREADY_EXISTS(409, "RESOURCE_ALREADY_EXISTS", "리소스가 이미 존재합니다."),
    RESOURCE_NOT_FOUND(404, "RESOURCE_NOT_FOUND", "리소스를 찾을 수 없습니다."),
    INVALID_INPUT(400, "INVALID_INPUT", "잘못된 입력입니다."),
    DATABASE_ERROR(500, "DATABASE_ERROR", "데이터베이스 오류입니다."),

    // 파일 관련 예외 코드
    FILE_NOT_FOUND(404, "FILE_NOT_FOUND", "파일을 찾을 수 없습니다."),
    FILE_UPLOAD_FAILED(500, "FILE_UPLOAD_FAILED", "파일 업로드에 실패했습니다."),
    FILE_SIZE_EXCEEDED(413, "FILE_SIZE_EXCEEDED", "파일 크기가 초과되었습니다."),
    UNSUPPORTED_FILE_TYPE(415, "UNSUPPORTED_FILE_TYPE", "지원되지 않는 파일 형식입니다."),

    // 네트워크 관련 예외 코드
    NETWORK_ERROR(503, "NETWORK_ERROR", "네트워크 오류입니다."),
    TIMEOUT(504, "TIMEOUT", "요청이 시간 초과되었습니다."),

    // 임시 오류 코드
    TEMP_ERROR(999, "TEMP_ERROR", "임시 오류입니다. TEMP 값을 변경하세요");






    private final int status;
    private final String errorCode;
    private final String message;

    ErrorCode(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
